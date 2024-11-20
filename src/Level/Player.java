package Level;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.ImageIcon;

import Builders.FrameBuilder;
import Engine.GraphicsHandler;
import Engine.Key;
import Engine.KeyLocker;
import Engine.Keyboard;
import Engine.Screen;
import Game.Game;
import Game.GameState;
import Game.ScreenCoordinator;
import GameObject.Frame;
import GameObject.GameObject;
import GameObject.Rectangle;
import GameObject.SpriteSheet;
import Utils.Direction;
import Enemies.*; 
import Level.*;
import Level.Enemy;
import Screens.PlayLevelScreen;
import Utils.HealthSystem;
import Utils.Point;
import javax.imageio.ImageIO;
import java.io.File;

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
    protected HealthSystem health = new HealthSystem(3);
    protected int upBound;
    protected int downBound;
    protected int leftBound;
    protected int rightBound;

    protected boolean hurt;

    protected GameObject stickRectangle = new GameObject(50, 50);
    protected GameObject slingshotRectangle = new GameObject(200, 50);

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

    protected int i = 0;

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
        stickRectangle.setBounds(new Rectangle(0,0,50,50));
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

        if(iFrames%16 == 1){
            if(hurt){
                hurtFalse();
            }
            hurt = false;
        }
        
        handlePlayerAnimation();

        updateLockedKeys();

        stickRectangle.setMap(map);

        slingshotRectangle.setMap(map);
        
        // This is where the stick hitbox is being put in the direction that the player is facing
        if (facingDirection == Direction.RIGHT && currentAnimationName.equals("STICK_RIGHT") && getCurrentFrameIndex() == 5) {
            stickRectangle.setBounds(new Rectangle(0,0,50,50));
            stickRectangle.setLocation(getX()+100, getY()+50);
            
        } else if (facingDirection == Direction.LEFT && currentAnimationName.equals("STICK_LEFT") && getCurrentFrameIndex() == 5) {
            stickRectangle.setBounds(new Rectangle(0,0,50,50));
            stickRectangle.setLocation(getX()-20, getY()+50);            
        
        } else if (facingDirection == Direction.UP && currentAnimationName.equals("STICK_UP") && getCurrentFrameIndex() == 5) {
            stickRectangle.setBounds(new Rectangle(0,0,50,50));
            stickRectangle.setLocation(getX()+40, getY()-20);    
        
        } else if (facingDirection == Direction.DOWN && currentAnimationName.equals("STICK_DOWN") && getCurrentFrameIndex() == 5) {
            stickRectangle.setBounds(new Rectangle(0,0,50,50));
            stickRectangle.setLocation(getX()+40, getY()+100);
        }

        // This is where the slingshot hitbox is being put in the direction that the player is facing
        if(i < 10){
            if (facingDirection == Direction.RIGHT && currentAnimationName.equals("ROCK_RIGHT")) {
                slingshotRectangle.setBounds(new Rectangle(0,0,16,16));
                
                slingshotRectangle.setLocation((getX()+60) + (i*20), getY()+50);
                
            } else if (facingDirection == Direction.LEFT && currentAnimationName.equals("ROCK_LEFT")) {
            
                slingshotRectangle.setBounds(new Rectangle(0,0,16,16));
            
                slingshotRectangle.setLocation((getX()+20) - (i*20), getY()+50);    
            
            } else if (facingDirection == Direction.UP && currentAnimationName.equals("ROCK_UP")) {
            
                slingshotRectangle.setBounds(new Rectangle(0,0,16,16));
            
                slingshotRectangle.setLocation(getX()+50, (getY()+40) - (i*20));
            
            } else if (facingDirection == Direction.DOWN && currentAnimationName.equals("ROCK_DOWN")) {
            
                slingshotRectangle.setBounds(new Rectangle(0,0,16,16));
            
                slingshotRectangle.setLocation(getX()+50, (getY()+100) + (i*20));
            
            }
            if(currentFrameIndex == i){
                i++;
            }
        } else{
            i = 0;
        }

        // This is where we would make the hitboxes dissapear when they are not attacking
        if(playerState.equals(PlayerState.WALKING) || playerState.equals(PlayerState.STANDING)){
            stickRectangle.setBounds(new Rectangle(0,0,0,0));
            slingshotRectangle.setBounds(new Rectangle(0,0,0,0));
            i = 0;
        } else if (playerState.equals(PlayerState.STICK_ATTACK)) {
            stickRectangle.setWidth(50);
        }

        if(!screenCoordinator.beatLvl1() && screenCoordinator.getGameState().equals(GameState.NEWLEVEL) || screenCoordinator.getGameState().equals(GameState.LEVEL)){
            upBound = -50;
            downBound = 3260;
            leftBound = -50;
            rightBound = 2773;
        }else if(screenCoordinator.beatLvl1() && screenCoordinator.getGameState().equals(GameState.NEWLEVEL) ||screenCoordinator.getGameState().equals(GameState.LEVEL2)){
            upBound = -50;
            downBound = 2967;
            leftBound = -50;
            rightBound = 4229;
        }else if(screenCoordinator.getGameState().equals(GameState.FLOOR1)){
            upBound = -50;
            downBound = 2967;
            leftBound = -50;
            rightBound = 4229;
        }

        // update player's animation
        super.update();
    }

    public void setInteractionRange(){
        this.interactionRange = 100;
    }
    public void resetInteractionRange(){
        this.interactionRange = 1;
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

        if (getCurrentFrameIndex() == 10) {
            playerState = PlayerState.ROCK_ATTACK;
            playerState = PlayerState.STANDING;
            i = 0;
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
            if(this.getX() + moveAmountX - walkSpeed > leftBound){
                moveAmountX -= walkSpeed;
            }
            facingDirection = Direction.LEFT;
            currentWalkingXDirection = Direction.LEFT;
            lastWalkingXDirection = Direction.LEFT;
        }

        // if walk right key is pressed, move player to the right
        else if (Keyboard.isKeyDown(MOVE_RIGHT_KEY)) {
            if(this.getX() + moveAmountX - walkSpeed < rightBound){
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
            if(this.getY() + moveAmountX - walkSpeed > upBound){
            moveAmountY -= walkSpeed;
            }
            facingDirection = Direction.UP;
            currentWalkingYDirection = Direction.UP;
            lastWalkingYDirection = Direction.UP;
        }
        else if (Keyboard.isKeyDown(MOVE_DOWN_KEY)) {
            if(this.getY() + moveAmountX - walkSpeed < downBound){
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
    public void touchedEnemy() {
        for (Enemy enemy : map.getActiveEnemies()) {
            if(enemy instanceof BearEnemy && (stickRectangle.intersects(enemy) || slingshotRectangle.intersects(enemy))){ 
                enemy.hurtEnemy();  
            } else if (enemy instanceof BreakableLog && (stickRectangle.intersects(enemy) || slingshotRectangle.intersects(enemy))){ 
                enemy.hurtEnemy();
            } else if(enemy instanceof BreakableBranch && (stickRectangle.intersects(enemy) || slingshotRectangle.intersects(enemy))){ 
                enemy.hurtEnemy();
            }  else if(enemy instanceof EvilMushroom && (stickRectangle.intersects(enemy) || slingshotRectangle.intersects(enemy))) {
                enemy.hurtEnemy();
            } 
        }
    }

    public void hurtPlayer() {
        if(playerHP>0){
            if(iFrames == 0) {
                playerHP--;
                iFrames = 60;
                hurt = true;
                hurtTrue();
                System.out.println("Player is hit\n" + getCurrentHealth());
                health.decreaseHealth();
            }
        }
        
        // when the player's HP gets down to 0, they die and have to restart from the beginning of the level
        if(playerHP == 0) {
            screenCoordinator.setGameState(GameState.DEATH);
            System.out.println("Player is dead");
            playerHP = 5;
        }
    }

    public boolean getHurt(){
        return hurt;
    }

    public static int getCurrentHealth(){
        return playerHP;
    }

    public static void resetHealth(){
        playerHP = 5;
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
        // stickRectangle.drawBounds(graphicsHandler, Color.BLACK);
        slingshotRectangle.drawBounds(graphicsHandler, Color.BLACK);
    }
}
