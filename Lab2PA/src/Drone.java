import java.time.LocalTime;

public class Drone extends Vehicle{  //Iacobut Denisa-Delia
    private int maxDuration;

    /**
     *
     * @param name
     * @param maxDuration
     */
    public Drone(String name, int maxDuration)
    {
        super(name);
        this.maxDuration = maxDuration;
    }

    /**
     *
     * @return
     */
    public int getCapacity(){
        return 0;
    }
}
