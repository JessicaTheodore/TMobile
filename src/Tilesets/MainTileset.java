package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;

import java.util.ArrayList;

// This class contains all custom map tiles that make up the game
public class MainTileset extends Tileset {
    
    public MainTileset(){
        super(ImageLoader.load("MainTileset.png"), 16, 16, 3);
    }

    @Override
    public ArrayList<MapTileBuilder> defineTiles() {
        ArrayList<MapTileBuilder> mapTiles = new ArrayList<>();

        // Grass

        Frame grassFrame = new FrameBuilder(getSubImage(0, 0))
            .withScale(tileScale)
            .build();

        MapTileBuilder grassTile = new MapTileBuilder(grassFrame);

        mapTiles.add(grassTile);

        // Grass top left (TL), Dirt bottom right (BM)

        Frame grassTL_DirtBR_Frame = new FrameBuilder(getSubImage(0, 1))
            .withScale(tileScale)
            .build();

        MapTileBuilder grassTL_dirtBR_Tile = new MapTileBuilder(grassTL_DirtBR_Frame);

        mapTiles.add(grassTL_dirtBR_Tile);

        // Grass top right, Dirt bottom left

        Frame grassTR_dirtBL_Frame = new FrameBuilder(getSubImage(0, 2))
            .withScale(tileScale)
            .build();

        MapTileBuilder grassTR_dirtBL_Tile = new MapTileBuilder(grassTR_dirtBL_Frame);

        mapTiles.add(grassTR_dirtBL_Tile);

        // Grass bottom right, Dirt top left

        Frame grassBR_dirtTL_Frame = new FrameBuilder(getSubImage(0, 3))
            .withScale(tileScale)
            .build();

        MapTileBuilder grassBR_dirtTL_Tile = new MapTileBuilder(grassBR_dirtTL_Frame);

        mapTiles.add(grassBR_dirtTL_Tile);

        // Grass bottom left, Dirt top right

        Frame grassBL_dirtTR_Frame = new FrameBuilder(getSubImage(0, 4))
            .withScale(tileScale)
            .build();

        MapTileBuilder grassBL_dirtTR_Tile = new MapTileBuilder(grassBL_dirtTR_Frame);

        mapTiles.add(grassBL_dirtTR_Tile);

        // Flowers

        Frame flowersFrame = new FrameBuilder(getSubImage(0, 5))
            .withScale(tileScale)
            .build();

        MapTileBuilder flowersTile = new MapTileBuilder(flowersFrame);

        mapTiles.add(flowersTile);

        // Rosebush bottom

        Frame roseBushBottomFrame = new FrameBuilder(getSubImage(0, 6))
            .withScale(tileScale)
            // .withBounds(0, 6, 16, 4)
            .build();

        MapTileBuilder roseBushBottomTile = new MapTileBuilder(roseBushBottomFrame)
            // .withTopLayer(roseBushBottomFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(roseBushBottomTile);

        // Rosebush top 

        Frame roseBushTopFrame = new FrameBuilder(getSubImage(0, 7))
            .withScale(tileScale)
            // .withBounds(0, 6, 16, 4)
            .build();

        MapTileBuilder roseBushTopTile = new MapTileBuilder(grassFrame)
            .withTopLayer(roseBushTopFrame)
            .withTileType(TileType.PASSABLE);

        mapTiles.add(roseBushTopTile);

        // Mushroom 1

        Frame mushroom1Frame = new FrameBuilder(getSubImage(0, 8))
            .withScale(tileScale)
            .build();

        MapTileBuilder mushroom1Tile = new MapTileBuilder(mushroom1Frame);

        mapTiles.add(mushroom1Tile);


        // Mushroom 2

        Frame mushroom2Frame = new FrameBuilder(getSubImage(0, 9))
            .withScale(tileScale)
            .build();

        MapTileBuilder mushroom2Tile = new MapTileBuilder(mushroom2Frame);

        mapTiles.add(mushroom2Tile);


        // Mushroom 3

        Frame mushroom3Frame = new FrameBuilder(getSubImage(0, 10))
            .withScale(tileScale)
            .build();

        MapTileBuilder mushroom3Tile = new MapTileBuilder(mushroom3Frame);

        mapTiles.add(mushroom3Tile);


        // Dirt 

        Frame dirtFrame = new FrameBuilder(getSubImage(1, 0))
            .withScale(tileScale)
            .build();

        MapTileBuilder dirtTile = new MapTileBuilder(dirtFrame);

        mapTiles.add(dirtTile);


        // Sand

        Frame sandFrame = new FrameBuilder(getSubImage(1, 1))
            .withScale(tileScale)
            .build();

        MapTileBuilder sandTile = new MapTileBuilder(sandFrame);

        mapTiles.add(sandTile);


        // Sand top, Grass bottom
        
        Frame sandT_grassB_Frame = new FrameBuilder(getSubImage(1, 2))
            .withScale(tileScale)
            .build();

        MapTileBuilder sandT_grassB_Tile = new MapTileBuilder(sandT_grassB_Frame);

        mapTiles.add(sandT_grassB_Tile);


        // Sand bottom, Grass top

        Frame sandB_grassT_Frame = new FrameBuilder(getSubImage(1, 3))
            .withScale(tileScale)
            .build();

        MapTileBuilder sandB_grassT_Tile = new MapTileBuilder(sandB_grassT_Frame);

        mapTiles.add(sandB_grassT_Tile);

        // Fence corner 1 

        Frame fenceCorner1Frame = new FrameBuilder(getSubImage(1, 4))
            .withScale(tileScale)
            .build();

        MapTileBuilder fenceCorner1Tile = new MapTileBuilder(fenceCorner1Frame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(fenceCorner1Tile);

        // Fence corner 2

        Frame fenceCorner2Frame = new FrameBuilder(getSubImage(1, 5))
            .withScale(tileScale)
            .build();

        MapTileBuilder fenceCorner2Tile = new MapTileBuilder(fenceCorner2Frame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(fenceCorner2Tile);
        
        // Fence corner 3

        Frame fenceCorner3Frame = new FrameBuilder(getSubImage(1, 6))
            .withScale(tileScale)
            .build();

        MapTileBuilder fenceCorner3Tile = new MapTileBuilder(fenceCorner3Frame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(fenceCorner3Tile);

        // Fence corner 4 

        Frame fenceCorner4Frame = new FrameBuilder(getSubImage(1, 7))
            .withScale(tileScale)
            .build();

        MapTileBuilder fenceCorner4Tile = new MapTileBuilder(fenceCorner4Frame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(fenceCorner4Tile);


        // Fence horizontal

        Frame fenceHFrame = new FrameBuilder(getSubImage(1, 8))
            .withScale(tileScale)
            .build();

        MapTileBuilder fenceHTile = new MapTileBuilder(fenceHFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(fenceHTile);


        // Fence vertical

        Frame fenceVFrame = new FrameBuilder(getSubImage(1, 9))
            .withScale(tileScale)
            .build();

        MapTileBuilder fenceVTile = new MapTileBuilder(fenceVFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(fenceVTile);

        // Fence post

        Frame fencePostFrame = new FrameBuilder(getSubImage(1, 10))
            .withScale(tileScale)
            .build();

        MapTileBuilder fencePostTile = new MapTileBuilder(fencePostFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(fencePostTile);


        // Roots 1 left

        Frame roots1LFrame = new FrameBuilder(getSubImage(2, 0))
            .withScale(tileScale)
            .build();

        MapTileBuilder roots1LTile = new MapTileBuilder(roots1LFrame);

        mapTiles.add(roots1LTile);


        // Bottom log 1

        Frame bottomLog1Frame = new FrameBuilder(getSubImage(2, 1))
            .withScale(tileScale)
            .build();

        MapTileBuilder bottomLog1Tile = new MapTileBuilder(bottomLog1Frame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(bottomLog1Tile);

        
        // Roots 1 right

        Frame roots1RFrame = new FrameBuilder(getSubImage(2, 2))
            .withScale(tileScale)
            .build();

        MapTileBuilder roots1RTile = new MapTileBuilder(roots1RFrame);

        mapTiles.add(roots1RTile);


        // Log 1

        Frame log1Frame = new FrameBuilder(getSubImage(2, 3))
            .withScale(tileScale)
            .build();

        MapTileBuilder log1Tile = new MapTileBuilder(grassFrame)
            .withTopLayer(log1Frame);

        mapTiles.add(log1Tile);


        // Roots 2 left

        Frame roots2LFrame = new FrameBuilder(getSubImage(2, 4))
            .withScale(tileScale)
            .build();

        MapTileBuilder roots2LTile = new MapTileBuilder(roots2LFrame);

        mapTiles.add(roots2LTile);

        // Bottom log 2

        Frame bottomLog2Frame = new FrameBuilder(getSubImage(2, 5))
            .withScale(tileScale)
            .build();

        MapTileBuilder bottomLog2Tile = new MapTileBuilder(bottomLog2Frame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(bottomLog2Tile);


        // Roots 2 right

        Frame roots2RFrame = new FrameBuilder(getSubImage(2, 6))
            .withScale(tileScale)
            .build();

        MapTileBuilder roots2RTile = new MapTileBuilder(roots2RFrame);

        mapTiles.add(roots2RTile);


        // Log 2

        Frame log2Frame = new FrameBuilder(getSubImage(2, 7))
            .withScale(tileScale)
            .build();

        MapTileBuilder log2Tile = new MapTileBuilder(grassFrame)
            .withTopLayer(log2Frame);

        mapTiles.add(log2Tile);

        // Leaves 1

        Frame leaves1Frame = new FrameBuilder(getSubImage(3, 0))
            .withScale(tileScale)
            .build();

        MapTileBuilder leaves1Tile = new MapTileBuilder(grassFrame)
            .withTopLayer(leaves1Frame);

        mapTiles.add(leaves1Tile);

        // Leaves 2

        Frame leaves2Frame = new FrameBuilder(getSubImage(3, 1))
            .withScale(tileScale)
            .build();

        MapTileBuilder leaves2Tile = new MapTileBuilder(grassFrame)
            .withTopLayer(leaves2Frame);

        mapTiles.add(leaves2Tile);
        
        // Leaves 3

        Frame leaves3Frame = new FrameBuilder(getSubImage(4, 0))
            .withScale(tileScale)
            .build();

        MapTileBuilder leaves3Tile = new MapTileBuilder(grassFrame)
            .withTopLayer(leaves3Frame);

        mapTiles.add(leaves3Tile);

        // Leaves 4

        Frame leaves4Frame = new FrameBuilder(getSubImage(4, 1))
            .withScale(tileScale)
            .build();

        MapTileBuilder leaves4Tile = new MapTileBuilder(grassFrame)
            .withTopLayer(leaves4Frame);

        mapTiles.add(leaves4Tile);

        // Water

        Frame[] waterFrames = new Frame[] {
            new FrameBuilder(getSubImage(5, 0), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 1), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 2), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 3), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 4), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 5), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 6), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(5, 7), 5)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder waterTile = new MapTileBuilder(waterFrames)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(waterTile);

        // Top water, Bottom sand

        Frame[] topWater_bottomSand_Frames = new Frame[] {
            new FrameBuilder(getSubImage(6, 0), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(6, 1), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(6, 2), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(6, 3), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(6, 4), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(6, 5), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(6, 6), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(6, 7), 5)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder topWater_bottomSand_Tile = new MapTileBuilder(topWater_bottomSand_Frames);

        mapTiles.add(topWater_bottomSand_Tile);

        // Top sand, Bottom water

        Frame[] topSand_bottomWater_Frames = new Frame[] {
            new FrameBuilder(getSubImage(7, 0), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(7, 1), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(7, 2), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(7, 3), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(7, 4), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(7, 5), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(7, 6), 5)
                    .withScale(tileScale)
                    .build(),
            new FrameBuilder(getSubImage(7, 7), 5)
                    .withScale(tileScale)
                    .build()
        };

        MapTileBuilder topSand_bottomWater_Tile = new MapTileBuilder(topSand_bottomWater_Frames);

        mapTiles.add(topSand_bottomWater_Tile);

        return mapTiles;
    }

}
