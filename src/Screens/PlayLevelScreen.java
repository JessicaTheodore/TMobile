package Screens;

import java.util.ArrayList;

import Enemies.BearEnemy;
import Enemies.BreakableLog;
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
import NPCs.Slingshot;
import Level.*;
import Maps.Level1;
import Players.Cat;
import Utils.Colors;
import Utils.Direction;
import Utils.HealthSystem;
import Utils.Point;

// This class is for when the RPG game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected FlagManager flagManager;
    private Sprite ranger;
    protected Sprite pickUp;
    protected Sprite slingShotAcquired;
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
    protected int counter = 0;
    protected boolean hurt = false;
    protected Sprite flash;
    protected float xLocation; 
    protected float yLocation;
    protected Direction dir;

    private HealthSystem healthSystem;
    private Slingshot slingshot;


    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;

        player.resetHealth();

        ranger = new Sprite(ImageLoader.loadSubImage("HelpIcon.png", Colors.MAGENTA, 0, 0, 64, 64));
        ranger.setScale(1);
        ranger.setLocation(725, 505); 

        newHelp = new Sprite(ImageLoader.loadSubImage("exclam.png", Colors.MAGENTA, 0, 0, 29, 29));
        newHelp.setScale(1);
        newHelp.setLocation(726, 487); 

        flash = new Sprite(ImageLoader.loadSubImage("darkFlash.png", Colors.MAGENTA, 0, 0, 800, 605), true);
        flash.setScale(1);
        flash.setLocation(0, 0);

        pickUp = new Sprite(ImageLoader.loadSubImage("PickUp.png", Colors.MAGENTA, 0, 0, 99, 37));
        pickUp.setScale(2);
        pickUp.setLocation(326, 457); 

        slingShotAcquired = new Sprite(ImageLoader.loadSubImage("SlingShotAcquired.png", Colors.MAGENTA, 0, 0, 99, 37));
        slingShotAcquired.setScale(3);
        slingShotAcquired.setLocation(240, 457);

        breakL = new Sprite(ImageLoader.loadSubImage("SpaceBreak.png", Colors.MAGENTA, 0, 0, 123, 37));
        breakL.setScale(2);
        breakL.setLocation(326, 457); 

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

            
        
            // Setup flag manager
            flagManager.addFlag("gameStart", false);
            flagManager.addFlag("brokeLog", false);
            flagManager.addFlag("pickedUpSlingShot", false);
            flagManager.addFlag("beatLvl1", false);
            flagManager.addFlag("nearSlingShot", false);
            flagManager.addFlag("SpaceBreak", false);
           
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
            if(!start){
                player.setLocation(xLocation, yLocation);
                player.setFacingDirection(dir);
            }
            map.setPlayer(player);

            // Let pieces of map know which button to listen for as the "interact" button
            map.getTextbox().setInteractKey(player.getInteractKey());

            // Preload all scripts ahead of time rather than loading them dynamically
            map.preloadScripts();


            // Initialize win screen
            winScreen = new WinScreen(this);

            healthSystem = new HealthSystem(player.getCurrentHealth()); // 5 hearts initially
        
    }

    public void update() {

    // Opens help screen when h is clicked
    if(flagManager.isFlagSet("beatLvl1")){
        screenCoordinator.setGameState(GameState.LEVELCOMPLETE);
        System.out.println("beat lvl 1");
    }
    if (Keyboard.isKeyDown(Key.H) && !keyLocker.isKeyLocked(Key.H) && helpOn) {
        helpOn = false;
        helpNew = false;
        helpScreen.changeStatus();
        keyLocker.lockKey(Key.H);
    }
    if (Keyboard.isKeyDown(Key.U) && !keyLocker.isKeyLocked(Key.U)) {
        System.out.println("Debug - skipped level 1");
        screenCoordinator.toggleLvl1();
        screenCoordinator.setGameState(GameState.LEVEL2);
        keyLocker.lockKey(Key.U);
    }
    if (Keyboard.isKeyDown(Key.H) && !keyLocker.isKeyLocked(Key.H) && !helpOn && !pauseOn) {
        helpOn = true;
        helpNew = false;
        helpScreen.changeStatus();
        keyLocker.lockKey(Key.H);
    }
    if (Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC) && helpOn) {
        helpOn = false;
        helpScreen.changeStatus();
        keyLocker.lockKey(Key.ESC);
    }
    
    if(Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC) && !helpOn){
        //pauseOn = !pauseOn;
        //pauseScreen.changeStatus();
        start = false;
        xLocation = player.getX();
        yLocation = player.getY();
        dir = player.getFacingDirection(); 
        screenCoordinator.setGameState(GameState.PAUSE);
        keyLocker.lockKey(Key.ESC);
    }
    if(Keyboard.isKeyDown(Key.L) && !keyLocker.isKeyLocked(Key.L)){
        screenCoordinator.setGameState(GameState.DEATH);
        keyLocker.lockKey(Key.L);
    }
    if (Keyboard.isKeyUp(Key.H)) {
        keyLocker.unlockKey(Key.H);
    }
    if (Keyboard.isKeyUp(Key.ESC)) {
        keyLocker.unlockKey(Key.ESC);
    }

    /* if(map.getSurroundingMapEntities(player).contains(map.getEnemies())){

    } */

    if(!player.intersects(map.getTriggers().get(1))){
        flagManager.unsetFlag("SpaceBreak");
    }
    if(!player.intersects(map.getTriggers().get(2))){
        flagManager.unsetFlag("nearSlingShot");
    }
    
    if(!helpStages[1]){
        if(flagManager.isFlagSet("brokeLog")){
            //System.out.println("Broken");
            helpStages[1] = true;
            helpNew = true;
        }
    }
    
    if(flagManager.isFlagSet("pickedUpSlingShot")){
        //helpStages[2] = true;
    }
    
    
    if (helpOn) {
        //helpScreen.update();
    } else if(pauseOn){
    }else {
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
                }
                break;
            // If level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                winScreen.update();
                break;
        }
    }
   
    if(flagManager.isFlagSet("pickedUpSlingShot")) {
        player.changeSlingshotStatus();
    }

    for (NPC npc : map.getNPCs()) {
        npc.update();  // Call update on each NPC (including Slingshot)

    if (playerCollidesWithBear()) {
        healthSystem.decreaseHealth();
        
        // If the player is out of hearts, go to the GAME_OVER screen
        if (healthSystem.getCurrentHealth() <= 0) {
            screenCoordinator.setGameState(GameState.GAMEOVER);
        }
    }
}  
    

    }

    public void draw(GraphicsHandler graphicsHandler) {

        for (NPC npc : map.getNPCs()) {
            npc.draw(graphicsHandler);  // Draw each NPC
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
                    /* for(int i = 0; i < map.getTriggers().size(); i ++){
                        map.getTriggers().get(i).draw(graphicsHandler);
                    } */
                    if(player.getHurt()){
                    }
                    ranger.draw(graphicsHandler);
                    if(helpNew){
                        newHelp.draw(graphicsHandler);
                    }

                    if(!flagManager.isFlagSet("pickedUpSlingShot") && flagManager.isFlagSet("nearSlingShot")){
                        pickUp.draw(graphicsHandler);
                        //flagManager.setFlag("nearSlingShot");
                        //flagManager.unsetFlag("pickedUpSlingShot");
                    }
                    if (flagManager.isFlagSet("pickedUpSlingShot")) {
                        if(counter < 600) {
                            slingShotAcquired.draw(graphicsHandler);
                        } 
                        counter++;
                        //flagManager.unsetFlag("nearSlingShot");
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
