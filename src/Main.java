import java.awt.Dimension;
import javax.swing.*;

public class Main {
    
    public static void main(String[] args) {
        JFrame mainWindow = new JFrame("PONG");
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.setSize(new Dimension(960, 540));
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setResizable(false);

        GameCanvas gameCanvas = new GameCanvas();
        mainWindow.add(gameCanvas);
        Menus menu = new Menus(mainWindow, gameCanvas);
        mainWindow.setJMenuBar(menu);
        mainWindow.setVisible(true);



        
    }
}
