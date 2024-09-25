package Maps;

import java.util.ArrayList;

import Level.Map;
import Level.Trigger;
import Scripts.Level1.IntroScript;
import Tilesets.CommonTileset;
import Utils.Point;

public class Level1 extends Map {

    public Level1() {
        super("lvl1.txt", new CommonTileset());
        this.playerStartPosition = new Point(460, 2290);
    }

        @Override
    public ArrayList<Trigger> loadTriggers() {
        ArrayList<Trigger> triggers = new ArrayList<>();
        triggers.add(new Trigger(950, 950, 100, 100, new IntroScript(), "gameStart"));
        return triggers;
    }
}