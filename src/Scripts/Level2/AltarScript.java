package Scripts.Level2;

import java.util.ArrayList;

import EnhancedMapTiles.Ladder;
import Level.Script;
import Level.ScriptState;
import Level.TileType;
import NPCs.Altar;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.FlagRequirement;
import ScriptActions.LockPlayerScriptAction;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import ScriptActions.UnlockPlayerScriptAction;
import Utils.Point;

public class AltarScript extends Script {
    
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
                scriptActions.add(new LockPlayerScriptAction());


        // Text
        scriptActions.add(new TextboxScriptAction() {{
            addText("Hello!");
        }});    
        // Ladder & Tiles
        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasTalkedToAltar", false));

                addScriptAction(new ScriptAction() {
                    @Override
                    public ScriptState execute() {

                        Ladder ladder = new Ladder(new Point(570,330));
                        ladder.setMap(map);
                        map.addEnhancedMapTile(ladder);
        
                        map.getMapTile(12,7).setTileType(TileType.PASSABLE);
                        map.getMapTile(12,8).setTileType(TileType.PASSABLE);
                        map.getMapTile(13,7).setTileType(TileType.PASSABLE);
        
                        
                        return ScriptState.COMPLETED;
                    }
                });
                addScriptAction(new ChangeFlagScriptAction("hasTalkedToAltar", true));


            }});
        }});
                scriptActions.add(new UnlockPlayerScriptAction());


        return scriptActions;
    }
}