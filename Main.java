import java.awt.Dimension;
import java.awt.event.KeyAdapter;

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
        mainWindow.setVisible(true);



        gameCanvas.start();
    }
}
