package EnhancedMapTiles;

import java.awt.Color;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Level.EnhancedMapTile;
import Level.TileType;
import Utils.Point;

// Bridge that the branch forms when you hit it
// Able to be walked over to cross the river
public class BranchBridgeTile extends EnhancedMapTile {

    public BranchBridgeTile(Point location) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("branchbridge.png"), 31, 68), TileType.PASSABLE);
        tileType = TileType.PASSABLE;
   }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(0, 0))
                .withScale(5)
                .withBounds(0,0,100,1000)
                .build();
        return new GameObject(x, y, frame);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);

        // DRAWS HITBOX
        drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
    }

}
