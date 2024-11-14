package Enemies;

import java.util.HashMap;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntityStatus;
import Level.Script;
import Scripts.Level1.BrokeLog;
import Utils.Point;
import java.awt.Color;

public class BreakableLog extends Enemy {
    protected int logHP;
    public BreakableLog(int id, int hp, Point location) {
        super(id, hp, location.x, location.y, new SpriteSheet(ImageLoader.load("breakablelog.png"), 100, 100), "FACING_RIGHT");
        this.logHP = hp;
        this.setInteractScript(new BrokeLog());
    }

    @Override
    public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("FACING_RIGHT", new Frame[] {
                    new FrameBuilder(spriteSheet.getSprite(0, 0))
                            .withScale(4)
                            .withBounds(43, 28, 15, 41)
                            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
                            .build()
            });
        }};
    }

    @Override
    public void hurtEnemy(){
        this.logHP--;
        if(this.logHP == 0){
            map.getPlayer().setInteractionRange();
            map.entityInteract(map.getPlayer());
            map.getPlayer().resetInteractionRange();
            mapEntityStatus = MapEntityStatus.REMOVED;
            //this.getInteractScript().setIsActive(true);
        }
    }

    // @Override
    // public void draw(GraphicsHandler graphicsHandler) {
    //     super.draw(graphicsHandler);

    //     // DRAWS HITBOX
    //     drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
    // }
}
