package Screens;

import Engine.*;
import Game.ScreenCoordinator;
import Level.FlagManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PauseScreen {

    protected BufferedImage helpMenu;
    protected FlagManager flagManager;
    protected boolean helpScreenOn = false;
    protected KeyLocker keyLocker = new KeyLocker();
    private PlayLevelScreen playLevelScreen ;

    public PauseScreen(FlagManager flagManager){
        this.flagManager = flagManager;

        helpMenu = ImageLoader.load("PauseScreen.png");
    }
    
    
    public void changeStatus(){
        helpScreenOn = !helpScreenOn;
    }

    public void draw(GraphicsHandler graphicsHandler){
        if(helpScreenOn){
            playLevelScreen.drawMap(graphicsHandler);
            graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), Color.pink);
            graphicsHandler.drawImage((helpMenu), 0, 0, 800, 550);
        }
    }
}
