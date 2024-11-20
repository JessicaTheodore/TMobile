package Maps;

import java.util.ArrayList;
import Engine.GraphicsHandler;
import GameObject.Sprite;
import Level.Map;
import Level.NPC;
import Level.Enemy;
import Level.Trigger;
import Tilesets.MainTileset;
import Enemies.BearEnemy;
import Enemies.BreakableBranch;
import Enemies.BreakableLog;
import NPCs.BrokenBranch;
import NPCs.Slingshot;
import Scripts.Level1.BreakLog;
import Scripts.Level1.BrokeLog;
import Scripts.Level1.NearSlingShot;
import Scripts.Level1.NextLevel;
import Scripts.Level1.PickUpSlingShot;
import Utils.Point;
import Level.Player; // Import the Player class

public class Level1 extends Map {

    protected Sprite ranger;
    protected float x;
    protected float y;
    BreakableLog log;

    public Level1() {
        super("lvl1.txt", new MainTileset());
        this.playerStartPosition = new Point(460, 2290);
    }

    public Level1(float x, float y) {
        super("lvl1.txt", new MainTileset());
        this.x = x;
        this.y = y;
        this.playerStartPosition = new Point(460, 2290);
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        Slingshot slingshot = new Slingshot(1, getMapTile(10, 26).getLocation());
        slingshot.setInteractScript(new PickUpSlingShot());
        npcs.add(slingshot);

        BrokenBranch brokenBranch = new BrokenBranch(2, new Point(480, 25));
        npcs.add(brokenBranch);

        return npcs;
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        // Define a radius for the circular movement
        float bearMovementRadius = 50.0f; // Adjust this value as needed

        BearEnemy bear2 = new BearEnemy(1, 2, getMapTile(6, 26).getLocation(), bearMovementRadius);
        enemies.add(bear2);

        BearEnemy bear3 = new BearEnemy(2, 2, getMapTile(9, 28).getLocation(), bearMovementRadius);
        enemies.add(bear3);

        BearEnemy bear4 = new BearEnemy(3, 2, getMapTile(13, 27).getLocation(), bearMovementRadius);
        enemies.add(bear4);

        BearEnemy bear5 = new BearEnemy(4, 2, getMapTile(13, 23).getLocation(), bearMovementRadius);
        enemies.add(bear5);

        // Adding breakable objects
        log = new BreakableLog(5, 1, new Point(1150, 1750));
        log.setInteractScript(new BrokeLog());
        enemies.add(log);
        BreakableBranch branch = new BreakableBranch(6, 1, new Point(480, 25));
        enemies.add(branch);

        return enemies;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(0, 00, 5000, 80, new NextLevel(), "beatLvl1"));
        //triggers.add(new Trigger(1335, 1860, 70, 180, new BrokeLog(), "brokeLog"));
        triggers.add(new Trigger(1270, 1850, 20, 190, new BreakLog(), "SpaceBreak"));
        triggers.add(new Trigger(477, 1257, 37, 32, new NearSlingShot(), "nearSlingShot"));
        return triggers;
    }

    // Override the update method to take Player as an argument
    @Override
    public void update(Player player) {
        // System.out.println("Update method in Level1 called.");
        // Level-specific update logic here
        super.update(player); // If you want to call the superclass method
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        for (int i = 0; i <= triggers.size(); i++) {
            triggers.get(i).draw(graphicsHandler);
        }
    }
}