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

        EvilMushroom shroom2 = new EvilMushroom(0,1, new Point(300,1700));
        enemies.add(shroom2);

        SquirrelEnemy Squirrel1 = new SquirrelEnemy (0,1, new Point(1500,1700), 10);
        enemies.add(Squirrel1);

        SquirrelEnemy Squirrel2 = new SquirrelEnemy (0,1, new Point(1650,1500), 10);
        enemies.add(Squirrel2);

        SquirrelEnemy Squirrel3 = new SquirrelEnemy (0,1, new Point(1250,1500), 10);
        enemies.add(Squirrel3);


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
