package Screens;

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
import Maps.TestMap;
import Players.Cat;
import Utils.Colors;
import Utils.Direction;
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
    protected KeyLocker keyLocker = new KeyLocker();
    protected HelpScreen helpScreen;
    private boolean helpOn = false;
    private Sprite helpScreenSprite;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;

        ranger = new Sprite(ImageLoader.loadSubImage("RangerIcon.png", Colors.MAGENTA, 0,0, 32, 32));
        ranger.setScale(2);
        ranger.setLocation(720, 505); 

        helpScreenSprite = new Sprite(ImageLoader.loadSubImage("Help.png", Colors.MAGENTA, 0,0, 180, 120));
        helpScreenSprite.setScale(1);
        helpScreenSprite.setLocation(0, 0); 

    }

    public void initialize() {
        // setup state
        flagManager = new FlagManager();
        flagManager.addFlag("gameStart", false);
        flagManager.addFlag("hasTalkedToWalrus", false);
        flagManager.addFlag("hasTalkedToDinosaur", false);
        flagManager.addFlag("hasFoundBall", false);

        // define/setup map
        map = new Level1();
        map.setFlagManager(flagManager);

        //set up help screen
        helpScreen = new HelpScreen(map.getFlagManager());

        // setup player
        player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        player.setFacingDirection(Direction.LEFT);

        map.setPlayer(player);

        // let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // preloads all scripts ahead of time rather than loading them dynamically
        // both are supported, however preloading is recommended
        map.preloadScripts();

        winScreen = new WinScreen(this);
    }

    public void update() {
        // Opens help screen when h is clicked
        if(Keyboard.isKeyDown(Key.H) && !keyLocker.isKeyLocked(Key.H) && !helpOn){
            helpOn = true;
            helpScreen.changeStatus();
            keyLocker.lockKey(Key.H);
        }
        if(Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC) && helpOn){
            helpOn = false;
            helpScreen.changeStatus();
            keyLocker.lockKey(Key.ESC);
        }
        if(Keyboard.isKeyUp(Key.H)){
            keyLocker.unlockKey(Key.H);
        }
        if(Keyboard.isKeyUp(Key.ESC)){
            keyLocker.unlockKey(Key.ESC);
        }
        if(helpOn){
            //helpScreen.update();
        }else{

            // based on screen state, perform specific actions
            switch (playLevelScreenState) {
            // if level is "running" update player and map to keep game logic for the platformer level going
            case RUNNING:
                player.update();
                map.update(player);
                break;
            // if level has been completed, bring up level cleared screen
            case LEVEL_COMPLETED:
                winScreen.update();
                break;
        }
        }
        
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // based on screen state, draw appropriate graphics
        if(helpOn){
            map.draw(player, graphicsHandler);
            helpScreenSprite.draw(graphicsHandler);
        }else{
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

    public void drawMap(GraphicsHandler graphicsHandler){
        map.draw(player, graphicsHandler);
    }

    // This enum represents the different states this screen can be in
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED
    }
}
