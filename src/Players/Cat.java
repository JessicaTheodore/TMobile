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
        walkSpeed = 6f;
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
                            .withBounds(6, 12, 12, 7)
                            .build()
            });

            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(10, 0))
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 12, 12, 7)
                            .build()
            });

            put("WALK_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(11, 0), 14)
                            .withScale(2)
                            .withBounds(6, 12, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 1), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 12, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 2), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 12, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 3), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 12, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 4), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 12, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 5), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 12, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 6), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 12, 12, 7)
                            .build(),    
                    new FrameBuilder(spriteSheet.getSprite(11, 7), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 12, 12, 7)
                            .build()
            });

            put("WALK_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(11, 0), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 12, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 1), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 12, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 2), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 12, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 3), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 12, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 4), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 12, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 5), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 12, 12, 7)
                            .build(),
                    new FrameBuilder(spriteSheet.getSprite(11, 6), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 12, 12, 7)
                            .build(),    
                    new FrameBuilder(spriteSheet.getSprite(11, 7), 14)
                            .withScale(2)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .withBounds(6, 12, 12, 7)
                            .build()
            });
        }};
    }
}
