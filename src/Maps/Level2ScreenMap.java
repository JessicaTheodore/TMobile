package Maps;

import java.util.ArrayList;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.ImageEffect;
import GameObject.Sprite;
import Level.Map;
import Level.Trigger;
import Scripts.Level1.IntroScript;
import Tilesets.CommonTileset;
import Utils.Colors;
import Utils.Point;


  // Represents the map that is used as a background for the introduction screen
    public class Level2ScreenMap extends Map {

        private Sprite back;
        private Sprite hiker;
        private Sprite ranger;

        public Level2ScreenMap() {
            super("title_screen_map.txt", new CommonTileset());
            back = new Sprite(ImageLoader.loadSubImage("temporaryBackground.png", Colors.MAGENTA, 0, 0, 800, 600));
            back.setScale(1);
            //back.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
            back.setLocation(0, 0);

            Point hikerLocation = getMapTile(3, 6).getLocation().subtractX(6).subtractY(7);
            hiker = new Sprite(ImageLoader.loadSubImage("Hiker.png", Colors.MAGENTA, 0,128, 70, 70));
            hiker.setScale(3);
            hiker.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
            hiker.setLocation(hikerLocation.x, hikerLocation.y); 

            Point rangerLocation = getMapTile(10, 6).getLocation().subtractX(6).subtractY(7);
            //temporarily will be the walrus image
            ranger = new Sprite(ImageLoader.loadSubImage("Ranger.png", Colors.MAGENTA, 0, 128, 70, 70));
            ranger.setScale(3);
            ranger.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
            ranger.setLocation(rangerLocation.x, rangerLocation.y - 20); 

        }

        @Override
        public void draw(GraphicsHandler graphicsHandler) {
            super.draw(graphicsHandler);
            back.draw(graphicsHandler);
            hiker.draw(graphicsHandler);
            ranger.draw(graphicsHandler);
         
        }

    }