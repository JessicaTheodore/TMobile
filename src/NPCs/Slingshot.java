package NPCs;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.NPC;
import Utils.Point;

import java.awt.Color;
import java.util.HashMap;

public class Slingshot extends NPC {

    protected Point startLocation;
    protected float movementSpeed = 3f;
    protected int iFrames = 0;
    
    // Add variables for cycling animation
    private float cycleTime = 0f;  // Tracks the time for oscillation
    private float oscillationSpeed = 0.1f;  // Speed of oscillation
    private float offset = 2f;  // How far to move on the x and y axes

    public Slingshot(int id, Point location) {
        super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("Slingshot.png"), 13, 19), "STAND_LEFT");
        this.startLocation = location;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("STAND_LEFT", new Frame[] {
                new FrameBuilder(spriteSheet.getSprite(0, 0))
                        .withScale(3)
                        .withBounds(0, 0, 10, 16) 
                        .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                        .build()
            });
        }};
    }

    @Override
    public void update() {
        super.update();

        if(iFrames > 0){
            iFrames--;
        }
        
        // Update the position for the animation effect
        cycleTime += oscillationSpeed;  // Increase cycle time for oscillation
        
        // Use sine or cosine to oscillate the position
        // float oscillationX = (float) Math.sin(cycleTime) * offset;  // -2 to +2 on X
        float oscillationY = (float) Math.cos(cycleTime) * offset;  // -2 to +2 on Y

        // Update the slingshot position
        this.setLocation(startLocation.x, startLocation.y + oscillationY);

       // System.out.println("Slingshot updating with oscillation: x=" + oscillationX + " y=" + oscillationY);
    }

    // @Override
    // public void draw(GraphicsHandler graphicsHandler) {
    //     super.draw(graphicsHandler);

    //     // DRAWS HITBOX
    //     drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
    // }
}
