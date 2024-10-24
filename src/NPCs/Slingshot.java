package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

import java.awt.Color;
import java.util.HashMap;

public class Slingshot extends NPC {

    public Slingshot(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Slingshot.png"), 10, 19), "STAND_LEFT");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                        .withScale(3)
                        .withBounds(0, 0, 10, 16) 
                        .build()
            });
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 1))
                        .withScale(3)
                        .withBounds(0, 1, 10, 16) 
                        .build()
            });
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 2))
                        .withScale(3)
                        .withBounds(0, 2, 10, 16) 
                        .build()
            });
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 3))
                        .withScale(3)
                        .withBounds(0, 3, 10, 16) 
                        .build()
            });
        }};
    }

    // @Override
    // public void draw(GraphicsHandler graphicsHandler) {
    //     super.draw(graphicsHandler);

    //     // DRAWS HITBOX
    //     drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
    // }
}
