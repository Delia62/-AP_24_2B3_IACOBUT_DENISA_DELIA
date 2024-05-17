package server;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Timer extends Thread{
    Player player;
    boolean done=false;
    boolean running=false;
    long startedAt;
    public Timer(Player player){
        this.player=player;
    }

    public void setStartedAt(long startedAt) {
        this.startedAt = startedAt;
    }

    @Override
    public void run(){
        startedAt=System.currentTimeMillis();
        while(!done){
            while(running){
                if(System.currentTimeMillis()-startedAt>10000){
                    running=false;
                    done=true;

                }
            }
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean isDone() {
        return done;
    }
}