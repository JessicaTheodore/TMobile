package MapEditor;

import Level.Map;
import Maps.Floor1;
import Maps.Level1;
import Maps.Level2;
import Maps.Level3;
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
            add("Level2");
            add("Level3");
            add("Floor1");
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
            case "Level2":
                return new Level2();
            case "Level3":
                return new Level3();
            case "Floor1":
                return new Floor1();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
