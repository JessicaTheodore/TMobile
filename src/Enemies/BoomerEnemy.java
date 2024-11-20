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

public class BoomerEnemy extends Enemy {
    protected Point startLocation;
    protected int boomerHP;
    protected float movementSpeed = 3f;
    protected int iFrames = 0;

    // Variables for circular movement
    private float radius; // Radius of the circular path
    private float angle;  // Current angle of the bear's position in the circular path

    public BoomerEnemy(int id, int enemyHP, Point location, float radius) {
        super(id, enemyHP, location.x, location.y, new SpriteSheet(ImageLoader.load("BOOMER.png"), 200, 270), "STAND");
        this.boomerHP = enemyHP; // Initialize bear HP
        this.radius = radius; // Set the radius for circular movement
        this.angle = (float)Math.random() * (float)(2 * Math.PI); // Random starting angle
        isUncollidable = true;
        startLocation = location; // Store the starting location
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0), 12)
                    .withScale(0.8f) // Scale down the sprite to half its size
                    .withBounds(20, 0, 160, 250) 
                    .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                    .build(),
            });
        }};
    }

    @Override
    public void hurtEnemy() {
        if(boomerHP > 0){
            if (iFrames == 0) {
                iFrames = 60;
                this.boomerHP--;
                System.out.println("hit the boomer " + getBoomerHP());
            }
        }

        if (boomerHP <= 0) {
            mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    public int getBoomerHP() {
        return this.boomerHP;
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        // Draws the hitbox
        drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
    }

    // Override update to implement circular movement
    @Override
    public void update() {
        super.update();

        // Handle invincibility frames countdown
        if (iFrames > 0) {
            iFrames--;
        }

        // Create vertical oscillation
        float verticalOscillation = 10 * (float) Math.sin(angle); // Oscillates up and down within a range of 10 pixels
        angle += movementSpeed * 0.01; // Increment the angle to continue the oscillation

        // Update the Y position only
        float newY = startLocation.y + verticalOscillation;

        // Keep X constant and update Y
        this.setLocation(startLocation.x, newY);
    }

}
