// package NPCs;

// import Builders.FrameBuilder;
// import Engine.GraphicsHandler;
// import Engine.ImageLoader;
// import GameObject.Frame;
// import GameObject.SpriteSheet;
// import Level.NPC;
// import Utils.Point;

// import java.awt.Color;
// import java.util.HashMap;

// // This is what appears after you break the branch off the tree, it falls to make a bridge over the river
// public class BranchBridge extends NPC {

//         public BranchBridge(int id, Point location) {
//         super(id, location.x, location.y, new SpriteSheet(ImageLoader.load("branchbridge.png"), 31, 68), "STAND_LEFT");
//     }

//     @Override
//     public HashMap<String, Frame[]> loadAnimations(SpriteSheet spriteSheet) {
//         return new HashMap<String, Frame[]>() {{
//             put("STAND_LEFT", new Frame[] {
//                     new FrameBuilder(spriteSheet.getSprite(0, 0))
//                             .withScale(5)
//                             .withBounds(40, 15, 1, 1)
//                             .build()
//             });
//         }};
//     }

//     @Override
//     public void draw(GraphicsHandler graphicsHandler) {
//         super.draw(graphicsHandler);

//         // DRAWS HITBOX
//         drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
//     }


    
// }
