package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

import java.util.HashMap; 

// This is the little piece of the branch that stays attactched to the tree when the rest gets broken off 
public class BrokenBranch extends NPC {
    
        public BrokenBranch(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("brokenbranch.png"), 100, 100), "STAND_LEFT");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(8)
                            .withBounds(10, 10, 10, 10)
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
