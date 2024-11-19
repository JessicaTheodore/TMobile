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
import Level.*;
import java.util.Random;

public class SquirrelEnemy extends Enemy {
    protected Point startLocation;
    protected int squirrelHP;
    protected float movementSpeed = 3f;
    protected int iFrames = 0;

    protected Random random = new Random();

    protected SquirrelState squirrelState;

    protected int shootWaitTimer;

    public SquirrelEnemy(int id, int enemyHP, Point location, float radius) {
        super(id, enemyHP, location.x, location.y, new SpriteSheet(ImageLoader.load("Squirrel.png"), 64, 57), "STAND");
        this.squirrelHP = enemyHP; // Initialize squirrel HP
        isUncollidable = true;
        startLocation = location; // Store the starting location
        this.initialize();
    }

    @Override
    public void initialize() {
        super.initialize();
        squirrelState = SquirrelState.WAIT;

        // every certain number of frames, the fireball will be shot out
        shootWaitTimer = 45;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0),12)
                    .withScale(2)
                    .withBounds(4, 5, 50, 50)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(), 
                });
            }};
        }
    

    // @Override
    // public void draw(GraphicsHandler graphicsHandler) {
    //     super.draw(graphicsHandler);
    //     // Draws the hitbox
    //     drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
    // }

    @Override
    public void update(Player player) {
        if (shootWaitTimer == 0 && squirrelState == SquirrelState.WAIT) {
            squirrelState = SquirrelState.SHOOT;
        } else {
            shootWaitTimer--;
        }

        if (squirrelState == SquirrelState.SHOOT) {
            // define where nut will spawn on map (x location) relative to Squirell enemy's location
            // and define its movement speed
            int nutX;
            float movementSpeed;
            
            nutX = Math.round(getX()) + 50;
            movementSpeed = 1.5f;

            // define where fireball will spawn on the map (y location) relative to Squirell enemy's location
            int nutY = Math.round(getY()) + 80;

            // create Nuts enemy
            Nuts nuts = new Nuts(new Point(nutX, nutY), movementSpeed, 200);

            // add fireball enemy to the map for it to spawn in the level
            map.addEnemy(nuts);

            // change Squirell back to its WALK state after shooting, reset shootTimer to wait a certain number of frames before shooting again
            squirrelState = SquirrelState.WAIT;

            // reset shoot wait timer so the process can happen again (dino walks around, then waits, then shoots)
            shootWaitTimer = random.nextInt(110 - 80 + 1) + 80;
        }
        super.update(player);
    }

    public enum SquirrelState {
        WAIT, SHOOT
    }
}
