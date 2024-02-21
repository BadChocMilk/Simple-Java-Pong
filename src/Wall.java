import java.awt.Rectangle;

public class Wall extends Rectangle{

    public Wall(int width, int height){

        super.x = 0;
        super.y = height;
        super.width = width;
        super.height = 20;
    }
    
}
