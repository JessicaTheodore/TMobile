package Maps;

import java.util.ArrayList;

import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Engine.ScreenManager;
import GameObject.Sprite;
import Level.Map;
import Level.Trigger;
import Scripts.Level1.IntroScript;
import Tilesets.CommonTileset;
import Utils.Colors;
import Utils.Point;

public class Level1 extends Map {

    protected Sprite ranger;

    public Level1() {
        super("lvl1.txt", new CommonTileset());
        this.playerStartPosition = new Point(460, 2290);
    }

        @Override
        public ArrayList<Trigger> loadTriggers() {
            ArrayList<Trigger> triggers = new ArrayList<>();
            triggers.add(new Trigger(470, 2300, 100, 100, new IntroScript(), "gameStart"));
            return triggers;
    }
}