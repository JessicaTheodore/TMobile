package Maps;

import Level.Enemy;
import Level.EnhancedMapTile;
import Level.Map;
import Level.NPC;
import Level.Player;
import Level.Trigger;
import NPCs.Altar;
import Scripts.Level2.AltarScript;
import Scripts.Level1.NextLevel;
import Scripts.Level2.Beat2;
import Tilesets.MainTileset;
import Utils.Point;
import java.util.ArrayList;

import Enemies.EvilMushroom;
import Enemies.SquirrelEnemy;
import Engine.GraphicsHandler;
import EnhancedMapTiles.Ladder;

public class Level2 extends Map {

    protected float x;
    protected float y;
    
    public Level2() {
        super("lvl2.txt", new MainTileset());
        this.playerStartPosition = new Point(1060,2890);
        //this.playerStartPosition = new Point(70,70);
    }

    public Level2(float x, float y) {
        super("lvl2.txt", new MainTileset());
        this.x = x;
        this.y = y;
        this.playerStartPosition = new Point(1060, 2890);
    }

    // NPCs
    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Altar altar = new Altar(0, new Point(3152,1752));
        altar.setInteractScript(new AltarScript());
        npcs.add(altar);

        return npcs;
    }

    // Enemies
    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        EvilMushroom shroom1 = new EvilMushroom(0,1, new Point(400,1900));
        enemies.add(shroom1);

        EvilMushroom shroom2 = new EvilMushroom(1,1, new Point(300,1700));
        enemies.add(shroom2);

        SquirrelEnemy Squirrel9 = new SquirrelEnemy (0,1, new Point(1200,1550), 10);
        enemies.add(Squirrel9);
        
        SquirrelEnemy Squirrel7 = new SquirrelEnemy (0,1, new Point(1300,1550), 10);
        enemies.add(Squirrel7);

        SquirrelEnemy Squirrel8 = new SquirrelEnemy (0,1, new Point(1400,1550), 10);
        enemies.add(Squirrel8);

        SquirrelEnemy Squirrel1 = new SquirrelEnemy (0,1, new Point(1500,1550), 10);
        enemies.add(Squirrel1);

        SquirrelEnemy Squirrel2 = new SquirrelEnemy (0,1, new Point(1700,1550), 10);
        enemies.add(Squirrel2);

        SquirrelEnemy Squirrel3 = new SquirrelEnemy (0,1, new Point(2000,1600), 10);
        enemies.add(Squirrel3);

        SquirrelEnemy Squirrel4 = new SquirrelEnemy (0,1, new Point(2100,1600), 10);
        enemies.add(Squirrel4);
        
        SquirrelEnemy Squirrel5 = new SquirrelEnemy (0,1, new Point(2300,1670), 10);
        enemies.add(Squirrel5);

        SquirrelEnemy Squirrel6 = new SquirrelEnemy (0,1, new Point(2400,1670), 10);
        enemies.add(Squirrel6);

        SquirrelEnemy Squirrel10 = new SquirrelEnemy (0,1, new Point(2500,1670), 10);
        enemies.add(Squirrel10);
        
        SquirrelEnemy Squirrel11 = new SquirrelEnemy (0,1, new Point(2600,1670), 10);
        enemies.add(Squirrel11);

        SquirrelEnemy Squirrel12 = new SquirrelEnemy (0,1, new Point(2700,1670), 10);
        enemies.add(Squirrel12);

        return enemies;
    }

    // Enhanced Map Tiles

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        // commented out cause this shouldnt be here from the start and it should get added when you talk to the altar
        // Ladder ladder = new Ladder(new Point(570,330));
        // enhancedMapTiles.add(ladder);

        return enhancedMapTiles;
    }


    // Triggers
    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        // Add triggers here
        triggers.add(new Trigger(0, 96, 20, 144, new Beat2(), "beatLvl2"));

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
