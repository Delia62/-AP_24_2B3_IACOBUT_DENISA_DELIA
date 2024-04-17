import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game {
    private final Bag bag;
    private final List<Player> players;
    private final int n;
    private boolean gameOver;
    private long startTime;
    private long timeLimit;
    private int currentPlayerIndex; //folosesc in functia de validare a secventei

    private ScheduledExecutorService timekeeper;

    public Game(int n, long timeLimit) {
        this.n = n;
        this.timeLimit = timeLimit;
        bag = new Bag(n);
        players = new ArrayList<>();
        gameOver = false;
        currentPlayerIndex = 0; // Începem cu primul jucător din listă

        timekeeper = Executors.newSingleThreadScheduledExecutor();
    }

    public synchronized void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }


    public synchronized void start() {
        startTime = System.currentTimeMillis();
        for (Player player : players) {
            new Thread(player).start();
        }

        // Schedule the timekeeper task to run every 100 milliseconds
        timekeeper.scheduleAtFixedRate(() -> {
            long endTime = startTime + timeLimit;
            checkGameEnd();
            if (System.currentTimeMillis() >= endTime || gameOver) {
                timekeeper.shutdown();
                System.out.println("Time's up! Game over!");
                setGameOver();
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }

    public synchronized boolean isGameOver() {
        return gameOver;
    }

    public synchronized void setGameOver() {
        gameOver = true;
        System.exit(0);
    }



    public Bag getBag() {
        return bag;
    }


    public synchronized void checkGameEnd() { //verificare daca jocul s-a terminat, fie un jucator a castigat fie nu mai sunt tokenuri de extras
        boolean allTokensExtracted = bag.getTokens().isEmpty();
        boolean sequenceOfLengthNFormed = false;
        Player winner = null;

        for (Player player : players) {
            if (player.getMaxSequenceLength() == n - 1) {
                sequenceOfLengthNFormed = true;
                winner = player;

            }
        }

        if (allTokensExtracted || sequenceOfLengthNFormed) {
            if (winner != null) {
                System.out.println(" Winner: " + winner.getName() + " with score: " + winner.getScore());
            } else {
                System.out.println("Game over! All token were removed from the bag");
                for(Player player : players){
                    System.out.println(player.getName() + " done with the score "+player.getScore());
                }
            }
            gameOver = true;
            System.exit(0);
        }
    }

    // Metoda pentru a obține următorul jucător în listă
    private Player getNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        return players.get(currentPlayerIndex);
    }

    // Metoda pentru a notifica următorul jucător din listă
    public synchronized void notifyNextPlayer() {
        Player currentPlayer = players.get(currentPlayerIndex);
        currentPlayer.setMyTurn(false); // Jucătorul curent și-a terminat rândul
       Player nextPlayer = getNextPlayer();
        synchronized (nextPlayer) {
            nextPlayer.setMyTurn(true);
            nextPlayer.notify(); // Notificăm jucătorul următor să înceapă
        }
    }


    public synchronized void shutdownTimekeeper() {
        if (timekeeper != null) {
            timekeeper.shutdown();
        }
    }
}



