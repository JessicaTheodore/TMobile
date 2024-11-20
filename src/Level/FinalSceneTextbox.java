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
import Maps.FinalSceneMap;
import SpriteFont.SpriteFont;
import Maps.IntroductionScreenMap;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class FinalSceneTextbox extends Textbox {

    private Map map;
    private Key interactKey = Key.E;
    private KeyLocker keyLocker = new KeyLocker();
    private SpriteFont introText;
    private Queue<SpriteFont> script;
    private Font maruMonica;
    protected ScreenCoordinator screenCoordinator;
    private FinalSceneMap finalsceneMap;

    public FinalSceneTextbox(Map map, ScreenCoordinator screenCoordinator) {
        super(map);
        this.screenCoordinator = screenCoordinator;
        this.map = map;
        this.finalsceneMap = (FinalSceneMap) map;
        keyLocker.lockKey(interactKey);
        initializeScript();
        
    }

    public void update() {
        int y = !map.getCamera().isAtBottomOfMap() ? bottomY : topY; 
       
        if (!script.isEmpty() && keyLocker.isKeyLocked(interactKey)) {
            introText = script.peek();
        } else if (script.isEmpty() && keyLocker.isKeyLocked(interactKey)) {
            screenCoordinator.setGameState(GameState.CREDITS);
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
        script.add(new SpriteFont("R: Welcome back Player. I was worried.", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap, false));
        script.add(new SpriteFont("Player: How'd I get back here so fast?", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap, true));
        script.add(new SpriteFont("R: Do not worry, boy. The spirits of the forest have rewarded \nyou.", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap, false));
        script.add(new SpriteFont("Player: Well, that's great, but I was looking forward to my \nhike. What don't you people get about that???", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("R: It's alright boy, you've saved all of humanity due to your \nbravery.", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("R: And even more, now that Hobbomock is gone I can finally \nreturn.", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Player: Sorry, what?", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("R: My name is not Park Ranger.", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("R: My name is Mishmash.", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Player: Bro I literally don't know who you people are", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Mishmash: I am the god of good and creation", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Player: Wait, weren't you dead or something?", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Mishmash: I was boy, weakeded and trapped in this human\n form. But you've freed me now.", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Mismash: This forest is in debt to you. Thank you.", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Mishmash: On behalf of the forest, we'll grant you one wish.", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Player: Any wish?", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Mishmash: If you wish for the greatest riches in the world, \nthen it will be yours boy.", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Player: Uh... I was thinking of something a little simpler, \nactually.", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Mishmash: Of course, whatever you want.", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Player: Yeah... can you, I don't know, make sure the caf \nfood isn't terrible?", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Player: Like actually tastes good?", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Mishmash: Well, I can certainly do that. But are you sure \nyou want to use your one wish from a god on food?", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Player: It'll change the lives of many.", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Mishmash: Very well then, it is done.", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Player: I'll get back on my hike then, considering the threat \nis gone now?", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Mishmash: Of course boy. Be on your way.", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap, false));
        script.add(new SpriteFont("Mishmash: May you carry this lesson with you too:", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Mishmash: Even the smallest action can shift the balance \nof the world", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
        script.add(new SpriteFont("Mishmash: Farewell and thank you for exploring the legend. ", x, y + 10, maruMonica.deriveFont(33f), Color.black, finalsceneMap));
    }
    
}