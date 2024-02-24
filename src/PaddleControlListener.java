import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PaddleControlListener extends KeyAdapter{
    GameCanvas gc;
    
    public PaddleControlListener(GameCanvas canvas){
        this.gc = canvas;
    }

    @Override
    public void keyPressed(KeyEvent e){

        int keyCode = e.getKeyCode();
        String key = KeyEvent.getKeyText(keyCode);

        // handles the arrow keys on MacOS
        key = macArrowKeys(key);

        switch (key){
            case "Up":
                gc.rightPaddle.paddleDirection("Up");
                break;
            case "Down":
                gc.rightPaddle.paddleDirection("Down");
                break;
            case "W":
                gc.leftPaddle.paddleDirection("Up");
                break;
            case "S":
                gc.leftPaddle.paddleDirection("Down");
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        String key = KeyEvent.getKeyText(keyCode);

        // handles the arrow keys on MacOS
        key = macArrowKeys(key);

        switch (key){
            case "Up":
                gc.rightPaddle.paddleStop("Up");
                break;
            case "Down":
                gc.rightPaddle.paddleStop("Down");
                break;
            case "W":
                gc.leftPaddle.paddleStop("Up");
                break;
            case "S":
                gc.leftPaddle.paddleStop("Down");
                break;
        }
    }
    
    public String macArrowKeys(String key){
        // apparently mac's arrow keys are handled differently. This corrects that. 
        if(key.equals("↑")){
            return "Up";
        }
        else if(key.equals("↓")){
            return "Down";
        }
        else{
            return key;
        }      
    }
}
