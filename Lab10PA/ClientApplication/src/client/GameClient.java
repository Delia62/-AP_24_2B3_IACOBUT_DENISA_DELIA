package client;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameClient extends Thread{
    int playerNr;
    int turn;
    MainFrame frame;
    private final String serverAdress = "127.0.0.1";
    private final int PORT = 2434;
    private boolean running=true;
    private int shipsAttack1 = 0;
    private int shipsAttack2 = 0;
    @Override
    public void run(){
        try{
            Socket socket = new Socket(serverAdress,PORT);
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String request,response;
            Scanner systemIn = new Scanner(System.in);



            while(running) {
                if (turn == playerNr) {
                    request = systemIn.nextLine();
                    if (request.equals("exit")) {
                        running = false;
                        System.exit(1);
                    }

                    out.println(request);
                }
                response = null;
                while (response == null)
                    response = in.readLine();
                System.out.println(response);
                System.out.flush();
                if (response.length() >= 6 && response.substring(0, 6).equals("Player")) {
                    turn = playerNr = 0;
                } else if (response.length() >= 28 && response.substring(0, 28).equals("Game started! You are Player")) {
                    playerNr = response.charAt(28) - '0';
                    frame = new MainFrame();
                    frame.setVisible(true);
                    turn = 1;
                } else if (response.length() >= 7 && response.substring(0, 7).equals("Placed ")) {
                    response = response.substring(7);
                    String parts[] = response.split(" ", 2);
                    int line = parts[0].charAt(0) - 'A' + 1;
                    int column = Integer.parseInt(parts[0].substring(1));
                    if(frame.board.checkBoard(line, column)){
                        if(playerNr == 1){
                            shipsAttack2++;
                            if(shipsAttack2 == 4){
                                JFrame frame = new JFrame("Game Over");
                                frame.setSize(400, 300);
                                JLabel label1;
                                label1 = new JLabel("Player 1 won");
                                label1.setFont(new Font("Arial", Font.BOLD, 23));

                                frame.add(label1);
                                frame.setLocationRelativeTo(null);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setVisible(true);
                                running = false;
                            }
                        }
                        else {
                            shipsAttack1++;
                            if(shipsAttack1 == 4){
                                JFrame frame = new JFrame("Game Over");
                                frame.setSize(400, 300);
                                JLabel label1;
                                label1 = new JLabel("Player 2 won!");
                                label1.setFont(new Font("Arial", Font.BOLD, 23));

                                frame.add(label1);
                                frame.setLocationRelativeTo(null);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setVisible(true);
                                running = false;
                            }
                        }
                        //System.out.println("INAINTE DE COLORAREEEE");
                        frame.board.place(line, column,Color.green);
                        //System.out.println("HITTT");
                        response = "Hit "+playerNr;
                        out.println(response);
                        out.flush();

                    }
                    else{
                        response = "Miss ";
                        out.println(response);
                       // System.out.println("YOU MISSSSSS, HEEHEEE");
                        Color color = new Color(Integer.parseInt(parts[1]));
                        frame.board.place(line, column, color);
                        response = "You miss! Waiting for the other player's move..";
                        //Color color = new Color(Integer.parseInt(parts[1]));
                        //frame.board.place(line,column,color);


                    }
                    System.out.println(response);
                    System.out.flush();
                    turn = turn%2 + 1;

                } else if (response.length() >= 30 && response.substring(0, 9).equals("Time's up")) {
                    // System.out.println(response);
                    //out.println(response);

                    JFrame frame = new JFrame("Game Over");
                    frame.setSize(400, 300);
                    JLabel label1;
                    label1 = new JLabel("Player " + playerNr + " lost the game");
                    label1.setFont(new Font("Arial", Font.BOLD, 23));

                    frame.add(label1);
                    frame.setLocationRelativeTo(null);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    running = false;
                }
            }
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        } catch (IOException ex) {
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}