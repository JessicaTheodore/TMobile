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
                addText("Player: *Laughs.* I've hiked harder trails faster \nthan that. Thanks, but I'll be fine.  ");
                addText("R: Listen, I'll be frank with you, boy. \nStrange things are happening on that mountain. \nIt's not wise to head up the Giant now. ");
                addText("Player: Strange things? ");
                addText("R: Rumors have been spreading by those who make \nit off the mountain… Grizzlies with glowing eyes, whispering trees \nattacking those who come too close… I've even seen some \njust like you go up and never come back.");
                addText("R: Some say the legend is to blame. ");
                addText("Player: The legend? ");
                addText("R: … ");
                addText("R: The Legend of Hobbomock. ");
                addText("Player: …Who?");
                addText("R: That's precisely the point, boy. \nYou're messing with things you don't understand. \nStories that are coming to life… \nSpirits that are waking up. ");
                addText("Player: Whatever man- people have made up\n crazier rumors on campus. I heard \nsomething about a pipe bomb last week?");
                addText("Player: Besides, I'm going on my\n hike, some random warnings won't stop me. ");
                addText("R: Say, what's your name boy? ");
                addText("R: Ok ____ ... you seem like a nice \nkid. Take this, it could come in handy.  ");
                addText("R: If you can keep your wits about \nyou, go ahead and climb the Giant… \nignore the warnings and the legends.  ");
                addText("R: Just don't say I didn't warn you.");
            }});
    
            scriptActions.add(new ChangeFlagScriptAction("gameStart", true));
    
            scriptActions.add(new UnlockPlayerScriptAction());
    
            return scriptActions;
        }
}
