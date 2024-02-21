import java.awt.*;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class Ball extends Rectangle {

    double ballSpeed;
    double ballAngle;
    String direction;
    double xSpeed;
    double ySpeed;
    int defaultX;
    int defaultY;
    FilePlayer audio;


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
        audio = new FilePlayer();
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
            audio.play("./tada.wav");
        }
    }

    public void wallCollision(){

        ballAngle = 2*Math.PI - ballAngle;
        speedFinder();

        audio.play("./beep.wav");

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
        if(angleFinder < 0.20){angleFinder = 0.20;}
        else if(angleFinder > 0.8){angleFinder = 0.8;}

        angleFinder = Math.PI * angleFinder;
        if(ballAngle <= 3*Math.PI/2 && ballAngle >= Math.PI/2){
            angleFinder = angleFinder + 3*Math.PI/2;
            if(angleFinder > 2*Math.PI){
                angleFinder = angleFinder - 2*Math.PI;
            }
            ballAngle = angleFinder;
            super.x += 2;
        }else{
            angleFinder = 3*Math.PI/2 - angleFinder;
            ballAngle = angleFinder;
            super.x -= 2;
        }
        ballSpeed = 10 + Math.abs(paddleSpeed)/2;
        speedFinder();
        audio.play("./beep.wav");
    }
    
}
