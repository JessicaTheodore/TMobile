package Game;

import java.util.ArrayList;

import Engine.DefaultScreen;
import Engine.GraphicsHandler;
import Engine.Screen;
import Level.FlagManager;
import Level.Map;
import Maps.Level1;
import Maps.Level2IntroScreenMap;
import Maps.Level3;
import Screens.ControlsHome;
import Screens.ControlsScreen;
import Screens.CreditsScreen;
import Screens.FinalSceneScreen;
import Screens.Floor1Screen;
import Screens.Floor2Screen;
import Screens.GameOverScreen;
import Screens.MenuScreen;
import Screens.PauseScreen;
import Screens.PlayLevelScreen;
import ScriptActions.ChangeFlagScriptAction;
import ScriptActions.ConditionalScriptAction;
import ScriptActions.ConditionalScriptActionGroup;
import ScriptActions.FlagRequirement;
import ScriptActions.ScriptAction;
import ScriptActions.TextboxScriptAction;
import Screens.IntroductionScreen;
import Screens.Level1Screen;
import Screens.Level2DialogueScreen;
import Screens.Level2IntroScreen;
import Screens.Level2PlayScreen;
import Screens.Level3PlayScreen;
import Screens.Level3Screen;
import Screens.LevelCompleteScreen;
import Screens.LevelLoseScreen;

/*
 * Based on the current game state, this class determines which Screen should be shown
 * There can only be one "currentScreen", although screens can have "nested" screens
 */
public class ScreenCoordinator extends Screen {
	// currently shown Screen
	protected Screen currentScreen = new DefaultScreen();
	protected boolean level1start = true;
	protected Screen hold;
	protected Screen level;
	ArrayList<ScriptAction> scriptActions = new ArrayList<>();
	protected FlagManager flagManager = new FlagManager();
	protected boolean beatLvl1 = false;
	protected boolean beatLvl2 = false;
	protected boolean beenInFloor1 = false;
	protected Screen floor1;
	protected Screen level3;
	protected Map holdMap;


	// keep track of gameState so ScreenCoordinator knows which Screen to show
	protected GameState gameState;
	protected GameState previousGameState;

	public GameState getGameState() {
		return gameState;
	}

	// Other Screens can set the gameState of this class to force it to change the currentScreen
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	@Override
	public void initialize() {
		// start game off with Menu Screen
		gameState = GameState.MENU;
		flagManager.addFlag("fresh", true);
	}

	@Override
	public void update() {
		do {
			// if previousGameState does not equal gameState, it means there was a change in gameState
			// this triggers ScreenCoordinator to bring up a new Screen based on what the gameState is
			if (previousGameState != gameState) {
				switch(gameState) {
					case MENU:
						currentScreen = new MenuScreen(this);
						break;
					case LEVEL:
						currentScreen = level;
						break;
					case NEWLEVEL:
					if(!beatLvl1){
						currentScreen = new PlayLevelScreen(this);
					}else if(!beatLvl2){
						currentScreen = new Level2PlayScreen(this);
					}else{
						currentScreen = new Level3PlayScreen(this);
					}
						level = currentScreen;
						break;
					case LEVEL2:
						currentScreen = new Level2PlayScreen(this);
						level = currentScreen;
						break; 
					case CREDITS:
						currentScreen = new CreditsScreen(this);
						break;
					case INTRO:
						currentScreen = new IntroductionScreen(this);
						break;
					case PAUSE:
						currentScreen = new PauseScreen(this);
						break;
					case CONTROLS:
						currentScreen = new ControlsScreen(this);
						break;
					case CONTROLSHOME:
						currentScreen = new ControlsHome(this);
						break;
					case GAMEOVER:
						currentScreen = new GameOverScreen(this);
						break;
					case DEATH:
						currentScreen = new LevelLoseScreen(this);
						break;
					case LEVELCOMPLETE:
						if(!beatLvl1){
							beatLvl1 = true;
						}else if(!beatLvl2){
							beatLvl2 = true;
						}
						currentScreen = new LevelCompleteScreen(this);
						break;
					case LEVEL2DIALOGUE:
						currentScreen = new Level2DialogueScreen(this);
						break;
					case LEVEL1SCREEN:
						currentScreen = new Level1Screen(this);
						break;
					case LEVEL2INTROSCREEN:
						currentScreen = new Level2IntroScreen(this);
						break;
					case LEVEL3:
						if(beenInFloor1){
							currentScreen = level3;
						}else{
							currentScreen = new Level3PlayScreen(this);
						}
						level = currentScreen;
						break;
					case FLOOR1:
						if(!beenInFloor1){
							level3 = currentScreen;
							beenInFloor1 = true;
							currentScreen = new Floor1Screen(this);
							floor1 = currentScreen;
						}else{
							currentScreen = floor1;
						}
						level = currentScreen;
						break;
					case FLOOR2:
						currentScreen = new Floor2Screen(this);
						level = currentScreen;
						break;
					case FINALSCENE:
						currentScreen = new FinalSceneScreen(this);
						break;
					 case LEVEL3INTROSCREEN:
						currentScreen = new Level3Screen(this);
					default:
						break;
				}
				currentScreen.initialize();
			}
			previousGameState = gameState;

			// call the update method for the currentScreen
			currentScreen.update();
		} while (previousGameState != gameState);
	}

	@Override
	public void draw(GraphicsHandler graphicsHandler) {
		// call the draw method for the currentScreen
		currentScreen.draw(graphicsHandler);
	}

	public boolean beatLvl1(){
		return beatLvl1;
	}

	public void toggleLvl1(){
		beatLvl1 = !beatLvl1;
	}

	public boolean beatLvl2(){
		return beatLvl2;
	}

	public void toggleLvl2(){
		beatLvl2 = !beatLvl2;
	}


}
