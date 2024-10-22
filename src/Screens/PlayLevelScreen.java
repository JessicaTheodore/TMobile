package Screens;

import java.util.ArrayList;

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
import Players.Cat;
import Utils.Colors;
import Utils.Direction;

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

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;

        ranger = new Sprite(ImageLoader.loadSubImage("HelpIcon.png", Colors.MAGENTA, 0, 0, 64, 64));
        ranger.setScale(1);
        ranger.setLocation(725, 505); 

        pause = (new Sprite(ImageLoader.loadSubImage("PauseScreen.png", Colors.MAGENTA, 0, 0, 185, 128)));
        pause.setScale(3);
        pause.setLocation(130, 100);  

        helpStages[0] = true;
        helpScreenSprite[0] = (new Sprite(ImageLoader.loadSubImage("BreakLogHelp.png", Colors.MAGENTA, 0, 0, 800, 605)));
        helpScreenSprite[0].setScale(1);
        helpScreenSprite[0].setLocation(0, 0); 

        helpStages[1] = false;
        helpScreenSprite[1] = (new Sprite(ImageLoader.loadSubImage("Helper.png", Colors.MAGENTA, 0, 0, 800, 605)));
        helpScreenSprite[1].setScale(1);
        helpScreenSprite[1].setLocation(0, 0); 
    }

    public PlayLevelScreen(ScreenCoordinator screenCoordinator, float x, float y) {
        this.screenCoordinator = screenCoordinator;

        ranger = new Sprite(ImageLoader.loadSubImage("HelpIcon.png", Colors.MAGENTA, 0, 0, 64, 64));
        ranger.setScale(1);
        ranger.setLocation(725, 505); 

        pause = (new Sprite(ImageLoader.loadSubImage("PauseScreen.png", Colors.MAGENTA, 0, 0, 185, 128)));
        pause.setScale(3);
        pause.setLocation(130, 100);  

        helpStages[0] = true;
        helpScreenSprite[0] = (new Sprite(ImageLoader.loadSubImage("BreakLogHelp.png", Colors.MAGENTA, 0, 0, 800, 605)));
        helpScreenSprite[0].setScale(1);
        helpScreenSprite[0].setLocation(0, 0); 

        helpStages[1] = false;
        helpScreenSprite[1] = (new Sprite(ImageLoader.loadSubImage("Helper.png", Colors.MAGENTA, 0, 0, 800, 605)));
        helpScreenSprite[1].setScale(1);
        helpScreenSprite[1].setLocation(0, 0); 

        this.x = x;
        this.y = y;
    }

    public void initialize() {
        flagManager = new FlagManager();

        if(start){
            System.out.println("flag is true");
            // Setup flag manager
            flagManager.addFlag("gameStart", false);
            flagManager.addFlag("hasTalkedToWalrus", false);
            flagManager.addFlag("hasTalkedToDinosaur", false);
            flagManager.addFlag("hasFoundBall", false);
            flagManager.addFlag("brokeLog", false);

            // Define/setup map
            map = new Level1();
            map.setFlagManager(flagManager);

            // Setup help screen
            helpScreen = new HelpScreen(map.getFlagManager());

            // Setup pause screen
            pauseScreen = new PauseScreen(screenCoordinator);

            // Setup player
            player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
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
        }else{
            System.out.println("flag is false");
        }
        
    }

    public void update() {
        // Opens help screen when h is clicked
        if (Keyboard.isKeyDown(Key.H) && !keyLocker.isKeyLocked(Key.H) && !helpOn && !pauseOn) {
            helpOn = true;
            helpScreen.changeStatus();
            keyLocker.lockKey(Key.H);
        }
        if (Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC) && helpOn) {
            helpOn = false;
            helpScreen.changeStatus();
            keyLocker.lockKey(Key.ESC);
        }
        if(Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC) && !helpOn){
            pauseOn = !pauseOn;
            //pauseScreen.changeStatus();
            start = false;
            screenCoordinator.setGameState(GameState.PAUSE);
            keyLocker.lockKey(Key.ESC);
        }
        if (Keyboard.isKeyUp(Key.H)) {
            keyLocker.unlockKey(Key.H);
        }
        if (Keyboard.isKeyUp(Key.ESC)) {
            keyLocker.unlockKey(Key.ESC);
        }
        if(flagManager.isFlagSet("brokeLog")){
            helpStages[1] = true;
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
                    break;
                // If level has been completed, bring up level cleared screen
                case LEVEL_COMPLETED:
                    winScreen.update();
                    break;
            }
        }

        this.x = player.getX();
        this.y = player.getY();


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
                    break;
                case LEVEL_COMPLETED:
                    winScreen.draw(graphicsHandler);
                    break;
            }
        }
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
}
