package Players;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Player;

import java.util.HashMap;

// This is the class for the Cat player character
// basically just sets some values for physics and then defines animations
public class Cat extends Player {

    public Cat(float x, float y) {
        super(new SpriteSheet(ImageLoader.load("Hiker.png"), 64, 64), x, y, "STAND_RIGHT");
        walkSpeed = 3f;
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
                    new FrameBuilder(spriteSheet.getSprite(10, 0))
                            .withScale(2)
                            .withBounds(15, 5, 35, 50)
                            .build()
            });

            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(10, 0))
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(15, 5, 35, 50)
                            .build()
            });

            put("STAND_UP", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(8, 0))
                        .withScale(2)
                        .withBounds(15, 5, 35, 50)
                        .build()
            });

            put("STAND_DOWN", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(10, 0))
                        .withScale(2)
                        .withBounds(15, 5, 35, 50)
                        .build()
            });

            put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(11, 0), 5)
                            .withScale(2)
                            .withBounds(15, 5, 35, 50)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 1), 5)
                            .withScale(2)
                            .withBounds(15, 5, 35, 50)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 2), 5)
                            .withScale(2)
                            .withBounds(15, 5, 35, 50)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 3), 5)
                            .withScale(2)
                            .withBounds(15, 5, 35, 50)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 4), 5)
                            .withScale(2)
                            .withBounds(15, 5, 35, 50)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 5), 5)
                            .withScale(2)
                            .withBounds(15, 5, 35, 50)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 6), 5)
                            .withScale(2)
                            .withBounds(15, 5, 35, 50)
                            .build(),    
                    new FrameBuilder(spriteSheet.getSprite(11, 7), 5)
                            .withScale(2)
                            .withBounds(15, 5, 35, 50)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 8), 5)
                            .withScale(2)
                            .withBounds(15, 5, 35, 50)
                            .build()
            });

            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(11, 0), 5)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(15, 5, 35, 50)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 1), 5)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(15, 5, 35, 50)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 2), 5)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(15, 5, 35, 50)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 3), 5)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(15, 5, 35, 50)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 4), 5)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(15, 5, 35, 50)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 5), 5)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(15, 5, 35, 50)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 6), 5)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(15, 5, 35, 50)
                            .build(),    
                    new FrameBuilder(spriteSheet.getSprite(11, 7), 5)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(15, 5, 35, 50)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 8), 5)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(15, 5, 35, 50)
                            .build()
            });
        
        //     put("WALK_UP", new Frame[] {
        //         new FrameBuilder(spriteSheet.getSprite(8, 0), 5)
        //                 .withScale(2)
        //                 .withBounds(15, 5, 35, 50)
        //                 .build(),
        //         new FrameBuilder(spriteSheet.getSprite(8, 1), 5)
        //                 .withScale(2)
        //                 .withBounds(15, 5, 35, 50)
        //                 .build(),
        //         new FrameBuilder(spriteSheet.getSprite(8, 2), 5)
        //                 .withScale(2)
        //                 .withBounds(15, 5, 35, 50)
        //                 .build(),
        //         new FrameBuilder(spriteSheet.getSprite(8, 3), 5)
        //                 .withScale(2)
        //                 .withBounds(15, 5, 35, 50)
        //                 .build(),
        //         new FrameBuilder(spriteSheet.getSprite(8, 4), 5)
        //                 .withScale(2)
        //                 .withBounds(15, 5, 35, 50)
        //                 .build(),
        //         new FrameBuilder(spriteSheet.getSprite(8, 5), 5)
        //                 .withScale(2)
        //                 .withBounds(15, 5, 35, 50)
        //                 .build(),
        //         new FrameBuilder(spriteSheet.getSprite(8, 6), 5)
        //                 .withScale(2)
        //                 .withBounds(15, 5, 35, 50)
        //                 .build(),    
        //         new FrameBuilder(spriteSheet.getSprite(8, 7), 5)
        //                 .withScale(2)
        //                 .withBounds(15, 5, 35, 50)
        //                 .build(),
        //         new FrameBuilder(spriteSheet.getSprite(8, 8), 5)
        //                 .withScale(2)
        //                 .withBounds(15, 5, 35, 50)
        //                 .build()
        //         });
        
        //    put("WALK_DOWN", new Frame[] {
        //         new FrameBuilder(spriteSheet.getSprite(10, 0), 5)
        //                 .withScale(2)
        //                 .withBounds(15, 5, 35, 50)
        //                 .build(),
        //         new FrameBuilder(spriteSheet.getSprite(10, 1), 5)
        //                 .withScale(2)
        //                 .withBounds(15, 5, 35, 50)
        //                 .build(),
        //         new FrameBuilder(spriteSheet.getSprite(10, 2), 5)
        //                 .withScale(2)
        //                 .withBounds(15, 5, 35, 50)
        //                 .build(),
        //         new FrameBuilder(spriteSheet.getSprite(10, 3), 5)
        //                 .withScale(2)
        //                 .withBounds(15, 5, 35, 50)
        //                 .build(),
        //         new FrameBuilder(spriteSheet.getSprite(10, 4), 5)
        //                 .withScale(2)
        //                 .withBounds(15, 5, 35, 50)
        //                 .build(),
        //         new FrameBuilder(spriteSheet.getSprite(10, 5), 5)
        //                 .withScale(2)
        //                 .withBounds(15, 5, 35, 50)
        //                 .build(),
        //         new FrameBuilder(spriteSheet.getSprite(10, 6), 5)
        //                 .withScale(2)
        //                 .withBounds(15, 5, 35, 50)
        //                 .build(),    
        //         new FrameBuilder(spriteSheet.getSprite(10, 7), 5)
        //                 .withScale(2)
        //                 .withBounds(15, 5, 35, 50)
        //                 .build(),
        //         new FrameBuilder(spriteSheet.getSprite(10, 8), 5)
        //                 .withScale(2)
        //                 .withBounds(15, 5, 35, 50)
        //                 .build()
        //         });
        }};
    }
}
