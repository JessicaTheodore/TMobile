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

// Represents the game's textbox
// Will display the text it is given to its textQueue
// Each String in the textQueue will be displayed in the textbox, and hitting the interact key will cycle between additional Strings in the queue
// Use the newline character in a String in the textQueue to break the text up into a second line if needed
// Also supports adding options for a player to select from
public class Textbox {
    // whether textbox is shown or not
    protected boolean isActive;

    // textbox constants
    protected final int x = 22;
    protected final int bottomY = 460;
    protected final int topY = 22;
    protected final int fontX = 35;
    protected final int fontBottomY = 472;
    protected final int fontTopY = 34;
    protected final int width = 750;
    protected final int height = 100;

    // options textbox constants
    protected final int optionX = 680;
    protected final int optionBottomY = 350;
    protected final int optionTopY = 130;
    protected final int optionWidth = 92;
    protected final int optionHeight = 100;
    protected final int fontOptionX = 706;
    protected final int fontOptionBottomYStart = 365;
    protected final int fontOptionTopYStart = 145;
    protected final int fontOptionSpacing = 35;
    protected final int optionPointerX = 690;
    protected final int optionPointerYBottomStart = 378;
    protected final int optionPointerYTopStart = 158;

    // core vars that make textbox work
    private Queue<TextboxItem> textQueue;
    private TextboxItem currentTextItem;
    protected int selectedOptionIndex = 0;
    private SpriteFont text = null;
    private ArrayList<SpriteFont> options = null;
    private KeyLocker keyLocker = new KeyLocker();
    private Key interactKey = Key.E;

    private Map map;

    private Font maruMonica;

    /// CODE FOR DRAWING THE E IN THE CORNER STILL NOT DONE JUST IGNORE THIS THANKS :)
    // private String pressEToStart;
    // private SpriteFont pressEToStartSpriteFont;

    public Textbox(Map map) {
        this.map = map;
        this.textQueue = new LinkedList<>();
        /// CODE FOR DRAWING THE E IN THE CORNER STILL NOT DONE JUST IGNORE THIS THANKS :)
        //this.pressEToStart = "E to continue";
    }

    public void addText(String text) {
        if (textQueue.isEmpty()) {
            keyLocker.lockKey(interactKey);
        }
        textQueue.add(new TextboxItem(text));
    }

    public void addText(String[] text) {
        if (textQueue.isEmpty()) {
            keyLocker.lockKey(interactKey);
        }
        for (String textItem : text) {
            textQueue.add(new TextboxItem(textItem));
        }
    }

    public void addText(TextboxItem text) {
        if (textQueue.isEmpty()) {
            keyLocker.lockKey(interactKey);
        }
        textQueue.add(text);
    }

    public void addText(TextboxItem[] text) {
        if (textQueue.isEmpty()) {
            keyLocker.lockKey(interactKey);
        }
        for (TextboxItem textItem : text) {
            textQueue.add(textItem);
        }
    }

    /// CODE FOR DRAWING THE E IN THE CORNER STILL NOT DONE JUST IGNORE THIS THANKS :)

    // public void createPressEToStartSpriteFont() {
    //     Font maruMonica;
    //     try { 
    //         InputStream is = getClass().getResourceAsStream("/Level/font/x12y16pxMaruMonica.ttf");
    //         maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
    //         pressEToStartSpriteFont = new SpriteFont(pressEToStart, fontX, fontBottomY + 20, maruMonica, Color.black);
    //     } catch (FontFormatException e) {
    //         e.printStackTrace();
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }


    // returns whether the textQueue is out of items to display or not
    // useful for scripts to know when to complete
    public boolean isTextQueueEmpty() {
        return textQueue.isEmpty();
    }

    public void update() {
        // if textQueue has more text to display and the interact key button was pressed previously, display new text
        if (!textQueue.isEmpty() && keyLocker.isKeyLocked(interactKey)) {
            currentTextItem = textQueue.peek();
            options = null;

            // if camera is at bottom of screen, text is drawn at top of screen instead of the bottom like usual
            // to prevent it from covering the player
            int fontY = !map.getCamera().isAtBottomOfMap() ? fontBottomY : fontTopY;

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

            if (maruMonica != null) {
                // create text spritefont that will be drawn in textbox
                text = new SpriteFont(currentTextItem.getText(), fontX, fontY, maruMonica.deriveFont(30f), Color.black);
            } else {
                //fall back *just* in case the file doesn't work for some reason
                text = new SpriteFont(currentTextItem.getText(), fontX, fontY, "Arial", 22, Color.black);
            }

            // if there are options associated with this text item, prepare option spritefont text to be drawn in options textbox
            if (currentTextItem.getOptions() != null) {
                // if camera is at bottom of screen, text is drawn at top of screen instead of the bottom like usual
                // to prevent it from covering the player
                int fontOptionY = !map.getCamera().isAtBottomOfMap() ? fontOptionBottomYStart : fontOptionTopYStart;

                options = new ArrayList<>();
                // for each option, create option text spritefont that will be drawn in options textbox
                for (int i = 0; i < currentTextItem.getOptions().size(); i++) {
                    options.add(new SpriteFont(currentTextItem.options.get(i), fontOptionX, fontOptionY + (i *  fontOptionSpacing), "Arial", 30, Color.black));
                }
                selectedOptionIndex = 0;
            }

        }
        // if interact key is pressed, remove the current text from the queue to prepare for the next text item to be displayed
        if (Keyboard.isKeyDown(interactKey) && !keyLocker.isKeyLocked(interactKey)) {
            keyLocker.lockKey(interactKey);
            textQueue.poll();

            // if an option was selected, set output manager flag to the index of the selected option
            // a script can then look at output manager later to see which option was selected and do with that information what it wants
            if (options != null) {
                map.getActiveScript().getScriptActionOutputManager().addFlag("TEXTBOX_OPTION_SELECTION", selectedOptionIndex);
            }
        }
        else if (Keyboard.isKeyUp(interactKey)) {
            keyLocker.unlockKey(interactKey);
        }

        if (options != null) {
            if (Keyboard.isKeyDown(Key.DOWN)) {
                if (selectedOptionIndex < options.size() - 1) {
                    selectedOptionIndex++;
                }
            }
            if (Keyboard.isKeyDown(Key.UP)) {
                if (selectedOptionIndex > 0) {
                    selectedOptionIndex--;
                }
            }
        }
    }

    public void draw(GraphicsHandler graphicsHandler) {
        // draw textbox
        // if camera is at bottom of screen, textbox is drawn at top of screen instead of the bottom like usual
        // to prevent it from covering the player
        int y = !map.getCamera().isAtBottomOfMap() ? bottomY : topY;
        Color transparency = new Color(255, 255, 255, 180);
        graphicsHandler.drawFilledRectangleWithBorder(x, y, width, height, transparency, Color.darkGray, 3);

        if (text != null) {
            // draw text in textbox
            text.drawWithParsedNewLines(graphicsHandler, 10);
            

            /// CODE FOR DRAWING THE E IN THE CORNER STILL NOT DONE JUST IGNORE THIS THANKS :)
            // if (pressEToStartSpriteFont != null) {
            //     pressEToStartSpriteFont.draw(graphicsHandler);
            // }
            
            if (options != null) {
                // draw options textbox
                // if camera is at bottom of screen, textbox is drawn at top of screen instead of the bottom like usual
                // to prevent it from covering the player
                int optionY = !map.getCamera().isAtBottomOfMap() ? optionBottomY : optionTopY;
                graphicsHandler.drawFilledRectangleWithBorder(optionX, optionY, optionWidth, optionHeight, Color.white, Color.black, 2);

                // draw each option text
                for (SpriteFont option : options) {
                    option.draw(graphicsHandler);
                }

                // the start y location of the option pointer depends on whether the options textbox is on top or bottom of screen
                int optionPointerYStart = !map.getCamera().isAtBottomOfMap() ? optionPointerYBottomStart : optionPointerYTopStart;
                // draw option selection indicator (small black rectangle)
                graphicsHandler.drawFilledRectangle(optionPointerX, optionPointerYStart + (selectedOptionIndex * fontOptionSpacing), 10, 10, Color.black);
            }
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setInteractKey(Key interactKey) {
        this.interactKey = interactKey;
    }

}
