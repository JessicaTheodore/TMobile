package NPCs;

import java.awt.Color;
import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntityStatus;
import Level.NPC;
import Level.Player;
import Utils.Direction;
import Utils.Point;

public class Hobbomock extends NPC {

    public Hobbomock(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("tempHobbomock.png"), 34, 34), "FLOATING");
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("FLOATING", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                        .withScale(3)
                        .withBounds(4, 4, 30, 28)
                        //.withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                        .build(),
                    new FrameBuilder(spriteSheet.getSprite(0, 1), 12)
                        .withScale(3)
                        .withBounds(4, 4, 30, 28)
                        .build()
            });       
        }};
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}