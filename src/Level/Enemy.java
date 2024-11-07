package Level;

import GameObject.Frame;
import GameObject.SpriteSheet;
import java.util.HashMap;
import Engine.GraphicsHandler;

// This class is a base class for all enemies in the game -- all enemies should extend from it
public class Enemy extends MapEntity {
    protected int enemyHP;
    protected int id;

    public Enemy(int id, int enemyHP, float x, float y, SpriteSheet spriteSheet, String startingAnimation) {
        super(x, y, spriteSheet, startingAnimation);
        this.id = id;
        this.enemyHP = enemyHP;
    }

    public Enemy(int id, int enemyHP, float x, float y, HashMap<String, Frame[]> animations, String startingAnimation) {
        super(x, y, animations, startingAnimation);
        this.id = id;
        this.enemyHP = enemyHP;
    }

    public Enemy(int id, int enemyHP, float x, float y, Frame[] frames) {
        super(x, y, frames);
        this.id = id;
        this.enemyHP = enemyHP;
    }

    public Enemy(int id, int enemyHP, float x, float y, Frame frame) {
        super(x, y, frame);
        this.id = id;
        this.enemyHP = enemyHP;
    }

    public Enemy(int id, int enemyHP, float x, float y) {
        super(x, y);
        this.id = id;
        this.enemyHP = enemyHP;
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    public int getId() { return id; }

    // Original update that requires Player object
    public void update(Player player) {
        super.update();
        this.performAction(player); // new testing for enemy movement
        if (intersects(player.stickRectangle) || intersects(player.slingshotRectangle)) {
            System.out.println("Attack Intersected");
            player.touchedEnemy();
        } else if(intersects(player)) {
            System.out.println("Player Intersected");
            player.hurtPlayer();
        }
    }

    // new testing for enemy movement 
    protected void performAction(Player player) {}

    // Overriding update without Player argument
    @Override
    public void update() {
        super.update();
    }

    public void hurtEnemy() {
        // Define how the enemy gets hurt
    }

    // @Override
    // public void draw(GraphicsHandler graphicsHandler) {
    //     super.draw(graphicsHandler);
    // }
}

