package NPCs;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

public class Lock extends NPC {

    public Lock (int id, Point location){
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Lock.png"), 18, 21), "1");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("1", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(1)
                    .withBounds(0,0,1,1)
                    .build()
            });
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }

    
}
