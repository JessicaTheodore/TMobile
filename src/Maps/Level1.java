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
import NPCs.BranchBridge;
import Scripts.Level1.PickUpSlingShot;
import Utils.Point;
import Level.Player; // Import the Player class

public class Level1 extends Map {

    protected Sprite ranger;
    protected float x;
    protected float y;

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

        Slingshot slingshot = new Slingshot(1, getMapTile(51, 48).getLocation());
        slingshot.setInteractScript(new PickUpSlingShot());
        npcs.add(slingshot);

        BrokenBranch brokenBranch = new BrokenBranch(2, new Point(480, 25));
        npcs.add(brokenBranch);

        BranchBridge bridge = new BranchBridge(3, new Point(720, 80));
        npcs.add(bridge);

        return npcs;
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
// Define a radius for the circular movement
float bearMovementRadius = 50.0f; // Adjust this value as needed

BearEnemy bear1 = new BearEnemy(0, 2, getMapTile(13, 54).getLocation(), bearMovementRadius);
enemies.add(bear1);

BearEnemy bear2 = new BearEnemy(1, 2, getMapTile(6, 26).getLocation(), bearMovementRadius);
enemies.add(bear2);

BearEnemy bear3 = new BearEnemy(2, 2, getMapTile(9, 28).getLocation(), bearMovementRadius);
enemies.add(bear3);

BearEnemy bear4 = new BearEnemy(3, 2, getMapTile(13, 27).getLocation(), bearMovementRadius);
enemies.add(bear4);

BearEnemy bear5 = new BearEnemy(4, 2, getMapTile(13, 23).getLocation(), bearMovementRadius);
enemies.add(bear5);



        // Adding breakable objects
        BreakableLog log = new BreakableLog(5, 1, getMapTile(24, 37).getLocation());
        enemies.add(log);
        BreakableBranch branch = new BreakableBranch(1, 1, new Point(480, 25));
        enemies.add(branch);

        return enemies;
    }

    @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
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
    }
}

