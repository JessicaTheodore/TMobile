package Level;

import GameObject.GameObject;
import Utils.Direction;
import Utils.Point;

// This class has methods to check if a game object has collided with a map entity (map tile, enhanced map tile, npc, or trigger if applicable)
// it is used by the game object class to determine if and where a collision occurred
public class MapCollisionHandler {

    // x axis collision logic
    // determines if a collision occurred with another entity on the map, and calculates where gameobject should be placed to resolve the collision
    public static MapCollisionCheckResult getAdjustedPositionAfterCollisionCheckX(GameObject gameObject, Map map, Direction direction) {
        // check map tiles in surrounding radius for potential collision
        int numberOfTilesToCheck = Math.max(gameObject.getBounds().getHeight() / map.getTileset().getScaledSpriteHeight(), 1);
        float edgeBoundX = direction == Direction.LEFT ? gameObject.getBounds().getX1() : gameObject.getBounds().getX2();
        Point tileIndex = map.getTileIndexByPosition(edgeBoundX, gameObject.getBounds().getY1());
        GameObject entityCollidedWith = null;
        for (int i = -1; i <= numberOfTilesToCheck + 1; i++) {
            MapTile mapTile = map.getMapTile(Math.round(tileIndex.x), Math.round(tileIndex.y + i));
            if (mapTile != null && hasCollidedWithMapEntity(gameObject, mapTile, direction)) {
                entityCollidedWith = mapTile;
                float adjustedPositionX = gameObject.getX();
                if (direction == Direction.RIGHT) {
                    float boundsDifference = gameObject.getX2() - gameObject.getBounds().getX2();
                    adjustedPositionX = mapTile.getBounds().getX1() - gameObject.getWidth() + boundsDifference;
                } else if (direction == Direction.LEFT) {
                    float boundsDifference = gameObject.getBounds().getX1() - gameObject.getX();
                    adjustedPositionX = (mapTile.getBounds().getX2() + 1) - boundsDifference;
                }
                return new MapCollisionCheckResult(new Point(adjustedPositionX, gameObject.getY()), entityCollidedWith);
            }
        }

        // check active enhanced map tiles for potential collision
        for (EnhancedMapTile enhancedMapTile : map.getActiveEnhancedMapTiles()) {
            if (!gameObject.equals(enhancedMapTile) && !enhancedMapTile.isUncollidable() && hasCollidedWithMapEntity(gameObject, enhancedMapTile, direction)) {
                entityCollidedWith = enhancedMapTile;
                float adjustedPositionX = gameObject.getX();
                if (direction == Direction.RIGHT) {
                    float boundsDifference = gameObject.getX2() - gameObject.getBounds().getX2();
                    adjustedPositionX = enhancedMapTile.getBounds().getX1() - gameObject.getWidth() + boundsDifference;
                } else if (direction == Direction.LEFT) {
                    float boundsDifference = gameObject.getBounds().getX1() - gameObject.getX();
                    adjustedPositionX = (enhancedMapTile.getBounds().getX2() + 1) - boundsDifference;
                }
                return new MapCollisionCheckResult(new Point(adjustedPositionX, gameObject.getY()), entityCollidedWith);
            }
        }

        // check active npcs for potential collision
        for (NPC npc : map.getActiveNPCs()) {
            if (!gameObject.equals(npc) && !npc.isUncollidable() && hasCollidedWithMapEntity(gameObject, npc, direction)) {
                entityCollidedWith = npc;
                float adjustedPositionX = gameObject.getX();
                if (direction == Direction.RIGHT) {
                    float boundsDifference = gameObject.getX2() - gameObject.getBounds().getX2();
                    adjustedPositionX = npc.getBounds().getX1() - gameObject.getWidth() + boundsDifference;
                } else if (direction == Direction.LEFT) {
                    float boundsDifference = gameObject.getBounds().getX1() - gameObject.getX();
                    adjustedPositionX = (npc.getBounds().getX2() + 1) - boundsDifference;
                }
                return new MapCollisionCheckResult(new Point(adjustedPositionX, gameObject.getY()), entityCollidedWith);
            }
        }

        for (Enemy enemy : map.getActiveEnemies()) {
            if (!gameObject.equals(enemy) && !enemy.isUncollidable() && hasCollidedWithMapEntity(gameObject, enemy, direction)) {
                entityCollidedWith = enemy;
                float adjustedPositionX = gameObject.getX();
                if (direction == Direction.RIGHT) {
                    float boundsDifference = gameObject.getX2() - gameObject.getBounds().getX2();
                    adjustedPositionX = enemy.getBounds().getX1() - gameObject.getWidth() + boundsDifference;
                } else if (direction == Direction.LEFT) {
                    float boundsDifference = gameObject.getBounds().getX1() - gameObject.getX();
                    adjustedPositionX = (enemy.getBounds().getX2() + 1) - boundsDifference;
                }
                return new MapCollisionCheckResult(new Point(adjustedPositionX, gameObject.getY()), entityCollidedWith);
            }
        }

        // check active triggers for potential collision
        if (gameObject.isAffectedByTriggers()) {
            for (Trigger trigger : map.getActiveTriggers()) {
                if (!gameObject.equals(trigger) && !trigger.isUncollidable() && trigger.exists() && hasCollidedWithMapEntity(gameObject, trigger, direction)) {
                    entityCollidedWith = trigger;
                    float adjustedPositionX = gameObject.getX();
                    if (direction == Direction.RIGHT) {
                        float boundsDifference = gameObject.getX2() - gameObject.getBounds().getX2();
                        adjustedPositionX = trigger.getBounds().getX1() - gameObject.getWidth() + boundsDifference;
                    } else if (direction == Direction.LEFT) {
                        float boundsDifference = gameObject.getBounds().getX1() - gameObject.getX();
                        adjustedPositionX = (trigger.getBounds().getX2() + 1) - boundsDifference;
                    }
                    return new MapCollisionCheckResult(new Point(adjustedPositionX, gameObject.getY()), entityCollidedWith);
                }
            }
        }

        // check for collision with player
        // this is to allow non-player entities to collision check against the player
        Player player = map.getPlayer();
        if (player != null && !gameObject.equals(player) && (!(gameObject instanceof MapEntity) || !((MapEntity)(gameObject)).isUncollidable())) {
            if (hasCollidedWithMapEntity(gameObject, player, direction)) {
                entityCollidedWith = player;
                float adjustedPositionX = gameObject.getX();
                if (direction == Direction.RIGHT) {
                    float boundsDifference = gameObject.getX2() - gameObject.getBounds().getX2();
                    adjustedPositionX = player.getBounds().getX1() - gameObject.getWidth() + boundsDifference;
                } else if (direction == Direction.LEFT) {
                    float boundsDifference = gameObject.getBounds().getX1() - gameObject.getX();
                    adjustedPositionX = (player.getBounds().getX2() + 1) - boundsDifference;
                }
                return new MapCollisionCheckResult(new Point(adjustedPositionX, gameObject.getY()), entityCollidedWith);
            }
        }

        // no collision occurred
        return new MapCollisionCheckResult(null, null);
    }

    // y axis collision logic
    // determines if a collision occurred with another entity on the map, and calculates where gameobject should be placed to resolve the collision
    public static MapCollisionCheckResult getAdjustedPositionAfterCollisionCheckY(GameObject gameObject, Map map, Direction direction) {
        // check map tiles in surrounding radius for potential collision
        int numberOfTilesToCheck = Math.max(gameObject.getBounds().getWidth() / map.getTileset().getScaledSpriteWidth(), 1);
        float edgeBoundY = direction == Direction.UP ? gameObject.getBounds().getY() : gameObject.getBounds().getY2();
        Point tileIndex = map.getTileIndexByPosition(gameObject.getBounds().getX1(), edgeBoundY);
        GameObject entityCollidedWith = null;
        for (int i = -1; i <= numberOfTilesToCheck + 1; i++) {
            MapTile mapTile = map.getMapTile(Math.round(tileIndex.x) + i, Math.round(tileIndex.y));
            if (mapTile != null && hasCollidedWithMapEntity(gameObject, mapTile, direction)) {
                entityCollidedWith = mapTile;
                float adjustedPositionY = gameObject.getY();
                if (direction == Direction.DOWN) {
                    float boundsDifference = gameObject.getY2() - gameObject.getBounds().getY2();
                    adjustedPositionY = mapTile.getBounds().getY1() - gameObject.getHeight() + boundsDifference;
                } else if (direction == Direction.UP) {
                    float boundsDifference = gameObject.getBounds().getY1() - gameObject.getY();
                    adjustedPositionY = (mapTile.getBounds().getY2() + 1) - boundsDifference;
                }
                return new MapCollisionCheckResult(new Point(gameObject.getX(), adjustedPositionY), entityCollidedWith);
            }
        }

        // check active enhanced map tiles for potential collision
        for (EnhancedMapTile enhancedMapTile : map.getActiveEnhancedMapTiles()) {
            if (!gameObject.equals(enhancedMapTile) && !enhancedMapTile.isUncollidable() && hasCollidedWithMapEntity(gameObject, enhancedMapTile, direction)) {
                entityCollidedWith = enhancedMapTile;
                float adjustedPositionY = gameObject.getY();
                if (direction == Direction.DOWN) {
                    float boundsDifference = gameObject.getY2() - gameObject.getBounds().getY2();
                    adjustedPositionY = enhancedMapTile.getBounds().getY1() - gameObject.getHeight() + boundsDifference;
                } else if (direction == Direction.UP) {
                    float boundsDifference = gameObject.getBounds().getY1() - gameObject.getY();
                    adjustedPositionY = (enhancedMapTile.getBounds().getY2() + 1) - boundsDifference;
                }
                return new MapCollisionCheckResult(new Point(gameObject.getX(), adjustedPositionY), entityCollidedWith);
            }
        }

        // check active npcs for potential collision
        for (NPC npc : map.getActiveNPCs()) {
            if (!gameObject.equals(npc) && !npc.isUncollidable() && hasCollidedWithMapEntity(gameObject, npc, direction)) {
                entityCollidedWith = npc;
                float adjustedPositionY = gameObject.getY();
                if (direction == Direction.DOWN) {
                    float boundsDifference = gameObject.getY2() - gameObject.getBounds().getY2();
                    adjustedPositionY = npc.getBounds().getY1() - gameObject.getHeight() + boundsDifference;
                } else if (direction == Direction.UP) {
                    float boundsDifference = gameObject.getBounds().getY1() - gameObject.getY();
                    adjustedPositionY = (npc.getBounds().getY2() + 1) - boundsDifference;
                }
                return new MapCollisionCheckResult(new Point(gameObject.getX(), adjustedPositionY), entityCollidedWith);
            }
        }

        for (Enemy enemy : map.getActiveEnemies()) {
            if (!gameObject.equals(enemy) && !enemy.isUncollidable() && hasCollidedWithMapEntity(gameObject, enemy, direction)) {
                entityCollidedWith = enemy;
                float adjustedPositionY = gameObject.getY();
                if (direction == Direction.DOWN) {
                    float boundsDifference = gameObject.getY2() - gameObject.getBounds().getY2();
                    adjustedPositionY = enemy.getBounds().getY1() - gameObject.getHeight() + boundsDifference;
                } else if (direction == Direction.UP) {
                    float boundsDifference = gameObject.getBounds().getY1() - gameObject.getY();
                    adjustedPositionY = (enemy.getBounds().getY2() + 1) - boundsDifference;
                }
                return new MapCollisionCheckResult(new Point(gameObject.getX(), adjustedPositionY), entityCollidedWith);
            }
        }

        // check active triggers for potential collision
        if (gameObject.isAffectedByTriggers()) {
            for (Trigger trigger : map.getActiveTriggers()) {
                if (!gameObject.equals(trigger) && !trigger.isUncollidable() && trigger.exists() && hasCollidedWithMapEntity(gameObject, trigger, direction)) {
                    entityCollidedWith = trigger;
                    float adjustedPositionY = gameObject.getY();
                    if (direction == Direction.DOWN) {
                        float boundsDifference = gameObject.getY2() - gameObject.getBounds().getY2();
                        adjustedPositionY = trigger.getBounds().getY1() - gameObject.getHeight() + boundsDifference;
                    } else if (direction == Direction.UP) {
                        float boundsDifference = gameObject.getBounds().getY1() - gameObject.getY();
                        adjustedPositionY = (trigger.getBounds().getY2() + 1) - boundsDifference;
                    }
                    return new MapCollisionCheckResult(new Point(gameObject.getX(), adjustedPositionY), entityCollidedWith);
                }
            }
        }

        // check for collision with player
        // this is to allow non-player entities to collision check against the player
        Player player = map.getPlayer();
        if (player != null && !gameObject.equals(player) && (!(gameObject instanceof MapEntity) || !((MapEntity)(gameObject)).isUncollidable())) {
            if (hasCollidedWithMapEntity(gameObject, player, direction)) {
                entityCollidedWith = player;
                float adjustedPositionY = gameObject.getY();
                if (direction == Direction.DOWN) {
                    float boundsDifference = gameObject.getY2() - gameObject.getBounds().getY2();
                    adjustedPositionY = player.getBounds().getY1() - gameObject.getHeight() + boundsDifference;
                } else if (direction == Direction.UP) {
                    float boundsDifference = gameObject.getBounds().getY1() - gameObject.getY();
                    adjustedPositionY = (player.getBounds().getY2() + 1) - boundsDifference;
                }
                return new MapCollisionCheckResult(new Point(gameObject.getX(), adjustedPositionY), entityCollidedWith);
            }
        }

        // no collision occurred
        return new MapCollisionCheckResult(null, null);
    }

    // based on tile type, perform logic to determine if a collision did occur with an intersecting tile or not
    private static boolean hasCollidedWithMapEntity(GameObject gameObject, GameObject otherEntity, Direction direction) {
        // if entity that is being checked for collision against is a map tile
        // collision is determined based on tile type
        if (otherEntity instanceof MapTile) {
            MapTile mapTile = (MapTile)otherEntity;
            switch (mapTile.getTileType()) {
                case PASSABLE:
                    return false;
                case NOT_PASSABLE:
                    return gameObject.intersects(mapTile);
                default:
                    return false;
            }
        }

        // for all other cases other than MapTile, let game object subclass (NPC, enhanced map tile, etc.) handle the intersection logic
        else {
            return otherEntity.intersects(gameObject);
        }
    }
}
