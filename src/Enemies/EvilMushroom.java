package Enemies;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntityStatus;
import Utils.Point;

public class EvilMushroom extends Enemy {

    protected Point startLocation;
    protected int shroomHP;
    
    public EvilMushroom(int id, int enemyHP, Point location){
        super(id, enemyHP, location.x, location.y, new SpriteSheet(ImageLoader.load("evilmushroom.png"), 30, 30), "GROUNDED");
        this.shroomHP = enemyHP; 
        isUncollidable = true;
        startLocation = location;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("GROUNDED", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(3)
                    .withBounds(0, 0, 33, 33)
                    .build()
            });
            put("MIDAIR", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(1, 1))
                    .withScale(3)
                    .withBounds(69, 0, 34, 34)
                    .build()
            });

        }};
    }

    @Override
    public void hurtEnemy() {
        this.shroomHP--;
        System.out.println("hit the bear" + getShroomHP());

        if (shroomHP <= 0) {
            mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    public int getShroomHP() {
        return this.shroomHP;
    }


    // this will be used for setting the movement i think 
    @Override
    public void update() {
        super.update();
    }

}
