package Scripts.Level1;

import java.util.ArrayList;

import Level.Script;
import Utils.Visibility;
import ScriptActions.*;


// Script to make the BreakableLog break when hit by the player
public class BreakableLogScript extends Script {
    
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{

                // these requirements may not be neccessary depending on how the script is activated in the first place
                // these flags below dont exist yet 
                // addRequirement(new FlagRequirement("touchingLog", true));
                // addRequirement(new FlagRequirement("attackingLog", true));

                addScriptAction(new WaitScriptAction(100));
                addScriptAction(new NPCChangeVisibilityScriptAction(Visibility.HIDDEN));
            }});
        }});
    
        return scriptActions;
    }


}
