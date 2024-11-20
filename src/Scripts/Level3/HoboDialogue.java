package Scripts.Level3;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import EnhancedMapTiles.Ladder;
import ScriptActions.*;
import SpriteFont.SpriteFont;
import Level.Script;
import Level.ScriptState;
import Level.TileType;
import Utils.Point;

public class HoboDialogue extends Script {

    //private Font maruMonica;
    
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasTalkedToHobo", false));
                addScriptAction(new TextboxScriptAction() {{
                    addText("what's up man?");
                }});
                addScriptAction(new ChangeFlagScriptAction("hasTalkedToHobo", true));
            }});
        }});
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;

    }
}