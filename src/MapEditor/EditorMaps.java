package MapEditor;

import Level.Map;
import Maps.Level1;
import Maps.PauseScreenMap;
import Maps.TestMap;
import Maps.TitleScreenMap;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("TestMap");
            add("TitleScreen");
            add("Level1");
            add("PauseScreen");
        }};
    }

    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "TestMap":
                return new TestMap();
            case "TitleScreen":
                return new TitleScreenMap();
            case "Level1":
                return new Level1();
            case "PauseScreen":
                return new PauseScreenMap();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
