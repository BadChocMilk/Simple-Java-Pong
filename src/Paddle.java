import java.awt.Rectangle;

public class Paddle extends Rectangle {

    double speed;
    double acceleration;
    static final double MAXSPEED = 10;
    int levelHeight;
    boolean movingUp = false;
    boolean movingDown = false;

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

    public void paddleDirection(String direction){
        if(direction == "Up"){
            movingUp = true;
        }
        else if(direction == "Down"){
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
        if(super.y >= levelHeight-119){
            acceleration = 0;
            speed = 0;
            super.y = levelHeight -120;
        }
        if(super.y <= 19){
            acceleration = 0;
            speed = 0;
            super.y = 20;
        }
    }


    
}
