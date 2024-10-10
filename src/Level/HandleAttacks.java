package Level;

import GameObject.Frame;
import GameObject.SpriteSheet;

import java.util.HashMap;

// This class is a base class for all enemies in the game -- all enemies should extend from it
public class HandleAttacks extends MapEntity {

    public HandleAttacks(float x, float y, SpriteSheet spriteSheet, String startingAnimation) {
        super(x, y, spriteSheet, startingAnimation);
    }

    public HandleAttacks(float x, float y, HashMap<String, Frame[]> animations, String startingAnimation) {
        super(x, y, animations, startingAnimation);
    }

    public HandleAttacks(float x, float y, Frame[] frames) {
        super(x, y, frames);
    }

    public HandleAttacks(float x, float y, Frame frame) {
        super(x, y, frame);
    }

    public HandleAttacks(float x, float y) {
        super(x, y);
    }

    @Override
    public void initialize() {
        super.initialize();
    }
}

