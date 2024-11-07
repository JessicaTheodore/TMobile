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
import Utils.Point;

public class EvilMushroom extends Enemy {

    protected Point startLocation;
    protected int shroomHP;
    
    public EvilMushroom(int id, int enemyHP, Point location){
        super(id, enemyHP, location.x, location.y, new SpriteSheet(ImageLoader.load("evilmushroom.png"), 34, 34), "GROUNDED");
        this.shroomHP = enemyHP; 
        isUncollidable = true;
        startLocation = location;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("GROUNDED", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(2)
                    .withBounds(0, 0, 34, 34)
                    .build()
            });
            put("MIDAIR", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                    .withScale(2)
                    .withBounds(35, 0, 33, 33)
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


    // this will be used for setting the movement i think 
    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);

        // Hitbox
        drawBounds(graphicsHandler, new Color(255, 0, 0, 100));

    }


}
