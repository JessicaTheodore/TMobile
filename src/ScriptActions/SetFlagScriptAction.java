package ScriptActions;



import Level.FlagManager;

// Custom script action to set a flag to true
public class SetFlagScriptAction extends ScriptAction {
    private String flagName;

    public SetFlagScriptAction(String flagName) {
        this.flagName = flagName;
    }

  //  @Override
    public void execute(FlagManager flagManager) {
        flagManager.setFlag(flagName); // Sets the flag to true
    }
}
