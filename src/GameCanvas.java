import java.awt.*;
import java.awt.image.BufferStrategy;

public class GameCanvas extends Canvas implements Runnable {

    private Thread thread;
    private BufferStrategy bufferStrategy;
    private Wall topWall;
    private Wall bottomWall;
    private Ball gameBall;
    public Paddle leftPaddle;
    public Paddle rightPaddle;
    private Scoreboard score;
    private boolean running = false;
    private double totalBallSpeed = 10;

    public void start(){
        running = true;
        this.createBufferStrategy(2);
        bufferStrategy = this.getBufferStrategy();

        thread = new Thread(this, "Game Thread");
        thread.start();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double delta = 0;
        int updatesPerSecond = 60;
        this.addComponents();
        
        while(true){
            long now = System.nanoTime();
            delta += (now - lastTime) / (double) (1000000000 / updatesPerSecond);
            lastTime = now;

            while(delta >= 1){
                tick();
                delta--;               
            }

            render();

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
            }

        }
    }

    public void restartGame(){
        addComponents();
    }

    private void tick(){

        this.wallIntersection();
        this.paddleIntersection();
        this.checkIfGameWon();

        gameBall.ballTick();
        rightPaddle.paddleTick();
        leftPaddle.paddleTick();
        gameBall.setSpeed(totalBallSpeed);

    }

    private void render(){
        Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics();

        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0, this.getWidth(), this.getHeight());
        g2d.setColor(Color.WHITE);
        g2d.fillRect(this.getWidth()/2-5, 0, 10, this.getHeight());
        g2d.fill(topWall);
        g2d.fill(bottomWall);
        g2d.fill(leftPaddle);
        g2d.fill(rightPaddle);
        g2d.fill(gameBall);

        g2d.setFont(new Font("Ariel", Font.BOLD, 48));
        g2d.drawString(Integer.toString(score.getPlayer1Score()), (this.getWidth()/2)-77, 100);
        g2d.drawString(Integer.toString(score.getPlayer2Score()), (this.getWidth()/2)+50, 100);
        g2d.drawString(score.getWinmessage(), this.getWidth()/2-153, this.getHeight()/2);

        g2d.dispose();
        bufferStrategy.show();

    }

    private void addComponents(){

        score = new Scoreboard();

        gameBall = new Ball((this.getWidth()/2)-10, (this.getHeight()/2)-10, score);
        topWall = new Wall(this.getWidth(), 0);
        bottomWall = new Wall(this.getWidth(), this.getHeight()-20);
        rightPaddle = new Paddle(this.getWidth()-40, this.getHeight()/2-50, this.getHeight());
        leftPaddle = new Paddle(25, this.getHeight()/2-50, this.getHeight());

        this.addKeyListener(new PaddleControlListener(this));
    }

    private void wallIntersection(){
        if(gameBall.intersects(bottomWall) || gameBall.intersects(topWall)){
            gameBall.wallCollision();
            gameBall.ballTick();
        }
    }

    private void paddleIntersection(){
        if(gameBall.intersects(rightPaddle)){
            gameBall.paddleCollision(rightPaddle.getY(), rightPaddle.getHeight(), rightPaddle.speed);
            gameBall.ballTick();
        }
        if(gameBall.intersects(leftPaddle)){
            gameBall.paddleCollision(leftPaddle.getY(), leftPaddle.getHeight(), rightPaddle.speed);
            gameBall.ballTick();
        }
    }

    private void checkIfGameWon(){

        if(!score.getWinmessage().equals("")){
            gameBall.stopBall();
        }
    }

    public void setBallSpeed(double speed){
        this.totalBallSpeed = speed;
    }

    public boolean isRunning(){
        return running;
    }
}