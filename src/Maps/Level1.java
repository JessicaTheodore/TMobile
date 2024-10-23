package Maps;

import java.awt.geom.Point2D;
import java.util.ArrayList;

// import Engine.GraphicsHandler;
// import Engine.ImageLoader;
// import Engine.ScreenManager;
import GameObject.Sprite;
import Level.Map;
import Level.NPC;
import Level.Enemy;
import Level.Trigger;
import Level.Trigger;
// import Scripts.Level1.IntroScript;
// import Tilesets.CommonTileset;
import Tilesets.MainTileset;
// import Utils.Colors;



import Enemies.BearEnemy;

import Utils.Point;
// import NPCs.Bear;
import NPCs.BrokenBranch;
//<<<<<<< HEAD
import NPCs.Slingshot;

//=======
import NPCs.Bear;
import NPCs.BranchBridge;
import Enemies.BreakableBranch;
import Enemies.BreakableLog;
//>>>>>>> 9f8546ec7260beeeda6ff2a4f2a68147d9cb2568
// import NPCs.Bug;
// import NPCs.Dinosaur;
// import Scripts.TestMap.BugScript;
// import Scripts.TestMap.DinoScript;
// import Scripts.TestMap.WalrusScript;
import Scripts.Level1.PickUpSlingShot;
import Scripts.Level1.BeatBear;
import Scripts.Level1.BreakableBranchScript;
import Scripts.TestMap.LostBallScript;

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

        BrokenBranch brokenBranch = new BrokenBranch(2, new Point(480,25));
        npcs.add(brokenBranch);

        BranchBridge bridge = new BranchBridge(3, new Point(720,80));
        npcs.add(bridge);

        // Bear bear = new Bear(1, getMapTile(4, 28).getLocation().subtractY(40));
        // bear.setInteractScript(new WalrusScript());
        // npcs.add(bear);

        // Dinosaur dinosaur = new Dinosaur(2, getMapTile(13, 4).getLocation());
        // dinosaur.setExistenceFlag("hasTalkedToDinosaur");
        // dinosaur.setInteractScript(new DinoScript());
        // npcs.add(dinosaur);
        
        // Bug bug = new Bug(3, getMapTile(7, 12).getLocation().subtractX(20));
        // bug.setInteractScript(new BugScript());
        // npcs.add(bug);

        return npcs;
    }

    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();

        BearEnemy bear1 = new BearEnemy(0, 2, getMapTile(13,54).getLocation());
        enemies.add(bear1);
        BearEnemy bear2 = new BearEnemy(1, 2, getMapTile(6,26).getLocation());
        enemies.add(bear2);
        BearEnemy bear3 = new BearEnemy(2, 2, getMapTile(9,28).getLocation());
        enemies.add(bear3);
        BearEnemy bear4 = new BearEnemy(3, 2, getMapTile(13,27).getLocation());
        enemies.add(bear4);
        BearEnemy bear5 = new BearEnemy(4, 2, getMapTile(13,23).getLocation());
        enemies.add(bear5);

        // This is the branch attatched to the tree that gets broken
        BreakableLog log = new BreakableLog(5, 1, getMapTile(10, 50).getLocation());
        enemies.add(log);

        return enemies;
    }

        @Override
        public ArrayList<Trigger> loadTriggers() {
            ArrayList<Trigger> triggers = new ArrayList<>();
            // triggers.add(new Trigger(470, 2300, 100, 100, new IntroScript(), "gameStart"));
            triggers.add(new Trigger(0, 00, 5000, 80, new NextLevel(), "beatLvl1"));

            return triggers;
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler){
        triggers.get(triggers.size()-1).draw(graphicsHandler);
    }
}