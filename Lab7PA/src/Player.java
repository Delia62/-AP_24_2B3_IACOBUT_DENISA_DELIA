
import java.util.ArrayList;
import java.util.List;

public class Player implements Runnable {
    private final String name;
    private Game game;
    private int score;
    private boolean myTurn;
    private List<Token> tokens;


    public Player(String name) {
        this.name = name;
        score = 0;
        myTurn = false;
        tokens = new ArrayList<>();
    }

    public void setPlayer() {
        myTurn = true;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void run() {
        while (!game.isGameOver()) {
            synchronized (this) {
                game.checkGameEnd();
                while (!myTurn && !game.isGameOver()) {
                    try {
                        wait(); // Așteptăm semnalul de la Game pentru a începe rândul
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if (game.isGameOver()) {
                break;
            }
            Token token = game.getBag().extractTokens();
            if(this.score == 0){ // daca este la primul token extras
                score++;
            }
            tokens.add(token);

            if(evaluateSequence(tokens)) //daca secventa este valida
            {
                this.score++;
            }

             System.out.println(name + " extracted token: " + token);

            synchronized (game) {
                myTurn = false; // Jucătorul și-a terminat rândul
                game.notifyNextPlayer(); // Notificăm Game să înceapă rândul jucătorului următor
            }
        }
    }

    public static boolean evaluateSequence(List<Token> tokens) {
        int count = 0;
        if (tokens.size() < 2) {
            return false; // secventa trebuie sa contina cel putin doua jetoane
        }
       Token lastToken = tokens.get(tokens.size() - 1);
        // verificam consecutivitatea si inchiderea secventei
        for (int i = 0; i < tokens.size() - 1; i++) {
            Token currentToken = tokens.get(i);
            if (currentToken.getSecondNumber() == lastToken.getFirstNumber()) { //daca al doilea numar din prima pereche este egal cu primul numar din a doua pereche
                count++;
            } else {
                if (currentToken.getFirstNumber() == lastToken.getSecondNumber()) { //daca al doilea numar din a doua pereche este egal cu primul numar din prima pereche--in pereche numerele sunt strict crescatoare deci este posibil si acest caz
                    count++;
                }
            }
        }
        if(count != 0){ //am gasit alt token ce se potriveste cu unul pe care il aveam deja -- avem o secventa
            return true;
        }
        else {
            return false;
        }
    }

    public int getMaxSequenceLength() {
        return score;
    }
}
