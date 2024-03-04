import java.awt.Rectangle;

public class Paddle extends Rectangle {

    double speed;
    double acceleration;
    int levelHeight;
    boolean movingUp = false;
    boolean movingDown = false;

    // maxspeed ensures that the paddles won't go too fast.
    static final double MAXSPEED = 10;
    static final int MINHEIGHT = 50;
    static final int MAXHEIGHT = 200;

    public Paddle(int x, int y, int height){
        super.width = 15;
        super.height = 100;
        super.x = x;
        super.y = y;

        this.levelHeight = height;
    }

    public void paddleTick(){

        super.y += speed;
        accelerate();
        paddleMovement();
        wallCollision();
    }
    
    public void accelerate(){
        if(speed >= MAXSPEED){}
        else{
            speed += acceleration;
        }
    }

    public void paddleDirection(String key){
        if(key == "Up"){
            movingUp = true;
        }
        else if(key == "Down"){
            movingDown = true;
        }
    }

    public void paddleStop(String key){
        if(key == "Up"){
            movingUp = false;
        }
        if(key == "Down"){
            movingDown = false;
        }     
    }

    public void paddleMovement(){

        if(movingUp && !movingDown){
            acceleration = -0.5;
        }
        if(!movingUp && movingDown){
            acceleration = 0.5;
        }
        if (movingUp && movingDown){
            speed = 0;
        }
        if(!movingUp && !movingDown){
            acceleration = 0;
            speed = 0;
        }

    }

    public void wallCollision(){

        // the numbers are one pixel off because it will snap back to place. the walls are 20 pixels
        if(super.y >= levelHeight-(super.height+19)){
            acceleration = 0;
            speed = 0;
            super.y = levelHeight -(super.height + 20);
        }
        if(super.y <= 19){
            acceleration = 0;
            speed = 0;
            super.y = 20;
        }
    }

    public void increaseSize(){
        if(super.height < MAXHEIGHT){
            super.height += 25;
        }
    }

    public void decreaseSize(){
        if(super.height > MINHEIGHT){
            super.height -= 25;
        }
    }


    
}
