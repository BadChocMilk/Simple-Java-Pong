import java.awt.*;
import java.net.URL;


public class Ball extends Rectangle {

    double ballSpeed;
    double defaultBallSpeed;
    double ballAngle;
    double xSpeed;
    double ySpeed;
    int defaultX;
    int defaultY;
    Scoreboard score;
    FilePlayer audio;
    URL beepSound, tadaSound;

    public static final int SLOW = 5;
    public static final int NORMAL = 10;
    public static final int FAST = 20;

    public Ball(int x, int y, Scoreboard score){
        super.width = 20;
        super.height = 20;
        super.x = x;
        super.y = y;
        this.score = score;
        ballAngle = 0;
        defaultBallSpeed = 10;
        ballSpeed = defaultBallSpeed;
        defaultX = x;
        defaultY = y;
        
        speedFinder();

        // this handles all the audio for the ball hitting the wall and scoring.
        audio = new FilePlayer();
        beepSound = getClass().getResource("beep.wav");
        tadaSound = getClass().getResource("tada.wav");
    }

    public void setDefaultBallSpeed(double defaultBallSpeed) {
        this.defaultBallSpeed = defaultBallSpeed;
    }

    public void ballTick(){
        // every game tick, this is called to update the posision of the ball and checks to see if the ball needs to be respawned. 
        super.x += xSpeed;
        super.y += ySpeed;
        ballRespawn();
    }

    public void ballRespawn(){
        // respawns the ball back to its original position and adds a score to the counter. 
        if(super.x <=0 || super.x >= ((defaultX+10)*2)-20){
            ballAngle = score.scored(this);
            super.x = defaultX;
            super.y = defaultY;
            ballSpeed = defaultBallSpeed;
            speedFinder();
            audio.play(tadaSound);
        }
    }

    public void wallCollision(){
        // called whenever a collision with the top and bottom walls occur
        ballAngle = 2*Math.PI - ballAngle;

        // shifts the ball depending on if it hit the top or bottom wall
        if(ballAngle <= Math.PI){
            super.y = 21;
        }else{
            // logic here is (defaultY+10)*2 is the total height, and - 41 is 20 from the wall, 20 from the ball, and 1 more pixel to keep it shifted away.
            // remember that the y position of the ball is at the top of it, thats why. 
            super.y = (defaultY+10)*2-41;
        }
        speedFinder();
        audio.play(beepSound);

    }

    public void speedFinder(){
        // any time speedfinder is called, it calculates the x and y speeds based on the angle of the ball. Simple trigonomotry. 
        xSpeed = ballSpeed * Math.cos(ballAngle);
        ySpeed = ballSpeed * Math.sin(ballAngle);

    }

    public void paddleCollision(double paddleY, double paddleHeight, double paddleSpeed){

        // the logic behind the paddle reflection is a bit complicated.
        double ballpos = super.y + 10;
        // ballpos shifts the y position of the ball to be in the middle of it, rather than the top of it. 

        // first it finds the distance between the ball's position and the top of the paddle, then divides it by total length of the paddle
        double angleFinder = Math.abs(ballpos - paddleY)/paddleHeight;

        // this will give a number between 0 and 1, sometimes when the ball is hit on the top or bottom of the paddle it will be outside this range. 
        // these two statements make it so if the angle is too far out of range, it will adjust it. This is mostly to ensure the ball does not have too steep of an angle. 
        // it helps keep the game flowing nicer, as well as prevents it from bouncing behding the paddle.
        if(angleFinder < 0.15){angleFinder = 0.15;}
        else if(angleFinder > 0.85){angleFinder = 0.85;}

        // this multiplies the percentage in anglefinder by pi, to find the actual angle. 
        angleFinder = Math.PI * angleFinder;

        // because 0 rads starts at the east facing position and goes clockwise, this checks whether or not the angle of the ball is heading west. 
        if(ballAngle <= 3*Math.PI/2 && ballAngle >= Math.PI/2){
            // multiplying it by 3pi/2 gives the east equivalent angle.
            angleFinder = angleFinder + 3*Math.PI/2;

            // there are cases where the angle would be over 2pi rads, this subtracts that from it so it can give the correct angle staring at 0rads.
            if(angleFinder > 2*Math.PI){
                angleFinder = angleFinder - 2*Math.PI;
            }
            // it then assigns the new angle to ball angle and shifts the ball over slightly to avoid it getting stuck in the paddle.
            ballAngle = angleFinder;
            super.x += 2;
        }else{
            // if it is heading eastward, all that needs to be done to find the angle is to subtract the new angle from 3pi/2 rads.
            angleFinder = 3*Math.PI/2 - angleFinder;
            // this would then give an angle that is facing west. nothing else needs to be done since the angle will not exceed 2pi rads.
            ballAngle = angleFinder;
            // shifts the ball over again so it does not get stuck
            super.x -= 2;
        }

        // this line adjusts the speed of the ball based on the speed of the paddle. This gives the game a bit more depth. 
        ballSpeed = defaultBallSpeed + Math.abs(paddleSpeed);
        System.out.println(ballSpeed);
        speedFinder();
        audio.play(beepSound);
    }

    public void stopBall(){
        this.ballSpeed = 0;
        speedFinder();
        super.y = 1000000;
    }

    public void setSpeed(double speed){
        this.defaultBallSpeed = speed;
        this.ballSpeed = speed;
        speedFinder();
    }
    
}
