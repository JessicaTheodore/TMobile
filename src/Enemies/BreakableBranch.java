package Enemies;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntityStatus;
import Level.NPC;
import Utils.Point;

import java.awt.Color;
import java.util.HashMap;

public class BreakableBranch extends Enemy {
    protected int branchHP;

    public BreakableBranch(int id, int hp, Point location) {
        super(id, hp, location.x, location.y, new SpriteSheet(ImageLoader.load("breakablebranch.png"), 80, 32), "FACING_RIGHT");
        this.branchHP = hp;
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("FACING_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(8)
                            .withBounds(0, 50, 80, 28)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
        }};
    }

    @Override 
    public void hurtEnemy() {
        this.branchHP--;
        if(branchHP == 0){
            mapEntityStatus = MapEntityStatus.REMOVED;
            // then it needs to spawn in an enhanced map tile over the river tiles
        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);

        // DRAWS HITBOX
        drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
    }

    
}






