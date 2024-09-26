package Tilesets;

import java.util.ArrayList;

import GameObject.Frame;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import Level.TileType;
import Level.Tileset;
import Builders.FrameBuilder;

public class WaterTileset extends Tileset {

    public WaterTileset(){
        super(ImageLoader.load("water.png"),16,16,3);
    }
    
    @Override
    public ArrayList<MapTileBuilder> defineTiles(){
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // water tile
        Frame waterFrame = new FrameBuilder(getSubImage(0,0))
            .withScale(tileScale)
            .build();

        MapTileBuilder waterTile = new MapTileBuilder(waterFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(waterTile);

        return mapTiles;

    }

}
