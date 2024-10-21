package ScriptActions;

import Level.Enemy;
import Level.ScriptState;
import Utils.Visibility;

public class EnemyChangeVisibilityScriptAction extends ScriptAction {

    protected Enemy enemy;
    protected Visibility visibility;

    public EnemyChangeVisibilityScriptAction(Visibility visibility) {
        this.visibility = visibility;
    }

    public EnemyChangeVisibilityScriptAction(int enemyId, Visibility visibility) {
        this.enemy = map.getEnemiesById(enemyId);
        this.visibility = visibility;
    }
    
    @Override
    public void setup() {
        if (this.enemy == null) {
            this.enemy = (Enemy)entity;
        }
    }

    @Override
    public ScriptState execute() {
        if (visibility == Visibility.VISIBLE) {
            enemy.setIsHidden(false);
        }
        else {
            enemy.setIsHidden(true);
        }
        return ScriptState.COMPLETED;
    }
}