import java.time.LocalTime;
import java.util.Objects;

public class Client{     //Iacobut Denisa-Delia
    public enum Type {
        REGULAR,
        PREMIUM;
    }
    private Type type;
    private String name;
    private LocalTime minTime;
    private LocalTime maxTime;

    /**
     *
     * @param name
     * @param type
     */
    public Client(String name, Type type){
        this.name = name;
        this.type = type;
        this.minTime = null;
        this.maxTime = null;
    }

    /**
     *
     */
    public Client(){}
    public Client(String name, LocalTime minTime, LocalTime maxTime){
        this.name = name;
        this.maxTime = maxTime;
        this.minTime = minTime;
    }

    /**
     *
     * @param name
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     *
     * @return
     */
    public String getName(){
        return this.name;
    }

    /**
     *
     * @return
     */
    public Type getType(){
        return this.type;
    }

    /**
     *
     * @param type
     */
    public void setType(Type type){
        this.type = type;
    }

    /**
     *
     * @param minTime
     */
    public void setMinTime(LocalTime minTime){
        this.minTime = minTime;
    }

    /**
     *
     * @param maxTime
     */
    public void setMaxTime(LocalTime maxTime){
        this.maxTime = maxTime;
    }

    /**
     *
     * @return
     */
    public LocalTime getMinTime(){
        return this.minTime;
    }

    /**
     *
     * @return
     */
    public LocalTime getMaxTime(){
        return this.maxTime;
    }

    /**
     *
     * @return
     */

    @Override
    public String toString() {
        return "Client{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                '}';
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return type == client.type && Objects.equals(name, client.name) && Objects.equals(minTime, client.minTime) && Objects.equals(maxTime, client.maxTime);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, name, minTime, maxTime);
    }
}


