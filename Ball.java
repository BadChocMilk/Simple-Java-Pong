import java.awt.*;

public class Ball extends Rectangle {

    double ballSpeed;
    double ballAngle;
    String direction;
    double xSpeed;
    double ySpeed;
    int defaultX;
    int defaultY;


    public Ball(int x, int y){
        super.width = 20;
        super.height = 20;
        super.x = x;
        super.y = y;
        ballAngle = 0;
        ballSpeed = 10;
        defaultX = x;
        defaultY = y;
        
        speedFinder();
    }

    public void ballTick(){
        super.x += xSpeed;
        super.y += ySpeed;
        ballRespawn();

    }

    public void ballRespawn(){
        if(super.x <=0 || super.x >= ((defaultX+10)*2)-20){
            ballAngle = 0;
            super.x = defaultX;
            super.y = defaultY;
            ballSpeed = 10;
            speedFinder();
        }
    }

    public void wallCollision(){

        ballAngle = 2*Math.PI - ballAngle;
        speedFinder();

    }

    public void speedFinder(){
        xSpeed = ballSpeed * Math.cos(ballAngle);
        ySpeed = ballSpeed * Math.sin(ballAngle);

    }

    public void paddleCollision(double paddleY, double paddleHeight, double paddleSpeed){

        double ballpos = super.y;
        double paddlepos = paddleY-30;
        double measuredHeight = paddleHeight+30;

        double angleFinder = Math.abs(ballpos - paddlepos)/measuredHeight;
        angleFinder = Math.PI * angleFinder;
        if(ballAngle <= 3*Math.PI/2 && ballAngle >= Math.PI/2){
            angleFinder = angleFinder + 3*Math.PI/2;
            if(angleFinder > 2*Math.PI){
                angleFinder = angleFinder - 2*Math.PI;
            }
            ballAngle = angleFinder;
        }else{
            angleFinder = 3*Math.PI/2 - angleFinder;
            ballAngle = angleFinder;
        }
        ballSpeed = 10 + Math.abs(paddleSpeed)/2;
        speedFinder();
    }
    
}
