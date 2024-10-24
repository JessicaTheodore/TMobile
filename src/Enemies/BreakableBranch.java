package Enemies;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import EnhancedMapTiles.BranchBridgeTile;
import GameObject.Frame;
import GameObject.ImageEffect;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.EnhancedMapTile;
import Level.MapEntityStatus;
import Level.NPC;
import Level.TileType;
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
                            .withBounds(0, 20, 80, 28)
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
            
            BranchBridgeTile bridge = new BranchBridgeTile(new Point(720,80));
            bridge.setMap(map);
            map.addEnhancedMapTile(bridge);

            map.getMapTile(15,3).setTileType(TileType.PASSABLE);
            map.getMapTile(16,3).setTileType(TileType.PASSABLE);
            map.getMapTile(17,3).setTileType(TileType.PASSABLE);

            map.getMapTile(15,4).setTileType(TileType.PASSABLE);
            map.getMapTile(16,4).setTileType(TileType.PASSABLE);
            map.getMapTile(17,4).setTileType(TileType.PASSABLE);

            map.getMapTile(15,5).setTileType(TileType.PASSABLE);
            map.getMapTile(16,5).setTileType(TileType.PASSABLE);
            map.getMapTile(17,5).setTileType(TileType.PASSABLE);

            map.getMapTile(15,6).setTileType(TileType.PASSABLE);
            map.getMapTile(16,6).setTileType(TileType.PASSABLE);
            map.getMapTile(17,6).setTileType(TileType.PASSABLE);

        }
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);

        // DRAWS HITBOX
        drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
    }

    
}






