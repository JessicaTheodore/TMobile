package Enemies;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Utils.Direction;
import Utils.Point;
import java.util.HashMap;

public class BearEnemy extends Enemy {
    protected Point startLocation;
    protected Point endLocation;
    
    protected int bearHP;
    
    protected float movementSpeed = 1f;
    private Direction startFacingDirection;
    protected Direction facingDirection;

    public BearEnemy(int id, int enemyHP, Point location) {
        super(id, enemyHP, location.x, location.y, new SpriteSheet(ImageLoader.load("Bear.png"), 24, 24), "STAND_LEFT");
    }

    public BearEnemy(int id, int enemyHP, Point startLocation, Point endLocation, Direction facingDirection) {
        super(id, 2, startLocation.x, startLocation.y, new SpriteSheet(ImageLoader.load("Bear.png"), 24, 24), "WALK_RIGHT");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
        this.initialize();
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(3)
                            .withBounds(37, 29, 27, 47)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
        }};
    }

    public void hurtEnemy(){
        if(bearHP > 0) {
            this.bearHP--;
        } else {
            // this is where I want to put the code of getting rid of the enemy
        }
    }
}
