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
import Tilesets.MainTileset;
import Utils.Colors;
import Utils.Point;


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