package Maps;

import Level.Enemy;
import Level.Map;
import Level.NPC;
import Level.Player;
import Level.Trigger;
import NPCs.Hobbomock;
import Scripts.Level3.EnterFloor1;
import Scripts.Level3.HoboDialogue;
import Tilesets.MainTileset;
import Utils.Point;
import java.util.ArrayList;

import Engine.GraphicsHandler;

public class Level3 extends Map {
    
    public Level3(){
        super("lvl3.txt", new MainTileset());
        this.playerStartPosition = getMapTile(38,27).getLocation();
    }

    // NPCs
    @Override
    public ArrayList<NPC> loadNPCs(){
        ArrayList<NPC> npcs = new ArrayList<>();

        Hobbomock hobo = new Hobbomock(0, new Point(100, 200));
        hobo.setInteractScript(new HoboDialogue());
        npcs.add(hobo);

        return npcs;
    }

    // Enemies
    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        // float boomerMovementRadius = 50.0f; // Adjust this value as needed

        // // add enemies here

        // BoomerEnemy boomer = new BoomerEnemy(4, 2, getMapTile(8, 6).getLocation(), boomerMovementRadius);
        // enemies.add(boomer);


        return enemies;
    }

    // Triggers
    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();

        // Add triggers here
        triggers.add(new Trigger(970, 505, 80, 120, new EnterFloor1(), "enterFloor1"));

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
