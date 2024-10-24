package Screens;

import java.util.ArrayList;

import Enemies.BearEnemy;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Sprite;
import NPCs.Bear;
import Level.*;
import Maps.Level1;
import Players.Cat;
import Utils.Colors;
import Utils.Direction;
import Utils.HealthSystem;

// This class is for when the RPG game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected FlagManager flagManager;
    private Sprite ranger;
    protected KeyLocker keyLocker = new KeyLocker();
    protected HelpScreen helpScreen;
    protected PauseScreen pauseScreen;
    protected Sprite pause;
    protected boolean helpOn = false;
    protected boolean pauseOn = false;
    protected final int helpSize = 99;
    protected boolean[] helpStages = new boolean[helpSize]; // 0: Starting area , 1: 
    protected Sprite[] helpScreenSprite = new Sprite[helpSize];
    protected boolean start = true;
    protected float x;
    protected float y;
    protected Trigger trigger;

    private HealthSystem healthSystem;


    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;

        ranger = new Sprite(ImageLoader.loadSubImage("HelpIcon.png", Colors.MAGENTA, 0, 0, 64, 64));
        ranger.setScale(1);
        ranger.setLocation(725, 505); 

        /* pause = (new Sprite(ImageLoader.loadSubImage("PauseScreen.png", Colors.MAGENTA, 0, 0, 185, 128)));
        pause.setScale(3);
        pause.setLocation(130, 100);   */

        helpStages[0] = true;
        helpScreenSprite[0] = (new Sprite(ImageLoader.loadSubImage("BreakLogHelp.png", Colors.MAGENTA, 0, 0, 800, 605)));
        helpScreenSprite[0].setScale(1);
        helpScreenSprite[0].setLocation(0, 0); 

        helpStages[1] = false;
        helpScreenSprite[1] = (new Sprite(ImageLoader.loadSubImage("SlingShotHelp.png", Colors.MAGENTA, 0, 0, 800, 605)));
        helpScreenSprite[1].setScale(1);
        helpScreenSprite[1].setLocation(0, 0); 

        helpScreenSprite[2] = (new Sprite(ImageLoader.loadSubImage("Helper.png", Colors.MAGENTA, 0, 0, 800, 605)));
        helpScreenSprite[2].setScale(1);
        helpScreenSprite[2].setLocation(0, 0); 

    }

    public void initialize() {
        flagManager = new FlagManager();

        if(start){
            // Setup flag manager
            flagManager.addFlag("gameStart", false);
            flagManager.addFlag("hasTalkedToWalrus", false);
            flagManager.addFlag("hasTalkedToDinosaur", false);
            flagManager.addFlag("hasFoundBall", false);
            flagManager.addFlag("brokeLog", false);
            flagManager.addFlag("pickedUpSlingShot", false);
            flagManager.addFlag("beatLvl1", false);

            // triger for beating level


            // Define/setup map
            map = new Level1();
            map.setFlagManager(flagManager);

            // Setup help screen
            helpScreen = new HelpScreen(map.getFlagManager());

            // Setup pause screen
            pauseScreen = new PauseScreen(screenCoordinator);

            // Setup player
            player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y, screenCoordinator);
            player.setMap(map);
            playLevelScreenState = PlayLevelScreenState.RUNNING;
            player.setFacingDirection(Direction.DOWN);
            map.setPlayer(player);

            // Let pieces of map know which button to listen for as the "interact" button
            map.getTextbox().setInteractKey(player.getInteractKey());

            // Preload all scripts ahead of time rather than loading them dynamically
            map.preloadScripts();

            // Initialize win screen
            winScreen = new WinScreen(this);

            healthSystem = new HealthSystem(player.getCurrentHealth()); // 5 hearts initially
        }
    }

    public void update() {
    // Opens help screen when h is clicked
    if (flagManager.isFlagSet("beatLvl1")) {
        screenCoordinator.setGameState(GameState.LEVELCOMPLETE);
        System.out.println("beat lvl 1");
    }
    
    // Other existing keyboard input checks...

    if (helpOn) {
        // Help screen updates if help is enabled
    } else if (pauseOn) {
        // Pause screen updates if pause is enabled
    } else {
        // Update player and map logic
        switch (playLevelScreenState) {
            case RUNNING:
                player.update();
                map.update(player);
                
                // Update all enemies (bears)
                for (MapEntity enemy : map.getEnemies()) {
                    if (enemy instanceof BearEnemy) {
                        enemy.update(); // Call the update method for each bear
                    }
                }
                break;
            case LEVEL_COMPLETED:
                winScreen.update();
                break;
            }
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
            
        } else if(pauseOn){
            //map.draw(player, graphicsHandler);
            //pause.draw(graphicsHandler);
        }
        else {
            switch (playLevelScreenState) {
                case RUNNING:
                    map.draw(player, graphicsHandler);
                    ranger.draw(graphicsHandler);
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
