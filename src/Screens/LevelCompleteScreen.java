package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Sprite;
import SpriteFont.SpriteFont;
import Utils.Colors;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import Level.Map;
import Maps.PauseScreenMap;

public class LevelCompleteScreen extends Screen {

    protected BufferedImage levelCompleteImage;
    protected SpriteFont message;
    protected ScreenCoordinator screenCoordinator;
    private Sprite levelCompleteSprite;
    protected Map background;
    protected SpriteFont nextLevel;
    protected SpriteFont restart;
    protected SpriteFont menu;
    protected int curr = 0;
    protected int chosen;
    private int timer;
    private int xLoc;
    private int yLoc;

    private Font maruMonica;

    public LevelCompleteScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;

        levelCompleteImage = ImageLoader.load("LevelComplete.png"); // Replace with your image file name.
        levelCompleteSprite = (new Sprite(ImageLoader.loadSubImage("LevelComplete.png", Colors.MAGENTA, 0, 0, 185, 128)));
        levelCompleteSprite.setScale(3);
        levelCompleteSprite.setLocation(130, 100);
    }

    @Override
    public void initialize() {
        try {
            InputStream is = getClass().getResourceAsStream("/Level/font/x12y16pxMaruMonica.ttf");
            if (is != null) {
                maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            } else {
                System.out.println("Font not found");
            }
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        } 

        message = new SpriteFont("Level Complete!", 150, 50, maruMonica.deriveFont(80f), new Color(255, 255, 255));
        message.setOutlineColor(Color.black);
        message.setOutlineThickness(3);

        nextLevel = new SpriteFont("Next Level", 330, 200, maruMonica.deriveFont(40f), new Color(255, 255, 255));
        nextLevel.setOutlineColor(Color.black);
        nextLevel.setOutlineThickness(3);

        restart = new SpriteFont("Restart", 330, 260, maruMonica.deriveFont(40f), new Color(255, 255, 255));
        restart.setOutlineColor(Color.black);
        restart.setOutlineThickness(3);

        menu = new SpriteFont("Menu", 330, 320, maruMonica.deriveFont(40f), new Color(255, 255, 255));
        menu.setOutlineColor(Color.black);
        menu.setOutlineThickness(3);

        timer = 0;
        background = new PauseScreenMap(); // Use an appropriate background map if available.
        background.setAdjustCamera(false);
    }

    @Override
    public void update() {
        // Handle user input to navigate options.
        if (Keyboard.isKeyDown(Key.S) && timer == 0) {
            timer = 14;
            curr++;
        } else if (Keyboard.isKeyDown(Key.W) && timer == 0) {
            timer = 14;
            curr--;
        } else {
            if (timer > 0) {
                timer--;
            }
        }

        // Loop through options.
        if (curr > 2) {
            curr = 0;
        } else if (curr < 0) {
            curr = 2;
        }

        // Highlight the current option.
        if (curr == 0) {
            nextLevel.setColor(Color.black);
            nextLevel.setOutlineColor(Color.white);
            restart.setColor(Color.white);
            restart.setOutlineColor(Color.black);
            menu.setColor(Color.white);
            menu.setOutlineColor(Color.black);
            xLoc = 300;
            yLoc = 212;
        } else if (curr == 1) {
            restart.setColor(Color.black);
            restart.setOutlineColor(Color.white);
            nextLevel.setColor(Color.white);
            nextLevel.setOutlineColor(Color.black);
            menu.setColor(Color.white);
            menu.setOutlineColor(Color.black);
            xLoc = 300;
            yLoc = 272;
        } else if (curr == 2) {
            menu.setColor(Color.black);
            menu.setOutlineColor(Color.white);
            nextLevel.setColor(Color.white);
            nextLevel.setOutlineColor(Color.black);
            restart.setColor(Color.white);
            restart.setOutlineColor(Color.black);
            xLoc = 300;
            yLoc = 332;
        }

        // Handle option selection.
        if (Keyboard.isKeyDown(Key.E)) {
            chosen = curr;
            if (chosen == 0) {
                // Proceed to the next level.
                if(screenCoordinator.beatLvl1()){
                    if(screenCoordinator.beatLvl2()){
                        screenCoordinator.setGameState(GameState.NEWLEVEL);
                    }else{
                        screenCoordinator.setGameState(GameState.LEVEL2DIALOGUE);
                    }
                }
            } else if (chosen == 1) {
                // Restart the current level.
                if(screenCoordinator.beatLvl1()){
                    if(screenCoordinator.beatLvl2()){
                        screenCoordinator.toggleLvl2();
                    }else{
                        screenCoordinator.toggleLvl1();
                    }
                }
                screenCoordinator.setGameState(GameState.LEVEL);
            } else if (chosen == 2) {
                // Return to the menu.
                screenCoordinator.setGameState(GameState.MENU);
            }
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        message.draw(graphicsHandler);
        nextLevel.draw(graphicsHandler);
        restart.draw(graphicsHandler);
        menu.draw(graphicsHandler);
        graphicsHandler.drawFilledRectangleWithBorder(xLoc, yLoc, 20, 20, new Color(255, 255, 255), Color.black, 2);
    }
}
