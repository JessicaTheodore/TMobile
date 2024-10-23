package Level;
    
import GameObject.Frame;
import GameObject.SpriteSheet;
import ScriptActions.NPCChangeVisibilityScriptAction;
import Utils.Visibility;
import Level.MapEntity;
import Enemies.BearEnemy;

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

    public Enemy(int id, int enemyHP,float x, float y, HashMap<String, Frame[]> animations, String startingAnimation) {
        super(x, y, animations, startingAnimation);
        this.id = id;
    }

    public Enemy(int id, int enemyHP,float x, float y, Frame[] frames) {
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

    public void update(Player player) {
        super.update(); // this goes and updates the current position and animation of the enemy
        if (intersects(player)) {
                player.touchedPlayer(player); // this is called into the Player class when the enemy intersects swith the player
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
