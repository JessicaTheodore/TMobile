package Maps;


import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Sprite;
import Level.Map;
import Tilesets.CommonTileset;
import Utils.Colors;


  // Represents the map that is used as a background for the introduction screen
    public class Level2IntroScreenMap extends Map {

        private Sprite back;

        public Level2IntroScreenMap() {
            super("title_screen_map.txt", new CommonTileset());
            back = new Sprite(ImageLoader.loadSubImage("Level2DialogueBackground.png", Colors.MAGENTA, 0, 0, 800, 600));
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