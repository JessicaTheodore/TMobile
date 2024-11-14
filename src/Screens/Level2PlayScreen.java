package Screens;

import Enemies.BearEnemy;
import Enemies.SquirrelEnemy;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Sprite;
import Level.*;
import Maps.Level1;
import Maps.Level2;
import Maps.TestMap;
import NPCs.Bear;
import Players.Cat;
import Utils.Colors;
import Utils.Direction;
import Utils.HealthSystem;
import Utils.Point;

// This class is for when the RPG game is actually being played
public class Level2PlayScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected FlagManager flagManager;
    private Sprite ranger;
    protected Sprite pickUp;
    protected Sprite breakL;
    protected KeyLocker keyLocker = new KeyLocker();
    protected HelpScreen helpScreen;
    protected PauseScreen pauseScreen;
    protected Sprite pause;
    protected Sprite newHelp;
    protected boolean helpOn = false;
    protected boolean pauseOn = false;
    protected final int helpSize = 99;
    protected boolean[] helpStages = new boolean[helpSize]; // 0: Starting area , 1: 
    protected Sprite[] helpScreenSprite = new Sprite[helpSize];
    protected boolean start = true;
    protected float x;
    protected float y;
    protected Trigger trigger;
    protected boolean helpNew = true; 

    private HealthSystem healthSystem;


    public Level2PlayScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;

        player.resetHealth();

        ranger = new Sprite(ImageLoader.loadSubImage("HelpIcon.png", Colors.MAGENTA, 0, 0, 64, 64));
        ranger.setScale(1);
        ranger.setLocation(725, 505); 

        newHelp = new Sprite(ImageLoader.loadSubImage("exclam.png", Colors.MAGENTA, 0, 0, 29, 29));
        newHelp.setScale(1);
        newHelp.setLocation(726, 487); 

        helpScreenSprite[0] = (new Sprite(ImageLoader.loadSubImage("Helper.png", Colors.MAGENTA, 0, 0, 800, 605)));
        helpScreenSprite[0].setScale(1);
        helpScreenSprite[0].setLocation(0, 0); 

        helpStages[0] = true;

    }

    public void initialize() {
        flagManager = new FlagManager();

        if(start){
            // Setup flag manager
            flagManager.addFlag("gameStart", false);
            flagManager.addFlag("beatLvl2", false);

            // Define/setup map
            map = new Level2();
            map.setFlagManager(flagManager);

            // Setup pause screen
            pauseScreen = new PauseScreen(screenCoordinator);

            // Setup player
            player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y, screenCoordinator);
            player.setMap(map);
            playLevelScreenState = PlayLevelScreenState.RUNNING;
            player.setFacingDirection(Direction.DOWN);
            player.changeSlingshotStatus();
            map.setPlayer(player);

            // Let pieces of map know which button to listen for as the "interact" button
            map.getTextbox().setInteractKey(player.getInteractKey());

            // Preload all scripts ahead of time rather than loading them dynamically
            map.preloadScripts();

            // Initialize win screen
            ////////////////
            //winScreen = new WinScreen(this);

            healthSystem = new HealthSystem(player.getCurrentHealth()); // 5 hearts initially
        }
    }

    public void update() {
        // Logic for beating level changed game state if player reached trigger to beat level 2
        if(flagManager.isFlagSet("beatLvl2")){
            screenCoordinator.setGameState(GameState.LEVELCOMPLETE);
            System.out.println("beat lvl 2");
        }

        // Help screen logic
        if (Keyboard.isKeyDown(Key.H) && !keyLocker.isKeyLocked(Key.H) && helpOn) {
            helpOn = false;
            helpNew = false;
            keyLocker.lockKey(Key.H);
        }
        if (Keyboard.isKeyDown(Key.H) && !keyLocker.isKeyLocked(Key.H) && !helpOn && !pauseOn) {
            helpOn = true;
            helpNew = false;
            keyLocker.lockKey(Key.H);
        }
        if(!helpStages[1]){
        }
        

        // Pause screen logic
        if (Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC) && helpOn) {
            helpOn = false;
            keyLocker.lockKey(Key.ESC);
        }
        if(Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC) && !helpOn){
            start = false;
            screenCoordinator.setGameState(GameState.PAUSE);
            keyLocker.lockKey(Key.ESC);
        }

        // Unlocking keys
        if (Keyboard.isKeyUp(Key.ESC)) {
            keyLocker.unlockKey(Key.ESC);
        }
        if (Keyboard.isKeyUp(Key.H)) {
            keyLocker.unlockKey(Key.H);
        }
        
        // Based on screen state, perform specific actions
        switch (playLevelScreenState) {
            // If level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                for (MapEntity enemy : map.getEnemies()) {
                    if (enemy instanceof BearEnemy) {
                        enemy.update(); // Call the update method for each bear
                    }
                    if (enemy instanceof SquirrelEnemy) {
                        enemy.update(); // Call the update method for each bear
                    }
                }
                break;
            // If level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                winScreen.update();
                break;
        }
        
        // Health stuff
        if (playerCollidesWithBear()) {
            healthSystem.decreaseHealth();
            
            // If the player is out of hearts, go to the GAME_OVER screen
            if (healthSystem.getCurrentHealth() <= 0) {
                screenCoordinator.setGameState(GameState.GAMEOVER);
            }
        }  

        // Debug stuff 
        if(Keyboard.isKeyDown(Key.L) && !keyLocker.isKeyLocked(Key.L)){
            screenCoordinator.setGameState(GameState.DEATH);
            keyLocker.lockKey(Key.L);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // Based on screen state, draw appropriate graphics
        if (helpOn) {
            for(int i = helpSize-1; i >=0; i--){
                if(helpStages[i] == true){
                    map.draw(player, graphicsHandler);
                    helpScreenSprite[i].draw(graphicsHandler);
                    break;
                }
            }
        }else {
            switch (playLevelScreenState) {
                case RUNNING:
                    map.draw(player, graphicsHandler);
                    /* for(int i = 0; i < map.getTriggers().size(); i ++){
                        map.getTriggers().get(i).draw(graphicsHandler);
                    } */
                    ranger.draw(graphicsHandler);
                    if(helpNew){
                        newHelp.draw(graphicsHandler);
                    }
                    if(!flagManager.isFlagSet("pickedUpSlingShot") && flagManager.isFlagSet("nearSlingShot")){
                        pickUp.draw(graphicsHandler);
                        flagManager.unsetFlag("nearSlingShot");
                    }
                    if(!flagManager.isFlagSet("brokeLog") && flagManager.isFlagSet("SpaceBreak")){
                        breakL.draw(graphicsHandler);
                        //flagManager.unsetFlag("SpaceBreak");
                    }
                    healthSystem.draw(graphicsHandler);
                    break;
                case LEVEL_COMPLETED:
                    winScreen.draw(graphicsHandler);
                    break;
            }
        }
        //map.getTriggers().get(map.getTriggers().size()-1).draw(graphicsHandler);
    }

    public PlayLevelScreenState getPlayLevelScreenState() {
        return playLevelScreenState;
    }

    public void resetLevel() {
        initialize();
    }

    public void goBackToMenu() {
        screenCoordinator.setGameState(GameState.MENU);
    }

    public void drawMap(GraphicsHandler graphicsHandler) {
        map.draw(player, graphicsHandler);
    }

    public float getX(){return this.x;}
    public float getY(){return this.y;}


    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED
    }

    private boolean playerCollidesWithBear() {
        // Example logic for collision detection with a bear object
        // Replace with your actual collision check logic
        for (MapEntity entity : map.getEnemies()) {
            if (entity instanceof Bear && player.intersects(entity)) {
                return true;
            }
        }
        return false;
        }
}