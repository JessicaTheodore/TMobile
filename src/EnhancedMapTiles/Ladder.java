package EnhancedMapTiles;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.ImageLoader;
import Level.EnhancedMapTile;
import Level.TileType;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.SpriteSheet;
import Utils.Point;

// Ladder that appears when you beat the puzzle on level 2
// Able to be climbed to escape the level
public class Ladder extends EnhancedMapTile{
    
    public Ladder(Point location){
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("ladder.png"), 64, 64), TileType.PASSABLE);
        tileType = TileType.PASSABLE;
    }

    @Override
    protected GameObject loadBottomLayer(SpriteSheet spriteSheet) {
        Frame frame = new FrameBuilder(spriteSheet.getSubImage(0, 0))
                .withScale(2)
                .withBounds(0,0,64,64)
                .build();
        return new GameObject(x, y, frame);
    }

    @Override
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
    }
}
