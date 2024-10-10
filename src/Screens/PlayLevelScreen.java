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
import Players.Cat;
import Utils.Colors;
import Utils.Direction;
import java.awt.image.BufferedImage;

// This class is for when the RPG game is actually being played
public class PlayLevelScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected Map map;
    protected Player player;
    protected PlayLevelScreenState playLevelScreenState;
    protected WinScreen winScreen;
    protected FlagManager flagManager;
    private Sprite ranger;
    private Sprite[] bears;  // Array to hold 5 bear sprites
    protected KeyLocker keyLocker = new KeyLocker();
    protected HelpScreen helpScreen;
    private boolean helpOn = false;
    private Sprite helpScreenSprite;

    public PlayLevelScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;

        // Setup Ranger sprite (change dimensions according to image size)
        ranger = loadSprite("HelpIcon.png", 0, 0, 64, 64);
        ranger.setScale(1);
        ranger.setLocation(725, 505);

        // Initialize 5 bear sprites (each bear uses bear.png with 243x245 dimensions)
        bears = new Sprite[5];
        for (int i = 0; i < bears.length; i++) {
            // Load each bear with its own sprite, assuming bear.png is 243x245
            bears[i] = loadSprite("HelpIcon.png", 0, 0, 64, 64);
            bears[i].setScale(1);  // Set bear's scale to 1 (adjust if needed)

            // Set unique locations for each bear on the map (you can modify positions)
            bears[i].setLocation(100 + i * 120, 450);  // Example positions
        }

        // Setup help screen sprite
        helpScreenSprite = loadSprite("Helper.png", 0, 0, 800, 605);
        helpScreenSprite.setScale(1);
        helpScreenSprite.setLocation(0, 0);
    }

    // Helper function to load sprite and check dimensions
    private Sprite loadSprite(String filePath, int x, int y, int width, int height) {
        BufferedImage image = ImageLoader.load(filePath);
        // Check if image loaded correctly
        if (image == null) {
            throw new IllegalArgumentException("Image not found: " + filePath);
        }
        System.out.println("Loaded image: " + filePath + " Dimensions: " + image.getWidth() + "x" + image.getHeight());
        
        // Ensure requested subimage dimensions are valid
        if (x < 0 || y < 0 || x + width > image.getWidth() || y + height > image.getHeight()) {
            throw new IllegalArgumentException("Invalid dimensions for image: " + filePath);
        }
        return new Sprite(image.getSubimage(x, y, width, height));
    }

    public void initialize() {
        // Setup flag manager
        flagManager = new FlagManager();
        flagManager.addFlag("gameStart", false);
        flagManager.addFlag("hasTalkedToWalrus", false);
        flagManager.addFlag("hasTalkedToDinosaur", false);
        flagManager.addFlag("hasFoundBall", false);

        // Define/setup map
        map = new Level1();
        map.setFlagManager(flagManager);

        // Setup help screen
        helpScreen = new HelpScreen(map.getFlagManager());

        // Setup player
        player = new Cat(map.getPlayerStartPosition().x, map.getPlayerStartPosition().y);
        player.setMap(map);
        playLevelScreenState = PlayLevelScreenState.RUNNING;
        player.setFacingDirection(Direction.LEFT);
        map.setPlayer(player);

        // Let pieces of map know which button to listen for as the "interact" button
        map.getTextbox().setInteractKey(player.getInteractKey());

        // Preload all scripts ahead of time rather than loading them dynamically
        map.preloadScripts();

        winScreen = new WinScreen(this);
    }

    public void update() {
        // Opens help screen when H is clicked
        if (Keyboard.isKeyDown(Key.H) && !keyLocker.isKeyLocked(Key.H) && !helpOn) {
            helpOn = true;
            helpScreen.changeStatus();
            keyLocker.lockKey(Key.H);
        }
        if (Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC) && helpOn) {
            helpOn = false;
            helpScreen.changeStatus();
            keyLocker.lockKey(Key.ESC);
        }
        if (Keyboard.isKeyUp(Key.H)) {
            keyLocker.unlockKey(Key.H);
        }
        if (Keyboard.isKeyUp(Key.ESC)) {
            keyLocker.unlockKey(Key.ESC);
        }

        if (!helpOn) {
            switch (playLevelScreenState) {
                case RUNNING:
                    player.update();
                    map.update(player);
                    break;
                case LEVEL_COMPLETED:
                    winScreen.update();
                    break;
            }
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        if (helpOn) {
            map.draw(player, graphicsHandler);
            helpScreenSprite.draw(graphicsHandler);
        } else {
            switch (playLevelScreenState) {
                case RUNNING:
                    map.draw(player, graphicsHandler);
                    ranger.draw(graphicsHandler);
                    
                    // Draw all bears
                    for (Sprite bear : bears) {
                        bear.draw(graphicsHandler);
                    }
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

    // Enum for PlayLevelScreen states
    private enum PlayLevelScreenState {
        RUNNING, LEVEL_COMPLETED
    }
}
