package Level;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class IntroductionTextbox extends Textbox {

    private Map map;
    private Key interactKey = Key.E;
    private KeyLocker keyLocker = new KeyLocker();
    private SpriteFont introText;
    private Queue<SpriteFont> script;

    public IntroductionTextbox(Map map) {
        //call constructor of parent class
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
        // if interact key is pressed, remove the current text from the queue to prepare for the next text item to be displayed
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

        SpriteFont pressE = new SpriteFont("press E", x + 200, y + height -65, new Font("TimesRoman", Font.PLAIN, 12), Color.black);
        pressE.draw(graphicsHandler);
    }

    private void initializeScript() {
        int x=100;
        int y = !map.getCamera().isAtBottomOfMap() ? bottomY : topY;
       
        
        script = new LinkedList<>();
        script.add(new SpriteFont("Park Ranger: Heading up the Giant, huh? ", x, y, "Arial", 22, Color.black));
        script.add(new SpriteFont("Player: Beautiful day for some hiking, I think.", x, y, "Arial", 22, Color.black));
    }
    
}