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
import NPCs.BreakableLog;
//<<<<<<< HEAD
import NPCs.Slingshot;

//=======
import NPCs.Bear;
import NPCs.BreakableBranch;
//>>>>>>> 9f8546ec7260beeeda6ff2a4f2a68147d9cb2568
// import NPCs.Bug;
// import NPCs.Dinosaur;
// import Scripts.TestMap.BugScript;
// import Scripts.TestMap.DinoScript;
// import Scripts.TestMap.WalrusScript;
import Scripts.Level1.BreakableLogScript;
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
        ArrayList<Enemy> enemies = new ArrayList<>();

        BreakableLog breakableLog = new BreakableLog(0, getMapTile(25,37).getLocation());
        breakableLog.setInteractScript(new BreakableLogScript());
        npcs.add(breakableLog);

        BearEnemy bear = new BearEnemy(0, 2, getMapTile(48,35).getLocation());
        enemies.add(bear);
        
        Slingshot slingshot = new Slingshot(1, getMapTile(48, 40).getLocation());
        // slingshot.setInteractScript(new PickUpSlingShot());
        npcs.add(slingshot);
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

        @Override
        public ArrayList<Trigger> loadTriggers() {
            ArrayList<Trigger> triggers = new ArrayList<>();
            // triggers.add(new Trigger(470, 2300, 100, 100, new IntroScript(), "gameStart"));
            return triggers;
    }
}