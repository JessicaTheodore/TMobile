package Maps;


import Engine.GraphicsHandler;
import GameObject.Sprite;
import Level.Map;
import Tilesets.MainTileset;


  // Represents the map that is used as a background for the introduction screen
    public class PauseScreenMap extends Map {

        private Sprite back;

        public PauseScreenMap() {
            super("PauseScreenMap.txt", new MainTileset());
            

        }

        @Override
        public void draw(GraphicsHandler graphicsHandler) {
            super.draw(graphicsHandler);
            //back.draw(graphicsHandler);
         
        }

    }