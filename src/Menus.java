import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.management.JMException;
import javax.swing.*;

public class Menus extends JMenuBar{
    
    JFrame mainWindow;
    GameCanvas gameCanvas;

    public Menus(JFrame window, GameCanvas gameCanvas){
        this.mainWindow = window;
        this.gameCanvas = gameCanvas;

        createMenuItems();
    }

    private void createMenuItems(){

        JMenu gameMenu = new JMenu("Game");
        JMenu ballSpeed = new JMenu("Ball Speed");

        JMenuItem start = new JMenuItem("Start");
        JMenuItem restart = new JMenuItem("Restart");
        JMenuItem quit = new JMenuItem("Quit");
        JMenuItem slow = new JMenuItem("Slow");
        JMenuItem normal = new JMenuItem("Normal");
        JMenuItem fast = new JMenuItem("Fast");

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(!gameCanvas.isRunning()){
                    gameCanvas.start();
                }
            }
        });

        restart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gameCanvas.restartGame();
            }
        });

        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });

        gameMenu.add(start);
        gameMenu.add(restart);
        gameMenu.add(quit);

        slow.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gameCanvas.setBallSpeed(Ball.SLOW);
            }
        });

        normal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gameCanvas.setBallSpeed(Ball.NORMAL);
            }
        });

        fast.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                gameCanvas.setBallSpeed(Ball.FAST);
            }
        });

        ballSpeed.add(slow);
        ballSpeed.add(normal);
        ballSpeed.add(fast);

        this.add(gameMenu);
        this.add(ballSpeed);

    }
}
