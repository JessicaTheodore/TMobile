package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.FlagManager;
import Level.Map;
import Level.Player;
import Level.Textbox;
import Maps.IntroductionScreenMap;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;
import Utils.Direction;
import Level.IntroductionTextbox;

public class IntroductionScreen extends Screen {

    protected ScreenCoordinator screenCoordinator;
    protected Player player;
    protected KeyLocker keyLocker = new KeyLocker();
    protected FlagManager flagManager;
    protected Map background;
    protected SpriteFont credits;
    protected IntroductionTextbox textbox;
    private Font maruMonica;

    public IntroductionScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {

        //this is for the font, probably would be better to extend it as a file instead of having to
        //do the try/catch block the everytime but as of rn it'll do :D
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

        credits = new SpriteFont("Press X to continue", 330, 433, maruMonica.deriveFont(16f), new Color(49, 207, 240));
        credits.setOutlineColor(Color.black);
        credits.setOutlineThickness(3);

        background = new IntroductionScreenMap();
        background.setAdjustCamera(false);
        
        background.preloadScripts();

        textbox = new IntroductionTextbox(background);

        keyLocker.lockKey(Key.X);
    }
    
    public void update() {
      // if space is pressed on menu item, change to appropriate screen based on which menu item was chosen
        if (Keyboard.isKeyUp(Key.X)) {
            keyLocker.unlockKey(Key.X);
        }
        if (!keyLocker.isKeyLocked(Key.X) && Keyboard.isKeyDown(Key.X)) {
                screenCoordinator.setGameState(GameState.LEVEL);
        }
        textbox.update();
    }
    
    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        credits.draw(graphicsHandler);
        textbox.draw(graphicsHandler);
    }
}