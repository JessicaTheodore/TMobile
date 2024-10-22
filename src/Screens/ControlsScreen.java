package Screens;

import Engine.*;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.ImageEffect;
import GameObject.Sprite;
import Level.FlagManager;
import Maps.PauseScreenMap;
import SpriteFont.SpriteFont;
import Utils.Colors;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import Level.Map;

public class ControlsScreen extends Screen{

    protected ScreenCoordinator screenCoordinator;
    private Font maruMonica;
    protected KeyLocker keyLocker = new KeyLocker();
    private SpriteFont[] controls = new SpriteFont[9];
    private SpriteFont controlTitle;
    protected Map background;
    protected SpriteFont exit;
    protected SpriteFont e;
    protected Sprite back;
    private int timer = 0;

    public ControlsScreen(ScreenCoordinator screenCoordinator){
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
        keyLocker.lockKey(Key.E);
        controlTitle = new SpriteFont("Contols",310, 25, maruMonica.deriveFont(70f), new Color(255, 255, 255));
        controlTitle.setOutlineColor(Color.black);
        controlTitle.setOutlineThickness(3);

        exit = new SpriteFont("Exit",730, 525, maruMonica.deriveFont(40f), new Color(0, 0, 0));
        exit.setOutlineColor(Color.WHITE);
        exit.setOutlineThickness(3);

        e = new SpriteFont("E", 700, 535, maruMonica.deriveFont(17f), Color.YELLOW);
        e.setOutlineColor(Color.black);
        e.setOutlineThickness(3);
        
        controls[0] = new SpriteFont("W: Move Up",340, 130, maruMonica.deriveFont(30f), new Color(255, 255, 255));
        controls[1] = new SpriteFont("A: Move Left",335, 170, maruMonica.deriveFont(30f), new Color(255, 255, 255));
        controls[2] = new SpriteFont("S: Move Down",325, 210, maruMonica.deriveFont(30f), new Color(255, 255, 255));
        controls[3] = new SpriteFont("D: Move Right",328, 250, maruMonica.deriveFont(30f), new Color(255, 255, 255));
        controls[4] = new SpriteFont("E: Interact",345, 290, maruMonica.deriveFont(30f), new Color(255, 255, 255));
        controls[5] = new SpriteFont("H: Help Screen",322, 330, maruMonica.deriveFont(30f), new Color(255, 255, 255));
        controls[6] = new SpriteFont("ESC: Pasue / Exit",310, 370, maruMonica.deriveFont(30f), new Color(255, 255, 255));
        controls[7] = new SpriteFont("L Shift: Ranged Attack",283, 410, maruMonica.deriveFont(30f), new Color(255, 255, 255));
        controls[8] = new SpriteFont("Space: Mele Attack",300, 450, maruMonica.deriveFont(30f), new Color(255, 255, 255));
        for(int i = 0; i < controls.length; i++){
            controls[i].setOutlineColor(Color.black);
            controls[i].setOutlineThickness(3);
        }

        background = new PauseScreenMap();
        background.setAdjustCamera(false);

        keyLocker.lockKey(Key.ESC);
        keyLocker.lockKey(Key.E);

        back = new Sprite(ImageLoader.loadSubImage("Intro.png", Colors.MAGENTA, 0, 0, 800, 600));
        back.setScale(1);
        back.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
        back.setLocation(0, 0);

    }

    @Override
    public void update() {
        if(Keyboard.isKeyUp(Key.ESC)){
            keyLocker.unlockKey(Key.ESC);
        }  
        if(Keyboard.isKeyUp(Key.E)){
            keyLocker.unlockKey(Key.E);
        }        
        if(Keyboard.isKeyDown(Key.ESC) && !keyLocker.isKeyLocked(Key.ESC) && timer == 0){
            screenCoordinator.setGameState(GameState.PAUSE);
            keyLocker.lockKey(Key.ESC);
        }else if(Keyboard.isKeyDown(Key.E) && !keyLocker.isKeyLocked(Key.E) && timer == 0){
            screenCoordinator.setGameState(GameState.PAUSE);
            keyLocker.lockKey(Key.E);
        }else{
            if(timer > 0){
                timer --;
            }
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        background.draw(graphicsHandler);
        back.draw(graphicsHandler);
        controlTitle.draw(graphicsHandler);
        for(int i = 0; i < controls.length; i++){
            controls[i].draw(graphicsHandler);
        }
        exit.draw(graphicsHandler);
        graphicsHandler.drawFilledRectangleWithBorder(695, 534, 20, 20, Color.WHITE, Color.black, 2);
        e.draw(graphicsHandler);
    }
    
}
