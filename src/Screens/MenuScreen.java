package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.Map;
import Maps.TitleScreenMap;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

// This is the class for the main menu screen
public class MenuScreen extends Screen {
    protected ScreenCoordinator screenCoordinator;
    protected int currentMenuItemHovered = 0; // current menu item being "hovered" over
    protected int menuItemSelected = -1;
    protected SpriteFont playGame;
    protected SpriteFont credits;
    protected SpriteFont controls;
    protected SpriteFont title;
    protected SpriteFont quitGame;
    protected Map background;
    protected int keyPressTimer;
    protected int pointerLocationX, pointerLocationY;
    protected KeyLocker keyLocker = new KeyLocker();

    private Font maruMonica;

    public MenuScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
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
            } catch (FontFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } 

        //text = new SpriteFont(currentTextItem.getText(), fontX, fontY, maruMonica.deriveFont(30f), Color.black);
        title = new SpriteFont("The Legend Of Hobbomock", 100, 55, maruMonica.deriveFont(65f), new Color(255,255,255));
        title.setOutlineColor(Color.black);
        title.setOutlineThickness(3);

        playGame = new SpriteFont("Play Game", 200, 253, maruMonica.deriveFont(30f), Color.BLACK);
        playGame.setOutlineColor(Color.white);
        playGame.setOutlineThickness(3);

        controls = new SpriteFont("Controls", 200, 323, maruMonica.deriveFont(30f), Color.white);
        controls.setOutlineColor(Color.black);
        controls.setOutlineThickness(3);

        quitGame = new SpriteFont("Close Game", 200, 393, maruMonica.deriveFont(30f), Color.white);
        quitGame.setOutlineColor(Color.black);
        quitGame.setOutlineThickness(3);

        credits = new SpriteFont("Credits", 200, 463, maruMonica.deriveFont(30f), Color.white);
        credits.setOutlineColor(Color.black);
        credits.setOutlineThickness(3);

        background = new TitleScreenMap();
        background.setAdjustCamera(false);
        keyPressTimer = 0;
        menuItemSelected = -1;
        keyLocker.lockKey(Key.SPACE);
    }

    public void update() {
        // update background map (to play tile animations)
        background.update(null);

        // if down or up is pressed, change menu item "hovered" over (blue square in front of text will move along with currentMenuItemHovered changing)
        if (Keyboard.isKeyDown(Key.S) && keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered++;
        } else if (Keyboard.isKeyDown(Key.W) && keyPressTimer == 0) {
            keyPressTimer = 14;
            currentMenuItemHovered--;
        } else {
            if (keyPressTimer > 0) {
                keyPressTimer--;
            }
        }

        // if down is pressed on last menu item or up is pressed on first menu item, "loop" the selection back around to the beginning/end
        if (currentMenuItemHovered > 3) {
            currentMenuItemHovered = 0;
        } else if (currentMenuItemHovered < 0) {
            currentMenuItemHovered = 3;
        }

        // sets location for blue square in front of text (pointerLocation) and also sets color of spritefont text based on which menu item is being hovered
        if (currentMenuItemHovered == 0) {
            playGame.setColor(Color.black);
            playGame.setOutlineColor(Color.white);
            controls.setColor(Color.white);
            controls.setOutlineColor(Color.black);
            credits.setColor(Color.white);
            credits.setOutlineColor(Color.black);
            quitGame.setColor(Color.white);
            quitGame.setOutlineColor(Color.black);
            pointerLocationX = 170;
            pointerLocationY = 260;
        } else if (currentMenuItemHovered == 1) {
            playGame.setColor(Color.white);
            playGame.setOutlineColor(Color.black);
            controls.setColor(Color.black);
            controls.setOutlineColor(Color.white);
            credits.setColor(Color.white);
            credits.setOutlineColor(Color.black);
            quitGame.setColor(Color.white);
            quitGame.setOutlineColor(Color.black);
            pointerLocationX = 170;
            pointerLocationY = 330;
        }else if(currentMenuItemHovered == 2){
            playGame.setColor(Color.white);
            playGame.setOutlineColor(Color.black);
            controls.setColor(Color.white);
            controls.setOutlineColor(Color.black);
            credits.setColor(Color.white);
            credits.setOutlineColor(Color.black);
            quitGame.setColor(Color.black);
            quitGame.setOutlineColor(Color.white);
            pointerLocationX = 170;
            pointerLocationY = 400;
        }else if(currentMenuItemHovered == 3){
            playGame.setColor(Color.white);
            playGame.setOutlineColor(Color.black);
            controls.setColor(Color.white);
            controls.setOutlineColor(Color.black);
            credits.setColor(Color.black);
            credits.setOutlineColor(Color.white);
            quitGame.setColor(Color.white);
            quitGame.setOutlineColor(Color.black);
            pointerLocationX = 170;
            pointerLocationY = 470;
        }

        // if space is pressed on menu item, change to appropriate screen based on which menu item was chosen
        if (Keyboard.isKeyUp(Key.SPACE)) {
            keyLocker.unlockKey(Key.SPACE);
        }
        if (!keyLocker.isKeyLocked(Key.E) && Keyboard.isKeyDown(Key.E)) {
            menuItemSelected = currentMenuItemHovered;
            if (menuItemSelected == 0) {
                //goes into the introduction screen dialogue
                screenCoordinator.setGameState(GameState.INTRO);
            } else if (menuItemSelected == 1) {
                screenCoordinator.setGameState(GameState.CONTROLSHOME);
            }else if(menuItemSelected == 2){
                System.exit(0);
            }else if(menuItemSelected == 3){
                screenCoordinator.setGameState(GameState.CREDITS);
            }

        }
    }
 
    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        playGame.draw(graphicsHandler);
        credits.draw(graphicsHandler);
        title.draw(graphicsHandler);
        quitGame.draw(graphicsHandler);
        controls.draw(graphicsHandler);
        graphicsHandler.drawFilledRectangleWithBorder(pointerLocationX, pointerLocationY, 20, 20, Color.white, Color.black, 2);
    }
    
}
