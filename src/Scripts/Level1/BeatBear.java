package Scripts.Level1;

import java.util.ArrayList;

import Level.Script;
import Utils.Visibility;
import ScriptActions.*;

//copied breaking log for now but will move to enemy class

// Script to make the BreakableLog break when hit by the player
// In the future, this won't be a script it will be based off hitbox of the stick attack
public class BeatBear extends Script {
    
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new NPCChangeVisibilityScriptAction(Visibility.HIDDEN));

        return scriptActions;
    }


}
