import java.awt.Rectangle;

public class PowerUp extends Ball{

    private boolean enabled = false;


    public PowerUp(int x, int y){      
        super(x, y, null);
  
    }

    private void spawnPowerUp(){
        if (Math.random()< 0.5){
            
        } 

    }

    @Override
    public void ballTick(){
        super.x += xSpeed;
        super.y += ySpeed;
    }
    
}
