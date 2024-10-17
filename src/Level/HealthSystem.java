package Level;

import Engine.ImageLoader;
import GameObject.Sprite;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class HealthSystem {
    private int maxHealth;
    private int currentHealth;
    private List<Sprite> hearts;

    public HealthSystem(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.hearts = new ArrayList<>();

        // Load the heart sprites into the list based on max health.
        for (int i = 0; i < maxHealth; i++) {
            Sprite heart = new Sprite(ImageLoader.load("heart.png"));
            heart.setScale(2); // Scale the hearts if necessary.
            heart.setLocation(10 + (i * 40), 10); // Position hearts horizontally.
            hearts.add(heart);
        }
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void decreaseHealth() {
        if (currentHealth > 0) {
            currentHealth--;
        }
    }

    public void increaseHealth() {
        if (currentHealth < maxHealth) {
            currentHealth++;
        }
    }

    public void draw(Graphics graphics) {
        for (int i = 0; i < currentHealth; i++) {
            hearts.get(i).draw(graphics);
        }
    }
}
