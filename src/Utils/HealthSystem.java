package Utils;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Sprite;
import java.awt.Graphics2D;
import Level.Player;

public class HealthSystem {
    private int maxHealth;
    private int currentHealth;
    private Sprite[] heartSprites = new Sprite[5];
    private Sprite heartSprite;

    public HealthSystem(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        for(int i = 0; i < 5; i++){
            this.heartSprites[i] = new Sprite(ImageLoader.load("heart.png"));
            heartSprites[i].setScale(1);
        }
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
        for (int i = 0; i < Player.getCurrentHealth(); i++) {
            heartSprites[i].setLocation(700 - i * (heartSprites[i].getWidth() + 10), 10);
            heartSprites[i].draw(graphicsHandler);
        }
    }
}


