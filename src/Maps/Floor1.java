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

public class Floor1 extends Map {

    public Floor1(){
        super("floor1.txt", new MainTileset());
        this.playerStartPosition = new Point(500,500);
    }

    // NPCs
    @Override
    public ArrayList<NPC> loadNPCs(){
        ArrayList<NPC> npcs = new ArrayList<>();

        // npcs here

        return npcs;
    }

    // Enemies
    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        // add enemies here

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
