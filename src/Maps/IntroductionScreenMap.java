package Maps;


import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.ImageEffect;
import GameObject.Sprite;
import Level.Map;
import Tilesets.CommonTileset;
import Utils.Colors;
import Utils.Point;


  // Represents the map that is used as a background for the introduction screen
    public class IntroductionScreenMap extends Map {

        private Sprite back;
        private Sprite hiker;
        private Sprite ranger;
        private boolean playerIsSpeaking1 = true;
        private Sprite playerSpeaking;
        private Sprite rangerSpeaking;

        public IntroductionScreenMap() {
            super("title_screen_map.txt", new CommonTileset());
            back = new Sprite(ImageLoader.loadSubImage("Intro.png", Colors.MAGENTA, 0, 0, 800, 600));
            back.setScale(1);
            back.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
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

            playerSpeaking = new Sprite(ImageLoader.loadSubImage("pointer.png", Colors.MAGENTA, 0, 0, 24, 24));
            playerSpeaking.setScale(2);
            playerSpeaking.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
            playerSpeaking.setLocation(hikerLocation.x + 87, hikerLocation.y - 50); 

            rangerSpeaking = new Sprite(ImageLoader.loadSubImage("pointer.png", Colors.MAGENTA, 0, 0, 24, 24));
            rangerSpeaking.setScale(2);
            rangerSpeaking.setImageEffect(ImageEffect.FLIP_HORIZONTAL);
            rangerSpeaking.setLocation(rangerLocation.x + 87, rangerLocation.y - 50); 

        }

        public void setPlayerSpeaking1(boolean flag) {
            playerIsSpeaking1 = flag;
        }

        @Override
        public void draw(GraphicsHandler graphicsHandler) {
            super.draw(graphicsHandler);
            back.draw(graphicsHandler);
            hiker.draw(graphicsHandler);
            ranger.draw(graphicsHandler);

            if (playerIsSpeaking1) {
                playerSpeaking.draw(graphicsHandler);
            } else {
                rangerSpeaking.draw(graphicsHandler);
            }    
         
        }

    }