package Scripts.Level3;

import java.util.ArrayList;

import Level.Script;
import Utils.Visibility;
import ScriptActions.*;


// Script to make the BreakableLog break when hit by the player
// In the future, this won't be a script it will be based off hitbox of the stick attack
public class ExitFloor2 extends Script {
    
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addScriptAction(new ChangeFlagScriptAction("exitFloor2", true));
                addScriptAction(new ChangeFlagScriptAction("enterFloor2", false));

            }});
        }});

        return scriptActions;
    }


}
