package Utils;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Sprite;
import java.awt.Graphics2D;
import Level.Player;

public class HealthSystem {
    private int maxHealth;
    private int currentHealth;
    private Sprite heartSprite;

    public HealthSystem(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.heartSprite = new Sprite(ImageLoader.load("heart.png"));
        heartSprite.setScale(1);
    }

    public void decreaseHealth() {
        if (currentHealth > 0) {
            currentHealth--;
        }
    }

    public int getCurrentHealth() {
        return currentHealth;
    }
    
    // we need to have an update thing here so when the player does take damage, it will update and redraw the correct number of hearts on the screen


    public void draw(GraphicsHandler graphicsHandler) {
        for (int i = 0; i < currentHealth; i++) {
            heartSprite.setLocation(700 - i * (heartSprite.getWidth() + 10), 10);
            heartSprite.draw(graphicsHandler);
        }
    }
}


