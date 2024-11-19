package Enemies;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntity;
import Level.MapEntityStatus;
import Level.Player;
import Utils.Direction;
import Utils.Point;

public class Nuts extends Enemy {
    private float movementSpeed;
    private int existenceFrames;

    public Nuts(Point location, float movementSpeed, int existenceFrames) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Nut.png"), 16, 16), "DEFAULT");
        this.movementSpeed = movementSpeed;

        // how long the nut will exist for before disappearing
        this.existenceFrames = existenceFrames;

        initialize();
    }

    @Override
    public void update(Player player) {
        // if timer is up, set map entity status to REMOVED
        // the camera class will see this next frame and remove it permanently from the map
        if (existenceFrames == 0) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        } else {
            // move nut down
            moveYHandleCollision(movementSpeed);
            super.update(player);
        }
        existenceFrames--;
    }

    // @Override
    // public void onEndCollisionCheckY(boolean hasCollided, Direction direction, MapEntity entityCollidedWith) {
    //     // if fireball collides with anything solid on the x axis, it is removed
    //     if (hasCollided) {
    //         this.mapEntityStatus = MapEntityStatus.REMOVED;
    //     }
    // }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(1)
                            .withBounds(0, 0, 16, 16)
                            .build()
            });
        }};
    }
}
