package Maps;

import Level.Enemy;
import Level.Map;
import Level.NPC;
import Level.Player;
import Level.Trigger;
import Enemies.BoomerEnemy;
import Scripts.Level2.Beat2;
import Scripts.Level3.EnterFloor2;
import Tilesets.MainTileset;
import Utils.Point;
import java.util.ArrayList;

import Engine.GraphicsHandler;
// import Enemies.BoomerEnemy;

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
        float boomerMovementRadius = 50.0f; // Adjust this value as needed
        // add enemies here

        BoomerEnemy boomer = new BoomerEnemy(0, 5, getMapTile(15, 7).getLocation(), boomerMovementRadius);
        enemies.add(boomer);
        return enemies;
    }

    // Triggers
    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // Add triggers here
        // triggers.add(new Trigger(380, 330, 50, 130, new EnterFloor2(), "enterFloor2"));

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
