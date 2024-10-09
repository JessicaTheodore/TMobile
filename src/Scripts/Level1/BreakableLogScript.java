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
                addScriptAction(new NPCChangeVisibilityScriptAction(Visibility.HIDDEN));
            }});
        }});
    

        return scriptActions;
    }


}
