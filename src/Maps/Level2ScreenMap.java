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
        private boolean showRanger = true;
        private Sprite playerSpeaking;
        private Sprite rangerSpeaking;
        private boolean playerIsSpeaking = true;
        private boolean treesAreSpeaking = true;

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
            ranger = new Sprite(ImageLoader.loadSubImage("Ranger.png", Colors.MAGENTA, 0, 128, 70, 70));
            ranger.setScale(3);
            ranger.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
            ranger.setLocation(rangerLocation.x, rangerLocation.y - 20); 

            playerSpeaking = new Sprite(ImageLoader.loadSubImage("pointer.png", Colors.MAGENTA, 0, 0, 24, 24));
            playerSpeaking.setScale(1);
            playerSpeaking.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
            playerSpeaking.setLocation(hikerLocation.x + 45, hikerLocation.y - 15); 

            rangerSpeaking = new Sprite(ImageLoader.loadSubImage("pointer.png", Colors.MAGENTA, 0, 0, 24, 24));
            rangerSpeaking.setScale(1);
            rangerSpeaking.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
            rangerSpeaking.setLocation(rangerLocation.x + 22, rangerLocation.y - 26); 

        }

        public void setShowRanger(boolean flag) {
            showRanger = flag;
        }

        public void setPlayerSpeaking(boolean flag) {
            playerIsSpeaking = flag;
        }

        public void setTreesSpeaking(boolean flag) {
            treesAreSpeaking = flag;
        }

        @Override
        public void draw(GraphicsHandler graphicsHandler) {
            super.draw(graphicsHandler);
            back.draw(graphicsHandler);
            hiker.draw(graphicsHandler);
            //for the ranger to pop up on screen
            if (showRanger) {
                ranger.draw(graphicsHandler);
            }

            if (!treesAreSpeaking) {
                if (playerIsSpeaking) {
                    playerSpeaking.draw(graphicsHandler);
                } else {
                    rangerSpeaking.draw(graphicsHandler);
                }
            }
            
        }
    }