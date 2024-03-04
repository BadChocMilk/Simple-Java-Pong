import java.net.URL;

public class PowerUp extends Ball{

    private boolean enabled = false;
    private boolean blueBall;
    URL powerupSound, powerdownSound;



    public PowerUp(int x, int y){      
        super(x, y, null);
        super.ballSpeed = 0;
        speedFinder();
        powerupSound = getClass().getResource("powerup.wav");
        powerdownSound = getClass().getResource("powerdown.wav");
  
    }

    public boolean exists(){
        return enabled;
    }

    public boolean isBlue(){
        return blueBall;
    }

    public void spawnPowerUp(){
        enabled = true;
        super.x = defaultX;
        super.y = defaultY;
        if (Math.random()< 0.5){
            blueBall = true;
        } 
        else{
            blueBall = false;
        }
        super.ballSpeed = super.defaultBallSpeed;
        super.speedFinder();

    }

    @Override
    public void ballRespawn(){
        if(super.x <=0 || super.x >= ((defaultX+10)*2)-20){
            super.x = defaultX;
            super.y = defaultY;
            ballSpeed = 0;
            speedFinder();
            enabled = false;
        }
    }

    public void paddleCollision(Paddle paddle){
        if(blueBall){
            paddle.increaseSize();
            audio.play(powerupSound);
        }
        else{
            paddle.decreaseSize();
            audio.play(powerdownSound);
        }

        super.x = 10000;
    }
    
}
