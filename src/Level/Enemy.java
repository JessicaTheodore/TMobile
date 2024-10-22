package Level;
    
import GameObject.Frame;
import GameObject.SpriteSheet;
import ScriptActions.NPCChangeVisibilityScriptAction;
import Utils.Visibility;
import Level.MapEntity;

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
        super.update();
        if (intersects(player)) {
            touchedPlayer(player);
        }
    }

    public void touchedPlayer(Player player) {
        player.hurtPlayer(this);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
