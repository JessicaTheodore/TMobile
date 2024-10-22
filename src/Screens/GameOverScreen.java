package Screens;

import Engine.GraphicsHandler;
import Engine.Screen;
import Game.ScreenCoordinator;
import SpriteFont.SpriteFont;
import java.awt.Color;

public class GameOverScreen extends Screen {
    private ScreenCoordinator screenCoordinator;
    private SpriteFont gameOverMessage;

    public GameOverScreen(ScreenCoordinator screenCoordinator) {
        this.screenCoordinator = screenCoordinator;
    }

    @Override
    public void initialize() {
        gameOverMessage = new SpriteFont("Game Over", 200, 200, "Arial", 50, Color.RED);
    }

    @Override
    public void update() {
        // Logic to restart the game or return to the menu.
        if (Keyboard.isKeyDown(Key.ENTER)) {
            screenCoordinator.setGameState(GameState.MENU);
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        gameOverMessage.draw(graphicsHandler);
    }
}
