package Enemies;

import java.awt.Color;
import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntityStatus;
import Level.Player;
import Utils.Direction;
import Utils.Point;

public class EvilMushroom extends Enemy {

    protected Point startLocation;
    protected int shroomHP;
    protected int iFrames = 0;

    private int totalAmountMoved = 0;
    private Direction direction = Direction.RIGHT;
    private float speed = 1;
    
    public EvilMushroom(int id, int enemyHP, Point location){
        super(id, enemyHP, location.x, location.y, new SpriteSheet(ImageLoader.load("evilmushroom.png"), 34, 34), "HOPPING");
        this.shroomHP = enemyHP; 
        isUncollidable = true;
        startLocation = location;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("HOPPING", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 8)
                    .withScale(2)
                    .withBounds(4, 4, 30, 28)
                    .build(),
                new FrameBuilder(spriteSheet.getSprite(0, 1), 12)
                    .withScale(2)
                    .withBounds(4, 4, 30, 28)
                    .build()

            });
        }};
    }

    @Override
    public void hurtEnemy() {
        this.shroomHP--;
        System.out.println("hit the mushroom" + getShroomHP());

        if (shroomHP <= 0) {
            mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    public int getShroomHP() {
        return this.shroomHP;
    }


    @Override
    public void update() {
        super.update();

        if(iFrames > 0){
            iFrames--;
        }        
    }

    // Makes evil mushroom enemy walk back and forth (left to right)
    @Override
    public void performAction(Player player) {
        // if shroomy has not yet moved __ pixels in one direction, move forward
        if (totalAmountMoved < 500) {
            float amountMoved = moveXHandleCollision(speed * direction.getVelocity());
            totalAmountMoved += Math.abs(amountMoved);
        }

        // else if shroomy has already moved __ pixels in one direction, flip direction
        else {
            totalAmountMoved = 0;
            if (direction == Direction.LEFT) {
                direction = Direction.RIGHT;
            }
            else {
                direction = Direction.LEFT;
            }
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);

        // Hitbox
        // drawBounds(graphicsHandler, new Color(255, 0, 0, 40));

    }


}
