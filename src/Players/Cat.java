package Players;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Game.ScreenCoordinator;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Player;

import java.util.HashMap;

// This is the class for the Cat player character
// basically just sets some values for physics and then defines animations
public class Cat extends Player {
    
    public Cat(float x, float y, ScreenCoordinator screenCoordinator) {
        super(new SpriteSheet(ImageLoader.load("Hiker.png"), 64, 64), x, y, "STAND_DOWN", screenCoordinator);
        walkSpeed = 4f;
    }

    public void update() {
        super.update();
    }

    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
                put("STAND_RIGHT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(11, 0))
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build()
                });

                put("STAND_LEFT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(9, 0))
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build()
                });

                put("STAND_UP", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(8, 0))
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build()
                });

                put("STAND_DOWN", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(10, 0))
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build()
                });

                put("WALK_RIGHT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(11, 0), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(11, 1), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(11, 2), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(11, 3), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(11, 4), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(11, 5), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(11, 6), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),    
                        new FrameBuilder(spriteSheet.getSprite(11, 7), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(11, 8), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build()
                });

                put("WALK_LEFT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(11, 0), 5)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(11, 1), 5)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(11, 2), 5)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(11, 3), 5)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(11, 4), 5)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(11, 5), 5)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(11, 6), 5)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(20, 31, 23, 22)
                                .build(),    
                        new FrameBuilder(spriteSheet.getSprite(11, 7), 5)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(11, 8), 5)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(20, 31, 23, 22)
                                .build()
                });
        
                put("WALK_UP", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(8, 0), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(8, 1), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(8, 2), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(8, 3), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(8, 4), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(8, 5), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(8, 6), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),    
                        new FrameBuilder(spriteSheet.getSprite(8, 7), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(8, 8), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build()
                });
        
                put("WALK_DOWN", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(10, 0), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(10, 1), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(10, 2), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(10, 3), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(10, 4), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(10, 5), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(10, 6), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),    
                        new FrameBuilder(spriteSheet.getSprite(10, 7), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(10, 8), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build()
                });

                put("STICK_RIGHT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(3, 0), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(3, 1), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(3, 2), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(3, 3), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(3, 0), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(3, 5), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(3, 6), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),    
                        new FrameBuilder(spriteSheet.getSprite(3, 7), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                });
                
                put("STICK_LEFT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(3, 0), 5)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(3, 1), 5)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(3, 2), 5)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(3, 3), 5)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(3, 4), 5)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(3, 5), 5)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(3, 6), 5)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(20, 31, 23, 22)
                                .build(),    
                        new FrameBuilder(spriteSheet.getSprite(3, 7), 5)
                                .withScale(2)
                                .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                });
                
                put("STICK_UP", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(0, 0), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 1), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 2), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 3), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 4), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 5), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(0, 6), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),    
                        new FrameBuilder(spriteSheet.getSprite(0, 7), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)
                                .build(),
                });
                
                put("STICK_DOWN", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(2, 0), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)                                
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(2, 1), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)                               
                                 .build(),
                        new FrameBuilder(spriteSheet.getSprite(2, 2), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)                               
                                 .build(),
                        new FrameBuilder(spriteSheet.getSprite(2, 3), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)                                
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(2, 4), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)                               
                                 .build(),
                        new FrameBuilder(spriteSheet.getSprite(2, 5), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)                                
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(2, 6), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)                                
                                .build(),    
                        new FrameBuilder(spriteSheet.getSprite(2, 7), 5)
                                .withScale(2)
                                .withBounds(20, 31, 23, 22)    
                                .build(),
                });
                
                put("ROCK_RIGHT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(7, 0), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(7, 1), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(7, 2), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(7, 3), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(7, 4), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(7, 5), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(7, 6), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),    
                        new FrameBuilder(spriteSheet.getSprite(7, 7), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(7, 8), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(7, 9), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(7, 10), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                });
                
                put("ROCK_LEFT", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(5, 0), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(5, 1), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(5, 2), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(5, 3), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(5, 4), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(5, 5), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(5, 6), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),    
                        new FrameBuilder(spriteSheet.getSprite(5, 7), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(5, 8), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(5, 9), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(5, 10), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                });
                
                put("ROCK_UP", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(4, 0), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(4, 1), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(4, 2), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(4, 3), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(4, 4), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(4, 5), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(4, 6), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),    
                        new FrameBuilder(spriteSheet.getSprite(4, 7), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(4, 8), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(4, 9), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(4, 10), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                });
                
                put("ROCK_DOWN", new Frame[] {
                        new FrameBuilder(spriteSheet.getSprite(6, 0), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(6, 1), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(6, 2), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(6, 3), 5)
                                .withScale(2)
                                .withBounds(15, 5, 35,25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(6, 4), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(6, 5), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(6, 6), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),    
                        new FrameBuilder(spriteSheet.getSprite(6, 7), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(6, 8), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(6, 9), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                        new FrameBuilder(spriteSheet.getSprite(6, 10), 5)
                                .withScale(2)
                                .withBounds(15, 28, 35, 25)
                                .build(),
                });
        }};
    }
}
