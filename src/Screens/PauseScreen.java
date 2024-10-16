package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Sprite;
import Level.FlagManager;
import Maps.PauseScreenMap;
import SpriteFont.SpriteFont;
import Utils.Colors;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import Level.Map;

public class PauseScreen extends Screen{

    protected BufferedImage pauseMenu;
    protected SpriteFont words;
    protected FlagManager flagManager;
    protected boolean pauseScreenOn = false;
    protected KeyLocker keyLocker = new KeyLocker();
    private PlayLevelScreen playLevelScreen ;
    protected ScreenCoordinator screenCoordinator;
    private Sprite pauseSprite;
    protected Map background;

    private Font maruMonica;


    public PauseScreen(ScreenCoordinator screenCoordinator){
        this.screenCoordinator = screenCoordinator;

        pauseMenu = ImageLoader.load("PauseScreen.png");
        pauseSprite = (new Sprite(ImageLoader.loadSubImage("PauseScreen.png", Colors.MAGENTA, 0, 0, 185, 128)));
        pauseSprite.setScale(3);
        pauseSprite.setLocation(130, 100);  
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
        keyLocker.lockKey(Key.ESC);
        words = new SpriteFont("Pause Screen",180, 203, maruMonica.deriveFont(100f), new Color(255, 255, 255));
        words.setOutlineColor(Color.black);
        words.setOutlineThickness(3);

        background = new PauseScreenMap();
        background.setAdjustCamera(false);
        
        keyLocker.lockKey(Key.ESC);

        
    }

    public void changeStatus(){
        pauseScreenOn = !pauseScreenOn;
    }

    @Override
    public void update() {
        if(Keyboard.isKeyUp(Key.ESC)){
            keyLocker.unlockKey(Key.ESC);
        }        
        if(Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC)){
            screenCoordinator.setGameState(GameState.LEVEL);
            keyLocker.lockKey(Key.ESC);
        }
    }

    public void draw(GraphicsHandler graphicsHandler){
        background.draw(graphicsHandler);
        //pauseSprite.draw(graphicsHandler);
        //playLevelScreen.drawMap(graphicsHandler);
        //graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.pink);
        //graphicsHandler.drawImage((pauseMenu), 0, 0, 800, 550);
        words.draw(graphicsHandler);
    }
}




