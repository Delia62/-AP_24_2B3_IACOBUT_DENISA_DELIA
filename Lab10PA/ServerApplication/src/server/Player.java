package server;

import java.awt.*;

public class Player {
    ClientThread thread;
    Color color;
    long startedAt;
    Timer timer = new Timer(this);
    public Player(ClientThread thread,Color c){
        this.thread=thread;
        this.color=c;
        startedAt=System.currentTimeMillis();
    }
}