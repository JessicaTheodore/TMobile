package Enemies;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntityStatus;
import Utils.Direction;
import Utils.Point;
import java.util.HashMap;
import java.awt.Color;

public class BearEnemy extends Enemy {
    protected Point startLocation;
    protected Point endLocation;
    
    protected int bearHP;
    
    protected float movementSpeed = 1f;
    private Direction startFacingDirection;
    protected Direction facingDirection;

    public BearEnemy(int id, int enemyHP, Point location) {
        super(id, enemyHP, location.x, location.y, new SpriteSheet(ImageLoader.load("Bear.png"), 24, 24), "STAND_LEFT");
        isUncollidable = true;
    }

    public BearEnemy(int id, int enemyHP, Point startLocation, Point endLocation, Direction facingDirection) {
        super(id, 2, startLocation.x, startLocation.y, new SpriteSheet(ImageLoader.load("Bear.png"), 24, 24), "STAND_LEFT");
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startFacingDirection = facingDirection;
        this.initialize();
        isUncollidable = true;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(3)
                            .withBounds(0, 0, 24, 24)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
        }};
    }
    
    // this gets called from the Player class when the player is in the stick attack state, which will do damage to the bear until its HP goes to 0
    public void hurtBear(){
        this.bearHP--; 
        System.out.println(getBearHP());

        if(bearHP <= 0){
            mapEntityStatus = MapEntityStatus.REMOVED;
            // this.isHidden();
        }
    }

    public int getBearHP(){
        return this.bearHP;
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);

        // DRAWS HITBOX
        drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
    }    
}
