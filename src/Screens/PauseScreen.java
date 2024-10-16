package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import Level.FlagManager;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class PauseScreen extends Screen{

    protected BufferedImage pauseMenu;
    protected SpriteFont words;
    protected FlagManager flagManager;
    protected boolean pauseScreenOn = false;
    protected KeyLocker keyLocker = new KeyLocker();
    private PlayLevelScreen playLevelScreen ;
    protected ScreenCoordinator screenCoordinator;

    private Font maruMonica;


    public PauseScreen(ScreenCoordinator screenCoordinator){
        this.screenCoordinator = screenCoordinator;

        pauseMenu = ImageLoader.load("PauseScreen.png");
    }
    
    
    public void changeStatus(){
        pauseScreenOn = !pauseScreenOn;
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
        System.out.println("pause screen");
        keyLocker.lockKey(Key.ESC);
        words = new SpriteFont("Play Game", 200, 253, maruMonica.deriveFont(30f), new Color(49, 207, 240));
        words.setOutlineColor(Color.black);
        words.setOutlineThickness(3);
        
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    


    @Override
    public void update() {
        keyLocker.unlockKey(Key.ESC);
        if(Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC)){
            screenCoordinator.setGameState(GameState.LEVEL);
            keyLocker.lockKey(Key.ESC);
        }
    }

    public void draw(GraphicsHandler graphicsHandler){
        if(pauseScreenOn){
            playLevelScreen.drawMap(graphicsHandler);
            graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.pink);
            graphicsHandler.drawImage((pauseMenu), 0, 0, 800, 550);
            words.draw(graphicsHandler);
        }
    }
}




