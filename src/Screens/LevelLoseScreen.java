package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

// This is the class for the level lose screen
public class LevelLoseScreen extends Screen {
    protected SpriteFont loseMessage;
    protected SpriteFont instructions;
    protected KeyLocker keyLocker = new KeyLocker();
    protected ScreenCoordinator screenCoordinator;
    private Font maruMonica;

    public LevelLoseScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
        initialize();
    }

    // super("title_screen_map.txt", new CommonTileset());
    // back = new Sprite(ImageLoader.loadSubImage("Intro.png", Colors.MAGENTA, 0, 0, 800, 600));
    // back.setScale(1);
    // back.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
    // back.setLocation(0, 0);

    @Override
    public void initialize() {

         //importing font type
         try {
            InputStream is = getClass().getResourceAsStream("/Level/font/x12y16pxMaruMonica.ttf");
            if (is != null) {
                maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
            } else {
                System.out.println("Font not found");
            }
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        loseMessage = new SpriteFont("The Giant has claimed another soul.", 220, 239, maruMonica.deriveFont(30f), Color.white);
        instructions = new SpriteFont("Press Space to Restart Level or Escape to go back to the Main Menu", 160, 279, maruMonica.deriveFont(20f), Color.white);
        keyLocker.lockKey(Key.E);
        keyLocker.lockKey(Key.ESC);
    }

    @Override
    public void update() {
        if (Keyboard.isKeyUp(Key.E)) {
            keyLocker.unlockKey(Key.E);
        }
        if (Keyboard.isKeyUp(Key.ESC)) {
            keyLocker.unlockKey(Key.ESC);
        }

        // if space is pressed, reset level. if escape is pressed, go back to main menu
        if (Keyboard.isKeyDown(Key.SPACE) && !keyLocker.isKeyLocked(Key.SPACE)) {
            screenCoordinator.setGameState(GameState.NEWLEVEL);
        } else if (Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC)) {
            screenCoordinator.setGameState(GameState.MENU);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.black);
        loseMessage.draw(graphicsHandler);
        instructions.draw(graphicsHandler);
    }
}