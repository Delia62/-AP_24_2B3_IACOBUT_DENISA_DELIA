public class Main {
    public static void main(String[] args) {
        int n = 10000;
        long timeLimit = 10000; // Limita de timp în milisecunde (60000 ms = 60 secunde)

        Game game = new Game(n, timeLimit);

        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        Player player3 = new Player("Player 3");


        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);

        player1.setPlayer();

        game.start();
    }
}