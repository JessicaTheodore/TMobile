package Level;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class IntroductionTextbox extends Textbox {

    private Map map;
    private Key interactKey = Key.E;
    private KeyLocker keyLocker = new KeyLocker();
    private SpriteFont introText;
    private Queue<SpriteFont> script;
    private Font maruMonica;

    public IntroductionTextbox(Map map) {
        super(map);
        this.map = map;
        keyLocker.lockKey(interactKey);
        initializeScript();
    }

    public void update() {
        int y = !map.getCamera().isAtBottomOfMap() ? bottomY : topY; 
       
        if (!script.isEmpty() && keyLocker.isKeyLocked(interactKey)) {
            introText = script.peek();
        }
        
        if (Keyboard.isKeyDown(interactKey) && !keyLocker.isKeyLocked(interactKey)) {
            
            keyLocker.lockKey(interactKey);
            script.poll();
        }
        else if (Keyboard.isKeyUp(interactKey)) {
            keyLocker.unlockKey(interactKey);
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        int y = !map.getCamera().isAtBottomOfMap() ? bottomY : topY; 
        
        if(introText != null) {
            introText.draw(graphicsHandler);
        }
    }

    private void initializeScript() {

         //importing font type
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

        int x = 45;
        int y = !map.getCamera().isAtBottomOfMap() ? bottomY : topY;
        
        script = new LinkedList<>();
        script.add(new SpriteFont("Park Ranger: Heading up the Giant, huh? ", x, y + 25, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("Player: Beautiful day for some hiking, I think.", x, y + 25, maruMonica.deriveFont(33f), Color.black));
    }
    
}