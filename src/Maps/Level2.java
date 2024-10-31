package Maps;

import Level.Enemy;
import Level.Map;
import Level.NPC;
import Level.Player;
import Level.Trigger;
import Tilesets.MainTileset;
import Utils.Point;
import java.util.ArrayList;

import Engine.GraphicsHandler;

public class Level2 extends Map {

    protected float x;
    protected float y;
    
    public Level2() {
        super("lvl2.txt", new MainTileset());
        this.playerStartPosition = new Point(0,0);
    }

    public Level2(float x, float y) {
        super("lvl2.txt", new MainTileset());
        this.x = x;
        this.y = y;
        this.playerStartPosition = new Point(0, 0);
    }

    // NPCs
    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        // Add NPCs here

        return npcs;
    }

    // Enemies
    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        // Add Enemies here

        return enemies;
    }

    // Triggers
    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // Add triggers here

        return triggers;
    }

    @Override
    public void update(Player player) {
        super.update(player); 
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        for (int i = 0; i <= triggers.size(); i++) {
            triggers.get(i).draw(graphicsHandler);
        }
    }


}
