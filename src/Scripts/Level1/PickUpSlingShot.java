package Scripts.Level1;

import Level.Script;
import ScriptActions.*;
import Utils.Visibility;

import java.util.ArrayList;

public class PickUpSlingShot extends Script {
    
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {
        ArrayList<ScriptAction> scriptActions = new ArrayList<>();

        scriptActions.add(new NPCChangeVisibilityScriptAction(Visibility.HIDDEN));

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addScriptAction(new ChangeFlagScriptAction("pickedUpSlingShot", true));
            }});
        }});

        return scriptActions;
    }
}