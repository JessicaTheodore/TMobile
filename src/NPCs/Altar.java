package NPCs;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

public class Altar extends NPC {
    
    public Altar(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("altar.png"), 47, 97), "FIRE_1");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("FIRE_1", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                    .withScale(1.5f)
                    .withBounds(3, 40, 39, 50)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 8)
                    .withScale(1.5f)
                    .withBounds(3, 40, 39, 50)
                    .build()
           });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

}
