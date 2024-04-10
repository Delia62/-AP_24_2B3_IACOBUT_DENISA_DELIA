import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Game {
    private final List<Player> players;
    private final Bag bag;

    public Game(int numberOfPlayers, int n) {
        bag = new Bag(n);
        players = new ArrayList<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player(bag));
        }
    }

    public void startGame() {
        ExecutorService executor = Executors.newFixedThreadPool(players.size());
        for (Player player : players) {
            executor.execute(player);
        }
        executor.shutdown();//dupa ce toti jucatorii au fost adaugati, executorul este oprit si nu se mai accepta alte firme de executie.
        while (!executor.isTerminated()) {
            // Asteptam ca toate firele sa se termine
        }
        displayScores();
    }

    private void displayScores() {
        System.out.println("Final scores:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getScore());
        }
    }
}
