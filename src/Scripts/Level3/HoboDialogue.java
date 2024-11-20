package Scripts.Level3;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import EnhancedMapTiles.Ladder;
import ScriptActions.*;
import SpriteFont.SpriteFont;
import Level.Script;
import Level.ScriptState;
import Level.TileType;
import Utils.Point;

public class HoboDialogue extends Script {

    //private Font maruMonica;
    
    @Override
    public ArrayList<ScriptAction> loadScriptActions() {

        ArrayList<ScriptAction> scriptActions = new ArrayList<>();
        scriptActions.add(new LockPlayerScriptAction());

        scriptActions.add(new ConditionalScriptAction() {{
            addConditionalScriptActionGroup(new ConditionalScriptActionGroup() {{
                addRequirement(new FlagRequirement("hasTalkedToHobo", false));
                addScriptAction(new TextboxScriptAction() {{
                    addText("Hobbomock: My oh my, what a surprise seeing you here, \nplayer.");
                    addText("Player: …Hobbomock?");
                    addText("Hobbomock: Indeed, it is I, the Sleeping Giant.");
                    addText("Player: …You seem awfully calm for an evil spirit who was \njust tried to kill me using my school's mascot.");
                    addText("Hobbomock: In my defense, your school has been causing \nquite an issue for me.");
                    addText("Player: What do you mean, we've been causing issues for \nyou?");
                    addText("Hobbomock: Your school's expansion disrupted the harmony \nof this mountain.");
                    addText("Hobbomock: Forests torn down, waters polluted...\n It awoke me from my slumber.");
                    addText("Player: That… doesn't sound great. But unleashing mascots \nand evil squirrels wasn't exactly great either.");
                    addText("Hobbomock: Fair point. I let my anger guide me—perhaps too \nmuch.");
                    addText("Hobbomock: I am sorry for the pain I've caused… perhaps I've \nbeen too cruel.");
                    addText("Hobbomock: I'll release the spirits of the forest.");
                    addText("Hobbomock: …I'm afraid I can't undo the quality of the caf \nfood");
                    addText("Hobbomock: or the hockey team's losing streak");
                    addText("Hobbomock: or Judy's bike accident...");
                    addText("Player: That was you???");
                    addText("Hobbomock: Of course.");
                    addText("Hobbomock: Regardless, I am sorry for the pain I have\n caused.");
                    addText("Player: Thank you for saying that, it takes a lot to \nown up to mistakes.");
                    addText("Player: On behalf of our school, we apologize as well and \nwill do better.");
                    addText("Hobbomock: Thank you, player. I will go quietly now.");

                }});
                addScriptAction(new ChangeFlagScriptAction("hasTalkedToHobo", true));
            }});
        }});
        scriptActions.add(new UnlockPlayerScriptAction());

        return scriptActions;

    }
}