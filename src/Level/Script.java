package Level;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.ScriptAction;
import ScriptActions.ScriptActionOutputManager;

// This class is a base class for all scripts in the game -- all scripts should extend from it
// Scripts can be used to interact with map entities
// Each script defines a set of instructions that will be carried out by the game when it is set to active
// Some examples include interact scripts (such as talking to an NPC) and trigger scripts (scripts that activate when the player walks on them)
public abstract class Script {
    protected ArrayList<ScriptAction> scriptActions;
    private int currentScriptActionIndex;

    // this is set to true if script is currently being executed
    protected boolean isActive = false;

    // if true, script should perform "setup" logic
    protected boolean start = true;

    // references to the map entity the script is attached to
    protected MapEntity entity;

    // reference to the map instance which can be used in any script
    protected Map map;

    // reference to the player instance which can be used in any script
    protected Player player;

    protected ScriptActionOutputManager scriptActionOutputManager;

    public Script() {
        scriptActionOutputManager = new ScriptActionOutputManager();
    }

    public Map getMap() { return map; }
    public void setMap(Map map) { this.map = map; }
    public Player getPlayer() { return player; }
    public void setPlayer(Player player) { this.player = player; }
    public MapEntity getEntity() { return entity; }
    public void setMapEntity(MapEntity entity) { this.entity = entity; }
    
    public ArrayList<ScriptAction> getScriptActions() {
        return scriptActions;
    }
    public ScriptActionOutputManager getScriptActionOutputManager() {
        return scriptActionOutputManager;
    }

    public void initialize() {
        // load script actions from subclass
        scriptActions = loadScriptActions();

        // recursively iterate through all script actiohns and set the necessary properties on them
        // the recursive part is needed due to conditionals having nested script actions, and those conditionals can have nested conditionals, etc.
        Queue<ScriptAction> scriptActionsToInitialize = new LinkedList<>();
        for (ScriptAction scriptAction : scriptActions) {
            scriptActionsToInitialize.add(scriptAction);
        }
        while (!scriptActionsToInitialize.isEmpty()) {
            ScriptAction scriptAction = scriptActionsToInitialize.poll();
            scriptAction.setMap(map);
            scriptAction.setPlayer(player);
            scriptAction.setEntity(entity);
            scriptAction.setOutputManager(scriptActionOutputManager);
            if (scriptAction instanceof ConditionalScriptAction) {
                ConditionalScriptAction conditionalScriptAction = (ConditionalScriptAction)scriptAction;
                for (ConditionalScriptActionGroup conditionalScriptActionGroup : conditionalScriptAction.getConditionalScriptActionGroups()) {
                    for (ScriptAction conditionalScriptActionGroupScriptAction : conditionalScriptActionGroup.getScriptActions()) {
                        scriptActionsToInitialize.add(conditionalScriptActionGroupScriptAction);
                    }
                }
            }
        }
    }

    public abstract ArrayList<ScriptAction> loadScriptActions();

    private boolean hasScriptActions() {
        return scriptActions.size() > 0;
    }

    public void update() {
        // Runs an execute cycle of the Script
        ScriptAction currentScriptAction = scriptActions.get(currentScriptActionIndex);
        ScriptState scriptState = currentScriptAction.execute();
        if (scriptState == ScriptState.COMPLETED) {
            currentScriptAction.cleanup();
            currentScriptActionIndex++;

            if (currentScriptActionIndex < scriptActions.size()) {
                scriptActions.get(currentScriptActionIndex).setup();
            }
            else {
                this.isActive = false;
                map.setActiveScript(null);
            }
        }
    }

    // if is active is true, game will execute script
    public boolean isActive() { return isActive; }

    public void setIsActive(boolean isActive) { 
        if (isActive && hasScriptActions()) {
            this.isActive = isActive; 
            this.currentScriptActionIndex = 0;
            scriptActions.get(currentScriptActionIndex).setup();
        }
        else {
            this.isActive = isActive; 
        }
    } 
}
