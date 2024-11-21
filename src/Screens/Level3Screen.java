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
import Maps.Level1ScreenMap;
import Maps.Level3ScreenMap;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;
import Utils.Direction;
import Level.IntroductionTextbox;
import Level.Level2Textbox;

public class Level3Screen extends Screen {

    protected ScreenCoordinator screenCoordinator;
    protected Player player;
    protected KeyLocker keyLocker = new KeyLocker();
    protected FlagManager flagManager;
    protected Map background;
    protected SpriteFont welcomeMessage;
    protected SpriteFont pressE;
    private Font maruMonica;

    public Level3Screen(ScreenCoordinator screenCoordinator) {
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
        
        background = new Level3ScreenMap();
        background.setAdjustCamera(false);
        background.preloadScripts();

        welcomeMessage = new SpriteFont("LEVEL 3: THE CASTLE", 200, 250, maruMonica.deriveFont(76f), Color.WHITE);
        welcomeMessage.setOutlineColor(Color.black);
        welcomeMessage.setOutlineThickness(6);

        pressE = new SpriteFont(("Press E to continue"), 300, 330, maruMonica.deriveFont(33f), Color.WHITE);
        pressE.setOutlineColor(Color.black);
        pressE.setOutlineThickness(5);
        // textbox = new IntroductionTextbox(background, screenCoordinator);
       
        keyLocker.lockKey(Key.E);
    }
    
    public void update() {
      // if X is pressed on menu item, change to appropriate screen based on which menu item was chosen
        if (Keyboard.isKeyUp(Key.E)) {
            keyLocker.unlockKey(Key.E);
        }
        if (!keyLocker.isKeyLocked(Key.E) && Keyboard.isKeyDown(Key.E)) {
                screenCoordinator.setGameState(GameState.NEWLEVEL);
        }
        // textbox.update();
        
    }
    
    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        welcomeMessage.draw(graphicsHandler);
        pressE.draw(graphicsHandler);
    }
}