package Tilesets;

import Builders.FrameBuilder;
import Builders.MapTileBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.ImageEffect;
import Level.TileType;
import Level.Tileset;
import ScriptActions.FlagStrategy;

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

        // Leaves top left, Grass bottom right

        Frame leavesTL_grassBR_Frame = new FrameBuilder(getSubImage(3, 2))
            .withScale(tileScale)
            .build();

        MapTileBuilder leavesTL_grassBR_Tile = new MapTileBuilder(grassFrame)
            .withTopLayer(leavesTL_grassBR_Frame);

        mapTiles.add(leavesTL_grassBR_Tile);

        // Leaves top right, Grass bottom left

        Frame leavesTR_grassBL_Frame = new FrameBuilder(getSubImage(3, 3))
            .withScale(tileScale)
            .build();

        MapTileBuilder leavesTR_grassBL_Tile = new MapTileBuilder(grassFrame)
            .withTopLayer(leavesTR_grassBL_Frame);

        mapTiles.add(leavesTR_grassBL_Tile);

        // Leaves bottom left, Grass top right

        Frame leavesBL_grassTR_Frame = new FrameBuilder(getSubImage(4, 2))
            .withScale(tileScale)
            .build();

        MapTileBuilder leavesBL_grassTR_Tile = new MapTileBuilder(grassFrame)
            .withTopLayer(leavesBL_grassTR_Frame);

        mapTiles.add(leavesBL_grassTR_Tile);

        // Leaves bottom right, Grass top left

        Frame leavesBR_grassTL_Frame = new FrameBuilder(getSubImage(4, 3))
            .withScale(tileScale)
            .build();

        MapTileBuilder leavesBR_grassTL_Tile = new MapTileBuilder(grassFrame)
            .withTopLayer(leavesBR_grassTL_Frame);

        mapTiles.add(leavesBR_grassTL_Tile);

        // Leaves top left, Dirt bottom right

        Frame leavesTL_dirtBR_Frame = new FrameBuilder(getSubImage(3, 2))
            .withScale(tileScale)
            .build();

        MapTileBuilder leavesTL_dirtBR_Tile = new MapTileBuilder(dirtFrame)
            .withTopLayer(leavesTL_dirtBR_Frame);

        mapTiles.add(leavesTL_dirtBR_Tile);

        // Leaves top right, Dirt bottom left

        Frame leavesTR_dirtBL_Frame = new FrameBuilder(getSubImage(3, 3))
            .withScale(tileScale)
            .build();

        MapTileBuilder leavesTR_dirtBL_Tile = new MapTileBuilder(dirtFrame)
            .withTopLayer(leavesTR_dirtBL_Frame);

        mapTiles.add(leavesTR_dirtBL_Tile);

        // Leaves bottom left, Dirt top right

        Frame leavesBL_dirtTR_Frame = new FrameBuilder(getSubImage(4, 2))
            .withScale(tileScale)
            .build();

        MapTileBuilder leavesBL_dirtTR_Tile = new MapTileBuilder(dirtFrame)
            .withTopLayer(leavesBL_dirtTR_Frame);

        mapTiles.add(leavesBL_dirtTR_Tile);

        // Leaves bottom right, Dirt top left

        Frame leavesBR_dirtTL_Frame = new FrameBuilder(getSubImage(4, 3))
            .withScale(tileScale)
            .build();

        MapTileBuilder leavesBR_dirtTL_Tile = new MapTileBuilder(dirtFrame)
            .withTopLayer(leavesBR_dirtTL_Frame);

        mapTiles.add(leavesBR_dirtTL_Tile);

        // Leaves peak, Grass background

        Frame leavesPeak_grass_Frame = new FrameBuilder(getSubImage(3, 4))
            .withScale(tileScale)
            .build();

        MapTileBuilder leavesPeak_grass_Tile = new MapTileBuilder(grassFrame)
            .withTopLayer(leavesPeak_grass_Frame);

        mapTiles.add(leavesPeak_grass_Tile);

        // Leaves peak, Dirt background

        Frame leavesPeak_dirt_Frame = new FrameBuilder(getSubImage(3, 4))
            .withScale(tileScale)
            .build();

        MapTileBuilder leavesPeak_dirt_Tile = new MapTileBuilder(dirtFrame)
            .withTopLayer(leavesPeak_dirt_Frame);

        mapTiles.add(leavesPeak_dirt_Tile);

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

        // Log 2 - UNPASSABLE

        Frame log2BarrierFrame = new FrameBuilder(getSubImage(2, 7))
            .withScale(tileScale)
            .build();

        MapTileBuilder log2BarrierTile = new MapTileBuilder(grassFrame)
            .withTopLayer(log2BarrierFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(log2BarrierTile);

        // Leaves - UNPASSABLE

        Frame leaves1BarrierFrame = new FrameBuilder(getSubImage(3, 0))
            .withScale(tileScale)
            .build();

        MapTileBuilder leaves1BarrierTile = new MapTileBuilder(grassFrame)
            .withTopLayer(leaves1BarrierFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(leaves1BarrierTile);

        // Passable water tile (for branch bridge)

        Frame[] passableWaterFrames = new Frame[] {
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

        MapTileBuilder passableWaterTile = new MapTileBuilder(passableWaterFrames)
            .withTileType(TileType.PASSABLE);

        mapTiles.add(passableWaterTile);

        // Bottom cliff tile: Horizontal

        Frame bottomCliffHFrame = new FrameBuilder(getSubImage(8, 0))
            .withScale(tileScale)
            .build();

        MapTileBuilder bottomCliffHTile = new MapTileBuilder(bottomCliffHFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(bottomCliffHTile);
        
        // Bottom cliff tile: Right corner 

        Frame bottomCliffRCornerFrame = new FrameBuilder(getSubImage(8, 1))
            .withScale(tileScale)
            .build();

        MapTileBuilder bottomCliffRCornerTile = new MapTileBuilder(bottomCliffRCornerFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(bottomCliffRCornerTile);
    
        // Bottom cliff tile: Left corner

        Frame bottomCliffLCornerFrame = new FrameBuilder(getSubImage(8, 1))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
            .build();

        MapTileBuilder bottomCliffLCornerTile = new MapTileBuilder(bottomCliffLCornerFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(bottomCliffLCornerTile);

        // Inside cliff corner right

        Frame insideCliffRCornerFrame = new FrameBuilder(getSubImage(8, 2))
            .withScale(tileScale)
            .build();

        MapTileBuilder insideCliffRCornerTile = new MapTileBuilder(insideCliffRCornerFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(insideCliffRCornerTile);

        // Inside cliff corner left

        Frame insideCliffLCornerFrame = new FrameBuilder(getSubImage(8, 2))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
            .build();

        MapTileBuilder insideCliffLCornerTile = new MapTileBuilder(insideCliffLCornerFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(insideCliffLCornerTile);


        // Upper cliff tile: right

        Frame upperCliffRFrame = new FrameBuilder(getSubImage(8, 3))
            .withScale(tileScale)
            .build();

        MapTileBuilder upperCliffRTile = new MapTileBuilder(upperCliffRFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(upperCliffRTile);

        // Upper cliff tile: left

        Frame upperCliffLFrame = new FrameBuilder(getSubImage(8, 3))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
            .build();

        MapTileBuilder upperCliffLTile = new MapTileBuilder(upperCliffLFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(upperCliffLTile);

        // Upper cliff tile: Horizontal

        Frame upperCliffHFrame = new FrameBuilder(getSubImage(8, 4))
            .withScale(tileScale)
            .build();

        MapTileBuilder upperCliffHTile = new MapTileBuilder(upperCliffHFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(upperCliffHTile);

        // Grass with rock bottom right

        Frame grassWithRockBRFrame = new FrameBuilder(getSubImage(8, 5))
            .withScale(tileScale)
            .build();

        MapTileBuilder grassWithRockBRTile = new MapTileBuilder(grassWithRockBRFrame)
            .withTileType(TileType.PASSABLE);

        mapTiles.add(grassWithRockBRTile);
        
        // Grass with rock bottom left

        Frame grassWithRockBLFrame = new FrameBuilder(getSubImage(8, 5))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
            .build();

        MapTileBuilder grassWithRockBLTile = new MapTileBuilder(grassWithRockBLFrame)
            .withTileType(TileType.PASSABLE);

        mapTiles.add(grassWithRockBLTile);

        // Right Cliff

        Frame bottomCliffRFrame = new FrameBuilder(getSubImage(8, 6))
            .withScale(tileScale)
            .build();

        MapTileBuilder bottomCliffRTile = new MapTileBuilder(bottomCliffRFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(bottomCliffRTile);

        // Left Cliff

        Frame bottomCliffLFrame = new FrameBuilder(getSubImage(8, 6))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
            .build();

        MapTileBuilder bottomCliffLTile = new MapTileBuilder(bottomCliffLFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(bottomCliffLTile);

        // Bright grass

        Frame brightGrassFrame = new FrameBuilder(getSubImage(8, 7))
            .withScale(tileScale)
            .build();

        MapTileBuilder brightGrassTile = new MapTileBuilder(brightGrassFrame)
            .withTileType(TileType.PASSABLE);

        mapTiles.add(brightGrassTile);

        // TL grass BR dirt

        Frame TLBrightGrassBRDirtFrame = new FrameBuilder(getSubImage(9, 0))
            .withScale(tileScale)
            .build();

        MapTileBuilder TLBrightGrassBRDirtTile = new MapTileBuilder(TLBrightGrassBRDirtFrame)
            .withTileType(TileType.PASSABLE);

        mapTiles.add(TLBrightGrassBRDirtTile);

        // TR grass BL dirt

        Frame TRBrightGrassBLDirtFrame = new FrameBuilder(getSubImage(9, 1))
            .withScale(tileScale)
            .build();

        MapTileBuilder TRBrightGrassBLDirtTile = new MapTileBuilder(TRBrightGrassBLDirtFrame)
            .withTileType(TileType.PASSABLE);

        mapTiles.add(TRBrightGrassBLDirtTile);

        // BR grass TL dirt

        Frame BRBrightGrassTLDirtFrame = new FrameBuilder(getSubImage(9, 2))
            .withScale(tileScale)
            .build();

        MapTileBuilder BRBrightGrassTLDirtTile = new MapTileBuilder(BRBrightGrassTLDirtFrame)
            .withTileType(TileType.PASSABLE);

        mapTiles.add(BRBrightGrassTLDirtTile);

        // BL grass TR dirt

        Frame BLBrightGrassTRDirtFrame = new FrameBuilder(getSubImage(9, 3))
            .withScale(tileScale)
            .build();

        MapTileBuilder BLBrightGrassTRDirtTile = new MapTileBuilder(BLBrightGrassTRDirtFrame)
            .withTileType(TileType.PASSABLE);

        mapTiles.add(BLBrightGrassTRDirtTile);

        // Upper cliff top 

        Frame upperCliffTopFrame = new FrameBuilder(getSubImage(9, 4))
            .withScale(tileScale)
            .build();

        MapTileBuilder upperCliffTopTile = new MapTileBuilder(upperCliffTopFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(upperCliffTopTile);

        // Upper cliff R corner

        Frame upperCliffRCornerFrame = new FrameBuilder(getSubImage(9, 5))
            .withScale(tileScale)
            .build();

        MapTileBuilder upperCliffRCornerTile = new MapTileBuilder(upperCliffRCornerFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(upperCliffRCornerTile);
        
        // Upper cliff L corner

        Frame upperCliffLCornerFrame = new FrameBuilder(getSubImage(9, 5))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
            .build();

        MapTileBuilder upperCliffLCornerTile = new MapTileBuilder(upperCliffLCornerFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(upperCliffLCornerTile);

        // Upper cliff L vertical

        Frame upperCliffLVerticalFrame = new FrameBuilder(getSubImage(9, 6))
            .withScale(tileScale)
            .build();

        MapTileBuilder upperCliffLVerticalTile = new MapTileBuilder(upperCliffLVerticalFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(upperCliffLVerticalTile);


        // Upper cliff R vertical 

        Frame upperCliffRVerticalFrame = new FrameBuilder(getSubImage(9, 6))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
            .build();

        MapTileBuilder upperCliffRVerticalTile = new MapTileBuilder(upperCliffRVerticalFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(upperCliffRVerticalTile);

        // Upper cliff inside corner TL

        Frame upperCliffInsideCornerTLFrame = new FrameBuilder(getSubImage(9, 7))
            .withScale(tileScale)
            .build();

        MapTileBuilder upperCliffInsideCornerTLTile = new MapTileBuilder(upperCliffInsideCornerTLFrame)
            .withTileType(TileType.PASSABLE);

        mapTiles.add(upperCliffInsideCornerTLTile);

        // Upper cliff inside corner TR

        Frame upperCliffInsideCornerTRFrame = new FrameBuilder(getSubImage(9, 7))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
            .build();

        MapTileBuilder upperCliffInsideCornerTRTile = new MapTileBuilder(upperCliffInsideCornerTRFrame)
            .withTileType(TileType.PASSABLE);

        mapTiles.add(upperCliffInsideCornerTRTile);

        // Leaves TL bright grass

        Frame leavesTL_brightGrassBR_Frame = new FrameBuilder(getSubImage(3, 2))
            .withScale(tileScale)
            .build();

        MapTileBuilder leavesTL_brightGrassBR_Tile = new MapTileBuilder(brightGrassFrame)
            .withTopLayer(leavesTL_brightGrassBR_Frame);

        mapTiles.add(leavesTL_brightGrassBR_Tile);



        // Leaves TR bright grass

        Frame leavesTR_brightGrassBL_Frame = new FrameBuilder(getSubImage(3, 3))
            .withScale(tileScale)
            .build();

        MapTileBuilder leavesTR_brightGrassBL_Tile = new MapTileBuilder(brightGrassFrame)
            .withTopLayer(leavesTR_brightGrassBL_Frame);

        mapTiles.add(leavesTR_brightGrassBL_Tile);


        // Leaves BL bright grass

        Frame leavesBL_brightGrassTR_Frame = new FrameBuilder(getSubImage(4, 2))
            .withScale(tileScale)
            .build();

        MapTileBuilder leavesBL_brightGrassTR_Tile = new MapTileBuilder(brightGrassFrame)
            .withTopLayer(leavesBL_brightGrassTR_Frame);

        mapTiles.add(leavesBL_brightGrassTR_Tile);


        // Leaves BR bright grass

        Frame leavesBR_brightGrassTL_Frame = new FrameBuilder(getSubImage(4, 3))
            .withScale(tileScale)
            .build();

        MapTileBuilder leavesBR_brightGrassTL_Tile = new MapTileBuilder(brightGrassFrame)
            .withTopLayer(leavesBR_brightGrassTL_Frame);

        mapTiles.add(leavesBR_brightGrassTL_Tile);

        // Roots 1 left - bright grass

        Frame roots1LBrightFrame = new FrameBuilder(getSubImage(10, 0))
            .withScale(tileScale)
            .build();

        MapTileBuilder roots1LBrightTile = new MapTileBuilder(roots1LBrightFrame);

        mapTiles.add(roots1LBrightTile);
    
        // Bottom log 1 - bright grass

        Frame bottomLog1BrightFrame = new FrameBuilder(getSubImage(10, 1))
            .withScale(tileScale)
            .build();

        MapTileBuilder bottomLog1BrightTile = new MapTileBuilder(bottomLog1BrightFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(bottomLog1BrightTile);
    
            
        // Roots 1 right - bright grass

        Frame roots1RBrightFrame = new FrameBuilder(getSubImage(10, 2))
            .withScale(tileScale)
            .build();

        MapTileBuilder roots1RBrightTile = new MapTileBuilder(roots1RBrightFrame);

        mapTiles.add(roots1RBrightTile);
        
    
        // Roots 2 left - bright grass

        Frame roots2LBrightFrame = new FrameBuilder(getSubImage(10, 3))
            .withScale(tileScale)
            .build();

        MapTileBuilder roots2LBrightTile = new MapTileBuilder(roots2LBrightFrame);

        mapTiles.add(roots2LBrightTile);

        // Bottom log 2 - bright grass

        Frame bottomLog2BrightFrame = new FrameBuilder(getSubImage(10, 4))
            .withScale(tileScale)
            .build();

        MapTileBuilder bottomLog2BrightTile = new MapTileBuilder(bottomLog2BrightFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(bottomLog2BrightTile);


        // Roots 2 right - bright grass

        Frame roots2RBrightFrame = new FrameBuilder(getSubImage(10, 5))
            .withScale(tileScale)
            .build();

        MapTileBuilder roots2RBrightTile = new MapTileBuilder(roots2RBrightFrame);

        mapTiles.add(roots2RBrightTile);

        // Flowers - bright grass

        Frame flowersBrightFrame = new FrameBuilder(getSubImage(11, 0))
            .withScale(tileScale)
            .build();

        MapTileBuilder flowersBrightTile = new MapTileBuilder(flowersBrightFrame);

        mapTiles.add(flowersBrightTile);

        // Rosebush bottom - bright grass

        Frame roseBushBottomBrightFrame = new FrameBuilder(getSubImage(11, 1))
            .withScale(tileScale)
            .build();

        MapTileBuilder roseBushBottomBrightTile = new MapTileBuilder(roseBushBottomBrightFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(roseBushBottomBrightTile);

        // Rosebush top - bright grass

        Frame roseBushTopBrightFrame = new FrameBuilder(getSubImage(0, 7))
            .withScale(tileScale)
            .build();

        MapTileBuilder roseBushTopBrightTile = new MapTileBuilder(brightGrassFrame)
            .withTopLayer(roseBushTopBrightFrame)
            .withTileType(TileType.PASSABLE);

        mapTiles.add(roseBushTopBrightTile);

        // Mushroom 1 - bright grass

        Frame mushroom1BrightFrame = new FrameBuilder(getSubImage(11, 2))
            .withScale(tileScale)
            .build();

        MapTileBuilder mushroom1BrightTile = new MapTileBuilder(mushroom1BrightFrame);

        mapTiles.add(mushroom1BrightTile);


        // Mushroom 2 - bright grass

        Frame mushroom2BrightFrame = new FrameBuilder(getSubImage(11, 3))
            .withScale(tileScale)
            .build();

        MapTileBuilder mushroom2BrightTile = new MapTileBuilder(mushroom2BrightFrame);

        mapTiles.add(mushroom2BrightTile);


        // Mushroom 3 - bright grass

        Frame mushroom3BrightFrame = new FrameBuilder(getSubImage(11, 4))
            .withScale(tileScale)
            .build();

        MapTileBuilder mushroom3BrightTile = new MapTileBuilder(mushroom3BrightFrame);

        mapTiles.add(mushroom3BrightTile);

        // Black Tile

        Frame blackFrame = new FrameBuilder(getSubImage(12, 0))
            .withScale(tileScale)
            .build();

        MapTileBuilder blackTile = new MapTileBuilder(blackFrame);

        mapTiles.add(blackTile);
        
        // Brick Wall Tile

        Frame brickFrame = new FrameBuilder(getSubImage(12, 1))
            .withScale(tileScale)
            .build();

        MapTileBuilder brickTile = new MapTileBuilder(brickFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(brickTile);

        // Door TL
        
        Frame doorTLFrame = new FrameBuilder(getSubImage(12, 2))
            .withScale(tileScale)
            .build();

        MapTileBuilder doorTLTile = new MapTileBuilder(doorTLFrame);

        mapTiles.add(doorTLTile);

        // Door TR

        Frame doorTRFrame = new FrameBuilder(getSubImage(12, 3))
            .withScale(tileScale)
            .build();

        MapTileBuilder doorTRTile = new MapTileBuilder(doorTRFrame);

        mapTiles.add(doorTRTile);

        // Window

        Frame windowFrame = new FrameBuilder(getSubImage(12, 4))
            .withScale(tileScale)
            .build();

        MapTileBuilder windowTile = new MapTileBuilder(windowFrame);

        mapTiles.add(windowTile);

        // Battlement --> set background

        Frame battlementFrame = new FrameBuilder(getSubImage(13, 1))
            .withScale(tileScale)
            .build();

        MapTileBuilder battlementTile = new MapTileBuilder(brightGrassFrame)
            .withTopLayer(battlementFrame);

        mapTiles.add(battlementTile);

        // Door BL

        Frame doorBLFrame = new FrameBuilder(getSubImage(13, 2))
            .withScale(tileScale)
            .build();

        MapTileBuilder doorBLTile = new MapTileBuilder(doorBLFrame);

        mapTiles.add(doorBLTile);

        // Door BR

        Frame doorBRFrame = new FrameBuilder(getSubImage(13, 3))
            .withScale(tileScale)
            .build();

        MapTileBuilder doorBRTile = new MapTileBuilder(doorBRFrame);

        mapTiles.add(doorBRTile);

        // Curved brick 1 left

        Frame curveBrick1LFrame = new FrameBuilder(getSubImage(13, 0))
            .withScale(tileScale)
            .build();

        MapTileBuilder curveBrick1LTile = new MapTileBuilder(curveBrick1LFrame);

        mapTiles.add(curveBrick1LTile);


        // Curved brick 2 left

        Frame curveBrick2LFrame = new FrameBuilder(getSubImage(13, 4))
            .withScale(tileScale)
            .build();

        MapTileBuilder curveBrick2LTile = new MapTileBuilder(curveBrick2LFrame);

        mapTiles.add(curveBrick2LTile);


        // Curved brick 1 right

        Frame curveBrick1RFrame = new FrameBuilder(getSubImage(13, 0))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
            .build();

        MapTileBuilder curveBrick1RTile = new MapTileBuilder(curveBrick1RFrame);

        mapTiles.add(curveBrick1RTile);


        // Curved brick 2 right

        Frame curveBrick2RFrame = new FrameBuilder(getSubImage(13, 4))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
            .build();

        MapTileBuilder curveBrick2RTile = new MapTileBuilder(curveBrick2RFrame);

        mapTiles.add(curveBrick2RTile);

        // Floor (brick)

        Frame floorFrame = new FrameBuilder(getSubImage(14, 0))
            .withScale(tileScale)
            .build();

        MapTileBuilder floorTile = new MapTileBuilder(floorFrame)
            .withTileType(TileType.PASSABLE);

        mapTiles.add(floorTile);

        // Right wall (with black)

        Frame rightWallFrame = new FrameBuilder(getSubImage(14, 1))
            .withScale(tileScale)
            .build();

        MapTileBuilder rightWallTile = new MapTileBuilder(rightWallFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(rightWallTile);

        // Left wall (with black)

        Frame leftWallFrame = new FrameBuilder(getSubImage(14, 1))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
            .build();

        MapTileBuilder leftWallTile = new MapTileBuilder(leftWallFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(leftWallTile);

        // Top wall (with black)

        Frame topWallFrame = new FrameBuilder(getSubImage(14, 2))
            .withScale(tileScale)
            .build();

        MapTileBuilder topWallTile = new MapTileBuilder(topWallFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(topWallTile);

        // Bottom wall (with black)

        Frame bottomWallFrame = new FrameBuilder(getSubImage(14, 2))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_VERTICAL)
            .build();

        MapTileBuilder bottomWallTile = new MapTileBuilder(bottomWallFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(bottomWallTile);

        // Corner wall TL

        Frame cornerWallTLFrame = new FrameBuilder(getSubImage(14, 3))
            .withScale(tileScale)
            .build();

        MapTileBuilder cornerWallTLTile = new MapTileBuilder(cornerWallTLFrame);

        mapTiles.add(cornerWallTLTile);

        // Corner wall TR

        Frame cornerWallTRFrame = new FrameBuilder(getSubImage(14, 3))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
            .build();

        MapTileBuilder cornerWallTRTile = new MapTileBuilder(cornerWallTRFrame);

        mapTiles.add(cornerWallTRTile);

        // Corner wall BL

        Frame cornerWallBLFrame = new FrameBuilder(getSubImage(14, 3))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_VERTICAL)
            .build();

        MapTileBuilder cornerWallBLTile = new MapTileBuilder(cornerWallBLFrame);

        mapTiles.add(cornerWallBLTile);

        // Corner wall BR

        Frame cornerWallBRFrame = new FrameBuilder(getSubImage(14, 3))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_H_AND_V)
            .build();

        MapTileBuilder cornerWallBRTile = new MapTileBuilder(cornerWallBRFrame);

        mapTiles.add(cornerWallBRTile);

        // Stairs

        Frame stairsFrame = new FrameBuilder(getSubImage(14, 4))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_VERTICAL)
            .build();

        MapTileBuilder stairsTile = new MapTileBuilder(stairsFrame)
            .withTileType(TileType.PASSABLE);

        mapTiles.add(stairsTile);

        // Barrel top

        Frame barrelTopFrame = new FrameBuilder(getSubImage(13, 5))
            .withScale(tileScale)
            .build();

        MapTileBuilder barrelTopTile = new MapTileBuilder(barrelTopFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(barrelTopTile);

        // Barrel bottom

        Frame barrelBottomFrame = new FrameBuilder(getSubImage(14, 5))
            .withScale(tileScale)
            .build();

        MapTileBuilder barrelBottomTile = new MapTileBuilder(barrelBottomFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(barrelBottomTile);

        // Railing 1 

        Frame railing1Frame = new FrameBuilder(getSubImage(14, 6))
            .withScale(tileScale)
            .build();

        MapTileBuilder railing1Tile = new MapTileBuilder(railing1Frame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(railing1Tile);

        // Railing 2

        Frame railing2Frame = new FrameBuilder(getSubImage(14, 6))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_HORIZONTAL)
            .build();

        MapTileBuilder railing2Tile = new MapTileBuilder(railing2Frame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(railing2Tile);



        // Inside door left

        Frame insideDoorLFrame = new FrameBuilder(getSubImage(14, 7))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_VERTICAL)
            .build();

        MapTileBuilder insideDoorLTile = new MapTileBuilder(insideDoorLFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(insideDoorLTile);

        // Inside door right 

        Frame insideDoorRFrame = new FrameBuilder(getSubImage(14, 8))
            .withScale(tileScale)
            .withImageEffect(ImageEffect.FLIP_VERTICAL)
            .build();

        MapTileBuilder insideDoorRTile = new MapTileBuilder(insideDoorRFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(insideDoorRTile);

        // Bright H fence

        Frame brightHFenceFrame = new FrameBuilder(getSubImage(10, 6))
            .withScale(tileScale)
            .build();

        MapTileBuilder brightHFenceTile = new MapTileBuilder(brightHFenceFrame)
            .withTileType(TileType.NOT_PASSABLE);

        mapTiles.add(brightHFenceTile);

        return mapTiles;
    }




}
