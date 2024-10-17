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
    protected SpriteFont controls;
    protected SpriteFont exit;
    protected SpriteFont restart;
    protected SpriteFont menu;
    protected int curr = 0;
    protected int chosen;
    private int timer;
    private int xLoc;
    private int yLoc;

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
        words = new SpriteFont("Pause Screen",180, 50, maruMonica.deriveFont(100f), new Color(255, 255, 255));
        words.setOutlineColor(Color.black);
        words.setOutlineThickness(3);

        controls = new SpriteFont("Controls",330, 200, maruMonica.deriveFont(40f), new Color(255, 255, 255));
        controls.setOutlineColor(Color.black);
        controls.setOutlineThickness(3);

        exit = new SpriteFont("Resume",330, 260, maruMonica.deriveFont(40f), new Color(255, 255, 255));
        exit.setOutlineColor(Color.black);
        exit.setOutlineThickness(3);

        restart = new SpriteFont("Restart",330, 320, maruMonica.deriveFont(40f), new Color(255, 255, 255));
        restart.setOutlineColor(Color.black);
        restart.setOutlineThickness(3);

        menu = new SpriteFont("Menu",330, 380, maruMonica.deriveFont(40f), new Color(255, 255, 255));
        menu.setOutlineColor(Color.black);
        menu.setOutlineThickness(3);

        timer = 0;

        background = new PauseScreenMap();
        background.setAdjustCamera(false);
        
        keyLocker.lockKey(Key.ESC);

        
    }

    public void changeStatus(){
        pauseScreenOn = !pauseScreenOn;
    }

    @Override
    public void update() {
        if(Keyboard.isKeyDown(Key.S) && timer == 0){
            timer = 14;
            curr++;
        }else if(Keyboard.isKeyDown(Key.W) && timer == 0){
            timer = 14;
            curr--;
        }else{
            if(timer > 0){
                timer--;
            }
        }

        if(curr > 3){
            curr = 0;
        }else if(curr < 0){
            curr = 3;
        }

        if(curr == 0){
            controls.setColor(new Color(0, 0, 0));
            controls.setOutlineColor(new Color(255, 255, 255));
            exit.setColor(new Color(255, 255, 255));
            exit.setOutlineColor(new Color(0, 0, 0));
            restart.setColor(new Color(255, 255, 255));
            restart.setOutlineColor(new Color(0, 0, 0));
            menu.setColor(Color.white);
            menu.setOutlineColor(Color.black);
            xLoc = 300;
            yLoc = 212;
        }else if(curr == 1){
            exit.setColor(new Color(0, 0, 0));
            controls.setColor(new Color(255, 255, 255));
            exit.setOutlineColor(new Color(255, 255, 255));
            controls.setOutlineColor(new Color(0, 0, 0));
            restart.setColor(new Color(255, 255, 255));
            restart.setOutlineColor(new Color(0, 0, 0));
            menu.setColor(Color.white);
            menu.setOutlineColor(Color.black);
            xLoc = 300;
            yLoc = 272;
        }else if(curr == 2){
            exit.setColor(new Color(255, 255, 255));
            controls.setColor(new Color(255, 255, 255));
            exit.setOutlineColor(new Color(0, 0, 0));
            controls.setOutlineColor(new Color(0, 0, 0));
            restart.setColor(new Color(0, 0, 0));
            restart.setOutlineColor(new Color(255, 255, 255));
            menu.setColor(Color.white);
            menu.setOutlineColor(Color.black);
            xLoc = 300;
            yLoc = 332;
        }
        else if(curr == 3){
            exit.setColor(new Color(255, 255, 255));
            controls.setColor(new Color(255, 255, 255));
            exit.setOutlineColor(new Color(0, 0, 0));
            controls.setOutlineColor(new Color(0, 0, 0));
            restart.setColor(Color.white);
            restart.setOutlineColor(Color.black);
            menu.setColor(Color.black);
            menu.setOutlineColor(Color.white);
            xLoc = 300;
            yLoc = 392;
        }

        if(Keyboard.isKeyUp(Key.ESC)){
            keyLocker.unlockKey(Key.ESC);
        } 
        
        if (!keyLocker.isKeyLocked(Key.E) && Keyboard.isKeyDown(Key.E)) {
            chosen = curr;
            if (chosen == 0) {
                //goes into the introduction screen dialogue
                screenCoordinator.setGameState(GameState.CONTROLS);
            } else if (chosen == 1) {
                screenCoordinator.setGameState(GameState.LEVEL);
            }else if (chosen == 2) {
                screenCoordinator.setGameState(GameState.LEVEL);
            }else if (chosen == 3) {
                screenCoordinator.setGameState(GameState.MENU);
            }
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
        controls.draw(graphicsHandler);
        exit.draw(graphicsHandler);
        restart.draw(graphicsHandler);
        menu.draw(graphicsHandler);
        graphicsHandler.drawFilledRectangleWithBorder(xLoc, yLoc, 20, 20, new Color(255, 255, 255), Color.black, 2);
    }
}




