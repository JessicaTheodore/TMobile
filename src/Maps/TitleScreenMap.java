package Maps;

import java.awt.Frame;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.ImageEffect;
import GameObject.Sprite;
import Level.Map;
import Tilesets.CommonTileset;
import Utils.Colors;
import Utils.Point;

// Represents the map that is used as a background for the main menu and credits menu screen
public class TitleScreenMap extends Map {

    // private Sprite cat;
    private Sprite back;
    // private Sprite topText;
    // private Sprite logo;

    public TitleScreenMap() {
        super("title_screen_map.txt", new CommonTileset());

        // Player sprite

        // Point catLocation = getMapTile(8, 5).getLocation().subtractX(6).subtractY(7);
        // cat = new Sprite(ImageLoader.loadSubImage("Hiker.png", Colors.MAGENTA, 0,128, 70, 70));
        // cat.setScale(2);
        // cat.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
        // cat.setLocation(catLocation.x, catLocation.y); 

        // Background image
        back = new Sprite(ImageLoader.loadSubImage("newBackground.png", 0,0, 540, 370));
        back.setScale(2);
        back.setLocation(0, -60);      

        // // Logo text 
        // logo = new Sprite(ImageLoader.loadSubImage("TLOH.png",0,0,150,39));
        // logo.setScale(5);
        // logo.setLocation(0,40);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        back.draw(graphicsHandler);
        // logo.draw(graphicsHandler);
        // cat.draw(graphicsHandler);
    }
}
