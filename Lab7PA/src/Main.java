
public class Main {
    public static void main(String[] args) {
        int numberOfPlayers = 4;
        int n = 4;

        Game game = new Game(numberOfPlayers, n);
        game.startGame();
    }
}