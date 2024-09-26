package Scripts.Level1;

    import java.util.ArrayList;
    import Level.Script;
    import ScriptActions.*;

public class IntroScript extends Script {
    
        @Override
        public ArrayList<ScriptAction> loadScriptActions() {
            ArrayList<ScriptAction> scriptActions = new ArrayList<>();
            scriptActions.add(new LockPlayerScriptAction());
    
            scriptActions.add(new TextboxScriptAction() {{
                addText("Park Ranger: Heading up the Giant, huh? ");
                addText("Player: Beautiful day for some hiking, I think.");
                addText("R: …Hiking? Say, the park closes in 3 hours. You\nwon't make it back in time, why don't\n you come back another day?");
                addText("Player: *Laughs.* I've hiked harder trails faster than that. Thanks, but I'll be fine.  ");
                addText("R: Listen, I'll be frank with you, boy. Strange things are happening on that mountain. It's not wise to head up the Giant now. ");
                addText("Player: Strange things? ");
                addText("R: Rumors have been spreading by those who make it off the mountain… Grizzlies with glowing eyes, whispering trees attacking those who come too close… I've even seen some just like you go up and never come back.");
                addText("R: Some say the legend is to blame. ");
                addText("Player: The legend? ");
                addText("R: … ");
                addText("R: The Legend of Hobbomock. ");
                addText("Player: …Who?");
                addText("R: That's precisely the point, boy. You're messing with things you don't understand. Stories that are coming to life… Spirits that are waking up. ");
                addText("Player: Whatever man- people have made up crazier rumors on campus. I heard something about a pipe bomb last week?\t ");
                addText("Player: Besides, I'm going on my hike, some random warnings won't stop me. ");
                addText("R: Say, what's your name boy? ");
                addText("R: Ok ____ ... you seem like a nice kid. Take this, it could come in handy.  ");
                addText("R: If you can keep your wits about you, go ahead and climb the Giant… ignore the warnings and the legends.  ");
                addText("R: Just don't say I didn't warn you.");
            }});
    
            scriptActions.add(new ChangeFlagScriptAction("gameStart", true));
    
            scriptActions.add(new UnlockPlayerScriptAction());
    
            return scriptActions;
        }
}
