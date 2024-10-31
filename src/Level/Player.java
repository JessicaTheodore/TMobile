package Level;

import java.awt.Color;

import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.GameObject;
import GameObject.Rectangle;
import GameObject.SpriteSheet;
import Utils.Direction;
import Enemies.*; 
import Level.Enemy;
import Utils.HealthSystem;

// used to extend GameObject, changed it to MapEntity for convenience, I don't know the repurcussions of this
public abstract class Player extends MapEntity {
    // values that affect player movement
    // these should be set in a subclass
    protected int iFrames = 0;
    protected float walkSpeed = 0;
    protected int interactionRange = 1;
    protected static int playerHP = 5;
    protected Direction currentWalkingXDirection;
    protected Direction currentWalkingYDirection;
    protected Direction lastWalkingXDirection;
    protected Direction lastWalkingYDirection;
    protected HealthSystem health = new HealthSystem(playerHP);

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

    protected ScreenCoordinator screenCoordinator;

    protected boolean pickedUpSlingshot = false;
    protected GameState gameState;
    
    public Player(SpriteSheet spriteSheet, float x, float y, String startingAnimationName, ScreenCoordinator screenCoordinator) {
        // super(spriteSheet, x, y, startingAnimationName);
        super(x, y, spriteSheet, startingAnimationName);
        this.xLoc = x;
        this.yLoc = y;
        facingDirection = Direction.RIGHT;
        playerState = PlayerState.STANDING;
        previousPlayerState = playerState;
        this.affectedByTriggers = true;
        this.screenCoordinator = screenCoordinator;
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

        if(iFrames > 0){
            iFrames--;
        }

        handlePlayerAnimation();

        updateLockedKeys();

        // update player's animation
        super.update();
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
            if(this.getY() + moveAmountX - walkSpeed < 2773){
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

    // This gets called from within the Enemy class when the enemy hitbox intersects with the player hitbox
    public void touchedPlayer(Player player) {
        if(playerState == PlayerState.STICK_ATTACK) {
            // check the list of enemies and determine if they are intersecting with you
            // if that enemy is, then hurt them
            for (Enemy enemy : map.getActiveEnemies()){
                if(enemy instanceof BearEnemy && intersects(enemy)) {
                    enemy.hurtEnemy(); // this is where I would put the code to hurt the enemy but I honestly have no idea how to make that work here  
                } else if (enemy instanceof BreakableLog && intersects(enemy)){
                    enemy.hurtEnemy();
                } else if(enemy instanceof BreakableBranch && intersects(enemy)){
                    enemy.hurtEnemy();
                }
            }
                        // I want to make it so the bear gets hit when the hitboxes intersect when the player is attacking/in the attacking state
                        // The only issue with this is that I don't have access to this method because the I don't have the reference to the bear enemy here
                        // if I try to include the bear enemy in here, then I have to edit the touched player parameters, which then mean I need to change the 
                        // parameters for update in the Enemy class, which means then i have to change every paramter everywhere to include the bear
                        // it just doen't work because what if i want to add more than just one enemy
                        // it simply just cannot work and that is driving me inssane right now. 
        } else {
            // decreaseHealth(); from the health system class. makes the hp system in player work with the heart display on the screen of this class
            hurtPlayer(player); // I want the  player to get hit when they are not in the attacking state/walking and standing state
        }
    }

    public void hurtPlayer(MapEntity mapEntity){
        if(playerHP>0){
            if(iFrames == 0) {
                playerHP--;
                iFrames = 60;
                System.out.println("Player is hit\n" + getCurrentHealth());
                health.decreaseHealth();
            } 
        }
        
        // when the player's HP gets down to 0, they die and have to restart from the beginning of the level
        if(playerHP == 0) {
            screenCoordinator.setGameState(GameState.DEATH);
            System.out.println("Player is dead");
        }
    }

    public static int getCurrentHealth(){
        return playerHP;
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
        drawBounds(graphicsHandler, new Color(255, 0, 0, 100));
    }
}
