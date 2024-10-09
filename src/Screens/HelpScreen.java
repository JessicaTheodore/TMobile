package Screens;

import Engine.*;
import Level.FlagManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HelpScreen {

    protected BufferedImage helpMenu;
    protected FlagManager flagManager;
    protected boolean helpScreenOn = false;
    protected KeyLocker keyLocker = new KeyLocker();

    public HelpScreen(FlagManager flagManager){
        this.flagManager = flagManager;

        helpMenu = ImageLoader.load("HelpScreen.png");
    }
    
    
    public void changeStatus(){
        helpScreenOn = !helpScreenOn;
    }

    public void draw(GraphicsHandler graphicsHandler){
        if(helpScreenOn){
            graphicsHandler.drawFilledRectangle(0, 0, ScreenManager.getScreenWidth(), ScreenManager.getScreenHeight(), null);
            graphicsHandler.drawImage((helpMenu), 00, 00, 800, 600);
        }
    }
}
