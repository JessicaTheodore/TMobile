package Maps;

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

    private Sprite cat;
    private Sprite back;

    public TitleScreenMap() {
        super("title_screen_map.txt", new CommonTileset());
        Point catLocation = getMapTile(8, 5).getLocation().subtractX(6).subtractY(7);
        cat = new Sprite(ImageLoader.loadSubImage("Hiker.png", Colors.MAGENTA, 0,128, 70, 70));
        cat.setScale(2);
        cat.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
        cat.setLocation(catLocation.x, catLocation.y); 
        back = new Sprite(ImageLoader.loadSubImage("title.png", Colors.MAGENTA, 0,50, 800, 600));
        back.setScale(1);
        back.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
        back.setLocation(0, 0);  
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        back.draw(graphicsHandler);
        cat.draw(graphicsHandler);
    }
}
