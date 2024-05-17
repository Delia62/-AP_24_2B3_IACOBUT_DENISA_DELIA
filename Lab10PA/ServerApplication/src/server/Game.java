package server;

public class Game {
    private Player player1,player2;
    private String name;
    int[][] position = new int[15][15];
    public int[][] board1 = new int[10][10];
    public int [][] board2 = new int[10][10];
    public int[][] ships1 = new int[10][10];
    public int[][] ships2 = new int[10][10];

    public void setShips1(int[][] ships1) {
        this.ships1 = ships1;
    }

    public void setShips2(int[][] ships2) {
        this.ships2 = ships2;
    }

    boolean won=false;
    int turn;
    public Game(String name) {
        this.name = name;
    }
    public void setPlayer1(Player player){
        player1 = player;
    }
    public void setPlayer2(Player player){
        player2 = player;
    }
    public boolean isGameReady(){
        return player1!=null && player2!=null;
    }
    public String getName(){
        return name;
    }
    public boolean isFree(int lin, int col){
        if(lin>10||col>10) return false;
        return position[lin][col]==0;
    }
    public void setPiece(int lin, int col, int playerNr){

        if(playerNr==1){
            board1[lin][col] = 1;
            lin--;
            col--;
            player1.thread.place(lin,col,player1.color);
            player2.thread.place(lin,col,player1.color);
        }
        else {
            board2[lin][col] = 1;
            lin--;
            col--;
            player2.thread.place(lin,col,player2.color);
            player1.thread.place(lin,col,player1.color);
        }
        position[lin][col]=playerNr;
        //return checkHit(lin, col, playerNr);
    }
    /*public boolean checkHit(int lin, int col, int playerNr){
        if(playerNr == 1){

        }
        else{

        }
    }*/
    public void setWon(String response){
        player1.thread.won(response);
        player2.thread.won(response);
    }
    public void setTurn(int playerNr){
        turn=playerNr;
        if(playerNr==1){
            player1.timer.running=true;
            player2.timer.running=false;
        }
        else{
            player2.timer.running=true;
            player1.timer.running=false;
        }
    }
    public boolean isTurn(int playerNr){
        return playerNr==turn;
    }
    public boolean outOfTime(int playerNr){
        if(playerNr==1)
            return player1.timer.done;
        return player2.timer.done;
    }
    public Player getPlayer(int playerNr){
        if(playerNr==1)
            return player1;
        else return player2;
    }
}