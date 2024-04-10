class Player implements Runnable {
    private static int nextId = 1; // Variabilă statică pentru următorul id al jucătorului
    private final int id;
    private final String name;
    private final Bag bag;
    private int score;

    public String getName() {
        return name;
    }

    public Player(Bag bag) {
        this.id = nextId++; // Setam id-ul jucatorului si apoi incrementam pentru urmatorul jucator
        this.name = "Player " + id;
        this.bag = bag;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void run() {
        while (true) {
            Token token = bag.extractToken();
            if (token == null) break;
            System.out.println(name + " extracted token: " + token);
            score++;
        }
    }
}
