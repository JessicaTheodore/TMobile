package Maps;

import Level.Map;
import Tilesets.CommonTileset;
import Utils.Point;

public class Level1 extends Map {

    public Level1() {
        super("lvl1.txt", new CommonTileset());
        this.playerStartPosition = new Point(10, 10);
    }

}