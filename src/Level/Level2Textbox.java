package Level;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import Maps.Level2ScreenMap;
import Screens.IntroductionScreen;
import SpriteFont.SpriteFont;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Level2Textbox extends Textbox {

    private Map map;
    private Key interactKey = Key.E;
    private KeyLocker keyLocker = new KeyLocker();
    private SpriteFont introText;
    private Queue<SpriteFont> script;
    private Font maruMonica;
    protected ScreenCoordinator screenCoordinator;
    private Level2ScreenMap level2Map;

    public Level2Textbox(Map map, ScreenCoordinator screenCoordinator) {
        super(map);
        this.screenCoordinator = screenCoordinator;
        this.map = map;
        this.level2Map = (Level2ScreenMap) map;
        keyLocker.lockKey(interactKey);
        initializeScript();
    }

    public void update() {
        int y = !map.getCamera().isAtBottomOfMap() ? bottomY : topY; 
       
        if (!script.isEmpty() && keyLocker.isKeyLocked(interactKey)) {
            introText = script.peek();
        } else if (script.isEmpty() && keyLocker.isKeyLocked(interactKey)) {
            screenCoordinator.setGameState(GameState.LEVEL2INTROSCREEN);
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

        script.add(new SpriteFont("Trees: *whispering*", x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map, false));
        script.add(new SpriteFont("%s: What was that??", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, false, level2Map, true));
        script.add(new SpriteFont("Trees: *...Hobbomock awakes...*", x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map, false));
        script.add(new SpriteFont("Trees: *...The hiker thinks he's brave... but the giant \nknows...*", x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map, false));
        script.add(new SpriteFont("%s: Are those trees... talking?",true, x, y + 10, maruMonica.deriveFont(33f), Color.black, false, level2Map, true));
        
        //PARK RANGER APPEARS AFTER THIS
        script.add(new SpriteFont("R: So %s, you're finally realizing what's happening, \naren't you?", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("%s: Ah! Where'd you come from?", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map, true));
        script.add(new SpriteFont("R: I followed you... didn't think you had it in you to continue\n up the path. Most would've turned around by now.", x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("%s: ...Thank you?", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map, true));
        script.add(new SpriteFont("R: Besides, I figured you'd be better equipped for what's \nto come if I filled you in. Strange things are-", x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("%s: -happening on this mountain. Blah blah, I know. \nI figured it out when the deer-looking bears attacked me.", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map, true));
        script.add(new SpriteFont("R: These trees speak for the giant... they were here \nwhen Hobbomock was first put to sleep.", x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("Trees: *His spirit lingers...*", x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map));
        script.add(new SpriteFont("R: A long time ago, when the Quinnipiac people still existed, \nHobbomock lived.", x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("R: You see, Hobbomock was an evil spirit, wreaking havoc \non the land.", x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("R: The Quinnipiac people turned to help from Mishmash, \nthe god of good fortune.", x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("R: Through a great deal of effort were able to put him \nto sleep.", x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("Trees: *The spirit of balance...*", x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map));
        script.add(new SpriteFont("%s: Well, why don't we just summon this god and have \nthem deal with it?", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map, true));
        script.add(new SpriteFont("R: Mishmash is gone, %s", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("%s: Gone?", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map, true));
        script.add(new SpriteFont("R: But you could put the giant back to sleep.", x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("%s: Well.. I don't think I want to deal with all of that.", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map, true));
        script.add(new SpriteFont("R: Too late %s. The mountain knows you're here. \nAnd once it knows, you can't leave.", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("Trees: *Hobbomock knows...*", x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map));
        script.add(new SpriteFont("%s: I can't leave??", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map, true));
        script.add(new SpriteFont("R: Unless you put the giant to sleep.", x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("%s: You want me to put an evil mountain spirit back to \nsleep? I'm just a hiker, man", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map, true));
        script.add(new SpriteFont("R: It's not that simple %s. Hobbomock is the land's spirit- \nhe demands respect.", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("R: I haven't been the one upsetting the legend", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("%s: Who has then?", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map, true));
        script.add(new SpriteFont("R: It's not difficult to pinpoint.", x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("R: Students like you take the Quinnipiac people's\n name and land and trounce over Hobbomock's spirit", x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("R: without an ounce of respect or understanding\n towards the stories it holds.", x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("R: Hobbomock can only be put to sleep by someone who \nregains his trust. And that can only be you.", x, y + 10, maruMonica.deriveFont(33f), Color.black));
        script.add(new SpriteFont("Trees: *Hobbomock chooses you...*", x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map));
        script.add(new SpriteFont("%s: ...", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map, true));
        script.add(new SpriteFont("%s: Fine. I'll put Hobbomock back to sleep.", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map, true));
        script.add(new SpriteFont("R: Thank you %s, that's brave of you. You see that trail \nright there?", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("%s: Yeah.", true, x, y + 10, maruMonica.deriveFont(33f), Color.black, level2Map, true));
        script.add(new SpriteFont("R: That'll take you to the spirit. Follow the path. Avoid the \ntrees.", x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("R: And most importantly, remember the mountain is alive.", x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
        script.add(new SpriteFont("R: Respect it.", x, y + 10, maruMonica.deriveFont(33f), Color.black, true, level2Map, false));
    }
    
}