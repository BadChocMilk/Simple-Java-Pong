public class Scoreboard {
    

    private int player1Score;
    private int player2Score;
    private String winmessage;

    public int getPlayer1Score() {
        return player1Score;
    }

    public int getPlayer2Score() {
        return player2Score;
    }

    public String getWinmessage() {
        return winmessage;
    }

    public Scoreboard(){
        this.player1Score = 0;
        this.player2Score = 0;
        this.winmessage = "";
    }

    public double scored(Ball ball){
        double ballPos = ball.getX();
        winner();

        if(ballPos <= 0){
            player2Score++;
            return 0;
        }
        else{
            player1Score++;
            return Math.PI;
        }
    }

    private void winner(){
        if(player1Score == 9){
            winmessage = "Player 1 Wins!";
        }
        else if(player2Score == 9){
            winmessage = "Player 2 Wins!";
        }
    }

}
