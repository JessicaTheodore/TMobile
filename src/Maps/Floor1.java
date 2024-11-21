package Maps;

import Level.Enemy;
import Level.Map;
import Level.MapEntityStatus;
import Level.NPC;
import Level.Player;
import Level.Trigger;
import NPCs.Lock;
import Scripts.Level3.ExitCastle;
import Enemies.BoomerEnemy;
import Tilesets.MainTileset;
import Utils.Point;
import java.util.ArrayList;

import Engine.GraphicsHandler;
// import Enemies.BoomerEnemy;

public class Floor1 extends Map {
    protected Lock lock;

    public Floor1(){
        super("floor1.txt", new MainTileset());
        this.playerStartPosition = new Point(610,650);
    }

    // NPCs
    @Override
    public ArrayList<NPC> loadNPCs(){
        ArrayList<NPC> npcs = new ArrayList<>();

        lock = new Lock(1, new Point(200,200));
        npcs.add(lock);

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
        triggers.add(new Trigger(639, 767, 66, 2, new ExitCastle(), "exitCastle"));

        return triggers;
    }

    @Override
    public void update(Player player) {
        super.update(player); 
        // if (getEnemiesById(0).mapEntityStatus == MapEntityStatus.REMOVED){
        //     // getNPCById(1).mapEntityStatus = MapEntityStatus.REMOVED;
        //     System.out.println("AKUDEHABSJKD");
        // }

    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        for (int i = 0; i <= triggers.size(); i++) {
            triggers.get(i).draw(graphicsHandler);
        }
    }


    
}
