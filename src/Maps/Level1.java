package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Trigger;
import Scripts.SimpleTextScript;
import Scripts.Level1.IntroScript;
import Scripts.TestMap.LostBallScript;
import Scripts.TestMap.TreeScript;
import Tilesets.CommonTileset;
import Utils.Point;

public class Level1 extends Map {

    public Level1() {
        super("lvl1.txt", new CommonTileset());
        this.playerStartPosition = new Point(1000, 1000);
    }

        @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(790, 1030, 100, 10, new IntroScript(), "gameStart"));
        triggers.add(new Trigger(790, 960, 10, 80, new IntroScript(), "gameStart"));
        triggers.add(new Trigger(890, 960, 10, 80, new IntroScript(), "gameStart"));
        return triggers;
    }

    // @Override
    // public void loadScripts() {
    //     getMapTile(21, 19).setInteractScript(new SimpleTextScript("Cat's house"));

    //     getMapTile(7, 26).setInteractScript(new SimpleTextScript("Walrus's house"));

    //     getMapTile(20, 4).setInteractScript(new SimpleTextScript("Dino's house"));

    //     getMapTile(2, 6).setInteractScript(new TreeScript());
    // }
}