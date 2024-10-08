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
            introText.drawWithParsedNewLines(graphicsHandler, 5);
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
        script.add(new SpriteFont("Park Ranger: Heading up the Giant, huh?", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("Player: Beautiful day for some hiking, I think.", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("R: …Hiking? Say, the park closes in 3 hours.", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("R: You won't make it back in time, why don't you come\n back another day?", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("Player: *Laughs.* I've hiked harder trails faster than\n that. Thanks, but I'll be fine.", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("R: Listen, I'll be frank with you, boy. Strange things are\n happening on that mountain.", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("R: It's not wise to head up the Giant now. ", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("Player: Strange things?", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("R: Rumors have been spreading by those who make it off\n the mountain… ", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("R: Grizzlies with glowing eyes, whispering tree attacking\n those who come too close…", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("R: I've even seen some just like you go up and never come\n back.", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("R: Some say the legend is to blame.", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("Player: The legend?", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("R: … ", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("R: The Legend of Hobbomock.", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("Player: …Who?", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("R: That's precisely the point, boy.\n You're messing with things you don't understand.", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("R: Stories that are coming to life… \n Spirits that are waking up. ", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("Player: Whatever man- people have made up crazier\n rumors on campus.", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("Player: I heard something about a pipe bomb last week?", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("Player: Besides, I'm going on my hike, some random\n warnings won't stop me. ", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("R: Say, what's your name boy?", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("R: Ok ____ ... you seem like a nice kid.\n Take this, it could come in handy.", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("R: If you can keep your wits about you, go ahead and\n climb the Giant…", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("R: Ignore the warnings and the legends.", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("R: Just don't say I didn't warn you.", x, y + 10, maruMonica.deriveFont(33f), Color.black));

    }
    
}