package server;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientThread extends Thread{
    private Socket socket = null;
    private boolean running=true;
    private Game game;
    private final GameServer server;
    public int attackedShips1 = 0;
    public int attackedShips2 = 0;
    private int playerNr;
    BufferedReader in;
    PrintWriter out;
    String response;
    private long start = System.currentTimeMillis();
    private long end; // 30 seconds›

    public ClientThread(Socket socket,GameServer server){
        this.socket=socket;
        this.server=server;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void place(int line, int column, Color color){
        char letter = (char) (line+'A');
        column++;
        out.println("Placed "+letter+column+" "+color.getRGB());
        out.flush();
    }
    public void won(String response){
        out.println(response+" You can start a new game now");
        out.flush();
        game=null;
    }
    @Override
    public void run(){
        try {

            String request,response;
            while(running) {
                System.out.println("running = " + running);
                request = in.readLine();
                System.out.println("request is "+request);
                if (game != null && game.outOfTime(playerNr) && game.isTurn(playerNr)) {
                    server.gameList.remove(game);
                    response = "Time's up, Player "+ playerNr+" lose! Player " + (playerNr % 2 + 1) + " won!";
                }
                else{
                    System.out.println("pe else");
                        if (request.equals("stop")) {
                            running = false;
                            response = "Server stopped";
                        } else if (request.equals("create game")) {
                            System.out.println("pe create game");
                            if (game != null)
                                response = "You're already in a game!";
                            else {
                                response = "Starting new game! Please enter a game name so others can join:";
                                out.println(response);
                                out.flush();
                                request = in.readLine();
                                game = server.createGame(request);
                                if (game == null)
                                    response = "There already is a game with that name! Try joining it or choosing a different name";
                                else {
                                    game.setPlayer1(new Player(this, Color.RED));
                                    playerNr = 1;
                                    response = "Game started! You are Player1. It's your turn!";
                                    end = start + 60 * 1000;

                                    while (!game.isGameReady()) {
                                        try {
                                            sleep(10);
                                        } catch (InterruptedException ex) {
                                            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    if (game.isTurn(playerNr)) {
                                        if (System.currentTimeMillis() > end) {
                                            server.gameList.remove(game);
                                            response = "Time's up, Player " + playerNr + " lose! Player " + (playerNr % 2 + 1) + " won!";
                                            game = null;
                                        }
                                        else{
                                            response = "Not your turn yet!";
                                        }
                                    }
                                    game.setTurn(1);
                                }
                            }
                        }

                        else if (request.equals("join game")) {
                            if (game != null)
                                response = "You're already in a game!";
                            else {
                                response = "Joining a game! Please enter the name of the game you want to join:";
                                out.println(response);
                                out.flush();
                                request = in.readLine();
                                game = server.findGame(request);
                                if (game == null)
                                    response = "There is no game with that name! Try creating it or choosing a different name";
                                else {
                                    game.setPlayer2(new Player(this, Color.BLUE));
                                    playerNr = 2;
                                    response = "Game started! You are Player2. Please wait your turn!";
                                    end = start + 60 * 1000;
                                    game.setTurn(2);
                                }
                            }
                        } else if (request.equals("submit move")) {
                            System.out.println("in submit");
                            if (game == null)
                                response = "You have to join a game first!";
                            else {
                                if (game.isTurn(playerNr)) {
                                    if (System.currentTimeMillis() > end) {
                                        response = "Time's up, Player "+ playerNr+" lose! Player " + (playerNr % 2 + 1) + " won!";
                                        server.gameList.remove(game);
                                        out.println(response);
                                        out.flush();
                                        break;
                                    }

                                    response = "Please select position to place on (ex: A1):";
                                    if (System.currentTimeMillis() > end) {
                                        server.gameList.remove(game);
                                        response = "Time's up, Player " + playerNr + " lose! Player " + (playerNr % 2 + 1) + " won!";
                                        game = null;
                                    }
                                    out.println(response);
                                    out.flush();
                                    System.out.flush();
                                    request = in.readLine();
                                    int line = request.charAt(0) - 'A' + 1;
                                    int column = Integer.parseInt(request.substring(1));
                                    if (game.isFree(line, column)) {
                                        game.setPiece(line, column,playerNr);

                                        request = in.readLine();
                                        if(request.substring(0,3).equals("Hit")){

                                            request = request.substring(4);
                                            int player = Integer.parseInt(request);
                                            if(player == 1){
                                                attackedShips2++;

                                            }
                                            else{
                                                attackedShips1++;
                                            }

                                            System.out.println("1");
                                        }
                                        else if(request.substring(0,4).equals("Miss")){


                                        }

                                        game.setTurn(playerNr % 2 + 1);
                                        end = System.currentTimeMillis()+ 60 * 1000;
                                    } else
                                        response = "Position is already taken or is out of bounds";
                                        out.println(response);
                                        out.flush();

                                } else {
                                    response = "It's not your turn yet!";
                                    out.println(response);
                                    out.flush();
                                }
                            }
                        } else if (request.equals("leave game")) {
                            if (game == null)
                                response = "You aren't in a game yet!";
                            else {
                                server.gameList.remove(game);
                                game.setWon("Player" + playerNr + " left the game, so Player" + (playerNr % 2 + 1) + " won! You can join a new game now");
                                response = null;
                            }
                        } else
                            response = "Server received the request '" + request + "' but it doesn't do anything";
                    }

                    if (response != null) {
                        out.println(response);
                        out.flush();
                    }
                }

            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        } catch (IOException e) {
            System.err.println("Communication error..."+e);
        }
    }
}