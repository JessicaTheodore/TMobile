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

    // change this to slingshot stuff
    public Slingshot(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("breakablelog.png"), 100, 100), "STAND_LEFT");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(3)
                            .withBounds(10, 10, 10, 10) // change this later to math
                            .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);

        // DRAWS HITBOX
        drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
    }
}
