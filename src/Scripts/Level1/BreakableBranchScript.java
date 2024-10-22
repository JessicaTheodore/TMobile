package Scripts.Level1;

import java.util.ArrayList;

import Level.Script;
import Utils.Visibility;
import ScriptActions.*;


// Script to make the BreakableBranch break when hit by the player
// In the future, this won't be a script it will be an enemy based off hitbox of the ranged attack
public class BreakableBranchScript extends Script {
    
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new NPCChangeVisibilityScriptAction(Visibility.HIDDEN));
        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("brokeBranch", false));
                addScriptAction(new ChangeFlagScriptAction("brokeBranch", true));
            }});

            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                //addRequirement(new FlagRequirement("brokeLog", true));
                // addScriptAction(new TextboxScriptAction("I sure love doing walrus things!"));
            }});
        }});

        return scriptActions;
    }


}
