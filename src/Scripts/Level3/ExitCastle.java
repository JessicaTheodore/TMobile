package Scripts.Level3;

import java.util.ArrayList;

import Level.Script;
import Utils.Visibility;
import ScriptActions.*;


// Script to make the BreakableLog break when hit by the player
// In the future, this won't be a script it will be based off hitbox of the stick attack
public class ExitCastle extends Script {
    
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addScriptAction(new ChangeFlagScriptAction("exitCastle", true));
                addScriptAction(new ChangeFlagScriptAction("enterFloor1", false));

            }});
        }});

        return scriptActions;
    }


}
