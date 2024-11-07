package Enemies;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntityStatus;
import Utils.Point;
import java.util.HashMap;
import java.awt.Color;

public class BearEnemy extends Enemy {
    protected Point startLocation;
    protected int bearHP;
    protected float movementSpeed = 3f;
    protected int iFrames = 0;

    // Variables for circular movement
    private float radius; // Radius of the circular path
    private float angle;  // Current angle of the bear's position in the circular path

    public BearEnemy(int id, int enemyHP, Point location, float radius) {
        super(id, enemyHP, location.x, location.y, new SpriteSheet(ImageLoader.load("NewBear.png"), 24, 24), "STAND");
        this.bearHP = enemyHP; // Initialize bear HP
        this.radius = radius; // Set the radius for circular movement
        this.angle = (float)Math.random() * (float)(2 * Math.PI); // Random starting angle
        isUncollidable = true;
        startLocation = location; // Store the starting location
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0),12)
                    .withScale(3)
                    .withBounds(4, 5, 15, 15)
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(), 
                    // new FrameBuilder(spriteSheet.getSprite(0, 1), 12)
                    // .withScale(2)
                    // .withBounds(4, 5, 15, 15)
                    // .build()
                });
            }};
        }

    @Override
    public void hurtEnemy() {
        if(bearHP > 0){
            if (iFrames == 0) {
                iFrames = 60;
                this.bearHP--;
                System.out.println("hit the bear" + getBearHP());
            }
        }

        if (bearHP <= 0) {
            mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    public int getBearHP() {
        return this.bearHP;
    }

    // @Override
    // public void draw(GraphicsHandler graphicsHandler) {
    //     super.draw(graphicsHandler);
    //     // Draws the hitbox
    //     drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
    // }

    // Override update to implement circular movement
    @Override
public void update() {
    super.update();

    if (iFrames > 0) {
        iFrames--;
    }

    // Update the angle to create circular movement
    angle += movementSpeed * 0.01; // Increment the angle (adjust speed multiplier as needed)

    // Calculate new X and Y positions for circular motion
    float newX = startLocation.x + radius * (float) Math.cos(angle);
    float newY = startLocation.y + radius * (float) Math.sin(angle);

    // Add more apparent vertical oscillation
    float verticalOscillation = 10 * (float) Math.sin(angle * 8); // Oscillation range increased to 10 pixels

    // Combine the circular motion with the vertical oscillation
    newY += verticalOscillation;

    // Set the bear's new position
    this.setLocation(newX, newY);
}
}
