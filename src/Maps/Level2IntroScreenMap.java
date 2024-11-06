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
    public class Level2IntroScreenMap extends Map {

        private Sprite back;

        public Level2IntroScreenMap() {
            super("title_screen_map.txt", new CommonTileset());
            back = new Sprite(ImageLoader.loadSubImage("temporaryBackground.png", Colors.MAGENTA, 0, 0, 800, 600));
            back.setScale(1);
            //back.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
            back.setLocation(0, 0);

        }

        @Override
        public void draw(GraphicsHandler graphicsHandler) {
            super.draw(graphicsHandler);
            back.draw(graphicsHandler);
        }

    }