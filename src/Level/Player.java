package Level;

import java.awt.Color;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Game.GameState;
import GameObject.GameObject;
import GameObject.Rectangle;
import GameObject.SpriteSheet;
import Utils.Direction;
import Enemies.BearEnemy;

// used to extend GameObject, changed it to MapEntity for convenience, I don't know the repurcussions of this
public abstract class Player extends MapEntity {
    // values that affect player movement
    // these should be set in a subclass
    protected float walkSpeed = 0;
    protected int interactionRange = 1;
    protected int playerHP = 5;
    protected Direction currentWalkingXDirection;
    protected Direction currentWalkingYDirection;
    protected Direction lastWalkingXDirection;
    protected Direction lastWalkingYDirection;

    // values used to handle player movement
    protected float moveAmountX, moveAmountY;
    protected float lastAmountMovedX, lastAmountMovedY, rockMovementSpeed = 2;

    // values used to keep track of player's current state
    protected PlayerState playerState;
    protected PlayerState previousPlayerState;
    protected Direction facingDirection;
    protected Direction lastMovementDirection;

    // define keys
    protected KeyLocker keyLocker = new KeyLocker();
    protected Key MOVE_LEFT_KEY = Key.A;
    protected Key MOVE_RIGHT_KEY = Key.D;
    protected Key MOVE_UP_KEY = Key.W;
    protected Key MOVE_DOWN_KEY = Key.S; 
    protected Key INTERACT_KEY = Key.E;
    protected Key STICK_ATTACK_KEY = Key.SPACE;
    protected Key ROCK_ATTACK_KEY = Key.SHIFT;
    protected Key RANGER_KEY = Key.H;
    protected boolean isLocked = false;
    protected float xLoc;
    protected float yLoc;

    protected boolean pickedUpSlingshot = false;
    protected GameState gameState;
    
    public Player(SpriteSheet spriteSheet, float x, float y, String startingAnimationName) {
        // super(spriteSheet, x, y, startingAnimationName);
        super(x, y, spriteSheet, startingAnimationName);
        this.xLoc = x;
        this.yLoc = y;
        facingDirection = Direction.RIGHT;
        playerState = PlayerState.STANDING;
        previousPlayerState = playerState;
        this.affectedByTriggers = true;
    }

    public void update() {
        if (!isLocked) {
            moveAmountX = 0;
            moveAmountY = 0;

            // if player is currently playing through level (has not won or lost)
            // update player's state and current actions, which includes things like determining how much it should move each frame and if its walking or jumping
            do {
                previousPlayerState = playerState;
                handlePlayerState();
            } while (previousPlayerState != playerState);

            // move player with respect to map collisions based on how much player needs to move this frame
            lastAmountMovedY = super.moveYHandleCollision(moveAmountY);
            lastAmountMovedX = super.moveXHandleCollision(moveAmountX);
        }

        handlePlayerAnimation();

        updateLockedKeys();

        // update player's animation
        super.update();
    }

    public void hurtPlayer() {
        if(playerHP > 0) {
            playerHP--;
        } else {
            // this is where the death screen would pop up
        }
    }

    public void touchedEnemy(BearEnemy bear) {
        bear.hurtBear();
    }

    // based on player's current state, call appropriate player state handling method
    protected void handlePlayerState() {
        switch (playerState) {
            case STANDING:
                playerStanding();
                break;
            case WALKING:
                playerWalking();
                break;
            case STICK_ATTACK:
                stickAttack();
                break;
            case ROCK_ATTACK:
                rockAttack();
                break;
        }
    }

    protected void stickAttack(){
        if (facingDirection == Direction.RIGHT) {
            this.currentAnimationName = "STICK_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
            this.currentAnimationName = "STICK_LEFT";
        } else if (facingDirection == Direction.UP) {
            this.currentAnimationName = "STICK_UP";
        } else if (facingDirection == Direction.DOWN) {
            this.currentAnimationName = "STICK_DOWN";
        }

        keyLocker.lockKey(STICK_ATTACK_KEY);

        if (getCurrentFrameIndex() == 7) {
            playerState = PlayerState.STICK_ATTACK;
            playerState = PlayerState.STANDING;
            setCurrentAnimationFrameIndex(0);
       }
    }

    protected void rockAttack(){
        if (facingDirection == Direction.RIGHT) {
            this.currentAnimationName = "ROCK_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
            this.currentAnimationName = "ROCK_LEFT";
        } else if (facingDirection == Direction.UP) {
            this.currentAnimationName = "ROCK_UP";
        } else if (facingDirection == Direction.DOWN) {
            this.currentAnimationName = "ROCK_DOWN";
        } 

        keyLocker.lockKey(ROCK_ATTACK_KEY);
        
        // if(getCurrentFrameIndex() == 8){
        //     RockProjectile rock = new RockProjectile(getLocation(), rockMovementSpeed, currentFrameIndex);
        //     rock.update();
        // }

        if (getCurrentFrameIndex() == 10) {
            playerState = PlayerState.ROCK_ATTACK;
            playerState = PlayerState.STANDING;
            setCurrentAnimationFrameIndex(0);
        }
    }

    // player STANDING state logic
    protected void playerStanding() {
        if (!keyLocker.isKeyLocked(INTERACT_KEY) && Keyboard.isKeyDown(INTERACT_KEY)) {
            keyLocker.lockKey(INTERACT_KEY);
            map.entityInteract(this);
        }

        // if a walk key is pressed, player enters WALKING state
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY) || Keyboard.isKeyDown(MOVE_RIGHT_KEY) || Keyboard.isKeyDown(MOVE_UP_KEY) || Keyboard.isKeyDown(MOVE_DOWN_KEY)) {
            playerState = PlayerState.WALKING;
        } 

        // if the stick attack key is pressed, the player enters the STICK_ATTACK state
        if(Keyboard.isKeyDown(STICK_ATTACK_KEY)){
            playerState = PlayerState.STICK_ATTACK;
        }

        // if the rock attack key is pressed, the player enters the ROCK_ATTACK state
        if(Keyboard.isKeyDown(ROCK_ATTACK_KEY) && pickedUpSlingshot){
            playerState = PlayerState.ROCK_ATTACK;
        }
    }

    // player WALKING state logic
    protected void playerWalking() {
        if (!keyLocker.isKeyLocked(INTERACT_KEY) && Keyboard.isKeyDown(INTERACT_KEY)) {
            keyLocker.lockKey(INTERACT_KEY);
            map.entityInteract(this);
        }

        // if walk left key is pressed, move player to the left
        if (Keyboard.isKeyDown(MOVE_LEFT_KEY)) {
            if(this.getX() + moveAmountX - walkSpeed > -50){
                moveAmountX -= walkSpeed;
            }
            facingDirection = Direction.LEFT;
            currentWalkingXDirection = Direction.LEFT;
            lastWalkingXDirection = Direction.LEFT;
        }

        // if walk right key is pressed, move player to the right
        else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
            if(this.getX() + moveAmountX - walkSpeed < 3260){
                moveAmountX += walkSpeed;
            }
            facingDirection = Direction.RIGHT;
            currentWalkingXDirection = Direction.RIGHT;
            lastWalkingXDirection = Direction.RIGHT;
        }
        else {
            currentWalkingXDirection = Direction.NONE;
        }

        if (Keyboard.isKeyDown(MOVE_UP_KEY)) {
            if(this.getY() + moveAmountX - walkSpeed > -50){
            moveAmountY -= walkSpeed;
            }
            facingDirection = Direction.UP;
            currentWalkingYDirection = Direction.UP;
            lastWalkingYDirection = Direction.UP;
        }
        else if (Keyboard.isKeyDown(MOVE_DOWN_KEY)) {
            if(this.getY() + moveAmountX - walkSpeed < 2771){
            moveAmountY += walkSpeed;
            }
            facingDirection = Direction.DOWN;
            currentWalkingYDirection = Direction.DOWN;
            lastWalkingYDirection = Direction.DOWN;
        }
        else {
            currentWalkingYDirection = Direction.NONE;
        }

        if ((currentWalkingXDirection == Direction.RIGHT || currentWalkingXDirection == Direction.LEFT) && currentWalkingYDirection == Direction.NONE) {
            lastWalkingYDirection = Direction.NONE;
        }

        if ((currentWalkingYDirection == Direction.UP || currentWalkingYDirection == Direction.DOWN) && currentWalkingXDirection == Direction.NONE) {
            lastWalkingXDirection = Direction.NONE;
        }

        if (Keyboard.isKeyUp(MOVE_LEFT_KEY) && Keyboard.isKeyUp(MOVE_RIGHT_KEY) && Keyboard.isKeyUp(MOVE_UP_KEY) && Keyboard.isKeyUp(MOVE_DOWN_KEY)) {
            playerState = PlayerState.STANDING;
        }
    }

    protected void updateLockedKeys() {
        if (Keyboard.isKeyUp(INTERACT_KEY) && !isLocked) {
            keyLocker.unlockKey(INTERACT_KEY);
        }
    }

    // anything extra the player should do based on interactions can be handled here
    protected void handlePlayerAnimation() {
        if (playerState == PlayerState.STANDING) {
            // sets animation to a STAND animation based on which way player is facing
            // this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT";
            if (facingDirection == Direction.RIGHT) {
                this.currentAnimationName = "STAND_RIGHT";
            } else if (facingDirection == Direction.LEFT) {
                this.currentAnimationName = "STAND_LEFT";
            } else if (facingDirection == Direction.UP) {
                this.currentAnimationName = "STAND_UP";
            } else if (facingDirection == Direction.DOWN) {
                this.currentAnimationName = "STAND_DOWN";
            }
        }
        else if (playerState == PlayerState.WALKING) {
            // sets animation to a WALK animation based on which way player is facing
            // this.currentAnimationName = facingDirection == Direction.RIGHT ? "WALK_RIGHT" : "WALK_LEFT";
            if (facingDirection == Direction.RIGHT) {
                this.currentAnimationName = "WALK_RIGHT";
            } else if (facingDirection == Direction.LEFT) {
                this.currentAnimationName = "WALK_LEFT";
            } else if (facingDirection == Direction.UP) {
                this.currentAnimationName = "WALK_UP";
            } else if (facingDirection == Direction.DOWN) {
                this.currentAnimationName = "WALK_DOWN";
            }
        }
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction, GameObject entityCollidedWith) { }

    @Override
    public void onEndCollisionCheckY(boolean hasCollided, Direction direction, GameObject entityCollidedWith) { }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public void setPlayerState(PlayerState playerState) {
        this.playerState = playerState;
    }

    public Direction getFacingDirection() {
        return facingDirection;
    }

    public void setFacingDirection(Direction facingDirection) {
        this.facingDirection = facingDirection;
    }

    public Rectangle getInteractionRange() {
        return new Rectangle(
                getBounds().getX1() - interactionRange,
                getBounds().getY1() - interactionRange,
                getBounds().getWidth() + (interactionRange * 2),
                getBounds().getHeight() + (interactionRange * 2));
    }

    public Key getInteractKey() { return INTERACT_KEY; }
    public Direction getCurrentWalkingXDirection() { return currentWalkingXDirection; }
    public Direction getCurrentWalkingYDirection() { return currentWalkingYDirection; }
    public Direction getLastWalkingXDirection() { return lastWalkingXDirection; }
    public Direction getLastWalkingYDirection() { return lastWalkingYDirection; }

    public void lock() {
        isLocked = true;
        playerState = PlayerState.STANDING;
        // this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT";
        if (facingDirection == Direction.RIGHT) {
            this.currentAnimationName = "STAND_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
            this.currentAnimationName = "STAND_LEFT";
        } else if (facingDirection == Direction.UP) {
            this.currentAnimationName = "STAND_UP";
        } else if (facingDirection == Direction.DOWN) {
            this.currentAnimationName = "STAND_DOWN";
        }
    }

    public void changeSlingshotStatus() {
        pickedUpSlingshot = true;
    }

    public void unlock() {
        isLocked = false;
        playerState = PlayerState.STANDING;
        // this.currentAnimationName = facingDirection == Direction.RIGHT ? "STAND_RIGHT" : "STAND_LEFT";
        if (facingDirection == Direction.RIGHT) {
            this.currentAnimationName = "STAND_RIGHT";
        } else if (facingDirection == Direction.LEFT) {
            this.currentAnimationName = "STAND_LEFT";
        } else if (facingDirection == Direction.UP) {
            this.currentAnimationName = "STAND_UP";
        } else if (facingDirection == Direction.DOWN) {
            this.currentAnimationName = "STAND_DOWN";
        }
    }

    // used by other files or scripts to force player to stand
    public void stand(Direction direction) {
        playerState = PlayerState.STANDING;
        facingDirection = direction;
        if (direction == Direction.RIGHT) {
            this.currentAnimationName = "STAND_RIGHT";
        }
        else if (direction == Direction.LEFT) {
            this.currentAnimationName = "STAND_LEFT";
        }
        else if (direction == Direction.UP) {
            this.currentAnimationName = "STAND_UP";
        }
        else if (direction == Direction.DOWN) {
            this.currentAnimationName = "STAND_DOWN";
        }
    }
    
    public void hurtPlayer(MapEntity mapEntity){
        if(mapEntity instanceof Enemy && playerHP > 0){
            playerHP--;            
        } 
        if(playerHP == 0){
            gameState = GameState.DEATH;
        }
    }

    // used by other files or scripts to force player to walk
    public void walk(Direction direction, float speed) {
        playerState = PlayerState.WALKING;
        facingDirection = direction;
        if (direction == Direction.RIGHT) {
            this.currentAnimationName = "WALK_RIGHT";
        }
        else if (direction == Direction.LEFT) {
            this.currentAnimationName = "WALK_LEFT";
        }
        else if (direction == Direction.UP) {
            this.currentAnimationName = "WALK_UP";
        }
        else if (direction == Direction.DOWN) {
            this.currentAnimationName = "WALK_DOWN";
        }


        if (direction == Direction.UP) {
            moveY(-speed);
        }
        else if (direction == Direction.DOWN) {
            moveY(speed);
        }
        else if (direction == Direction.LEFT) {
            moveX(-speed);
        }
        else if (direction == Direction.RIGHT) {
            moveX(speed);
        }
    }

    // Uncomment this to have game draw player's bounds to make it easier to visualize
    public void draw(GraphicsHandler graphicsHandler) {
        super.draw(graphicsHandler);
        // drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
    }
}
