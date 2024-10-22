package Game;

import java.util.ArrayList;

import Engine.DefaultScreen;
import Engine.GraphicsHandler;
import Engine.Screen;
import Level.FlagManager;
import Screens.ControlsHome;
import Screens.ControlsScreen;
import Screens.CreditsScreen;
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

/*
 * Based on the current game state, this class determines which Screen should be shown
 * There can only be one "currentScreen", although screens can have "nested" screens
 */
public class ScreenCoordinator extends Screen {
	// currently shown Screen
	protected Screen currentScreen = new DefaultScreen();
	protected boolean level1start = true;
	protected Screen hold;
	protected Screen level1 = new PlayLevelScreen(this);
	ArrayList<ScriptAction> scriptActions = new ArrayList<>();
	protected FlagManager flagManager = new FlagManager();


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
		level1.initialize();
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
						currentScreen.initialize();
						break;
					case LEVEL:
						currentScreen = level1;
						break;
					case CREDITS:
						currentScreen = new CreditsScreen(this);
						currentScreen.initialize();
						break;
					case INTRO:
						currentScreen = new IntroductionScreen(this);
						currentScreen.initialize();
						break;
					case PAUSE:
						currentScreen = new PauseScreen(this);
						currentScreen.initialize();
						break;
					case CONTROLS:
						currentScreen = new ControlsScreen(this);
						currentScreen.initialize();
						break;
					case CONTROLSHOME:
						currentScreen = new ControlsHome(this);
						currentScreen.initialize();
						break;
				}
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
}
