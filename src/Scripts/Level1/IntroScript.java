package Scripts.Level1;

    import java.util.ArrayList;
    import Level.Script;
    import ScriptActions.*;

public class IntroScript extends Script {
    
        @Override
        public ArrayList<ScriptAction> loadScriptActions() {
            ArrayList<ScriptAction> scriptActions = new ArrayList<>();
            // scriptActions.add(new LockPlayerScriptAction());
    
            // scriptActions.add(new TextboxScriptAction() {{
            //     addText("Park Ranger: Heading up the Giant, huh? ");
            //     addText("Player: Beautiful day for some hiking, I think.");
            //     addText("R: …Hiking? Say, the park closes in 3 hours. You\nwon’t make it back in time, why don’t\n you come back another day?");
            // }});
    
            scriptActions.add(new ChangeFlagScriptAction("gameStart", true));
    
            scriptActions.add(new UnlockPlayerScriptAction());
    
            return scriptActions;
        }
}
