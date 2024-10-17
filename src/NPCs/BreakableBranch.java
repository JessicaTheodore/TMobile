package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

import java.awt.Color;
import java.util.HashMap;

public class BreakableBranch extends NPC {

    public BreakableBranch(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("breakablebranch.png"), 80, 32), "FACING_RIGHT");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("FACING_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(3)
                            .withBounds(0, 0, 80, 32)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);

        // DRAWS HITBOX
        // drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
    }

}
