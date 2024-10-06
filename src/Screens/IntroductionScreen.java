package Screens;

import java.awt.Color;

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




public class IntroductionScreen extends Screen {

    protected ScreenCoordinator screenCoordinator;
    protected Player player;
    protected KeyLocker keyLocker = new KeyLocker();
    protected FlagManager flagManager;
    protected Map background;
    protected SpriteFont credits;
    protected Textbox textbox;

    public IntroductionScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        credits = new SpriteFont("Press Space to continue", 320, 430, "Times new roman", 16, new Color(49, 207, 240));
        credits.setOutlineColor(Color.black);
        credits.setOutlineThickness(3);

        background = new IntroductionScreenMap();
        background.setAdjustCamera(false);
        
        background.preloadScripts();

        textbox = new Textbox(background);

        keyLocker.lockKey(Key.SPACE);
    }
    
    public void update() {
      // if space is pressed on menu item, change to appropriate screen based on which menu item was chosen
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (!keyLocker.isKeyLocked(Key.SPACE) && Keyboard.isKeyDown(Key.SPACE)) {
                screenCoordinator.setGameState(GameState.LEVEL);
        }
    }
    
    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        credits.draw(graphicsHandler);
        textbox.draw(graphicsHandler);
    }
}