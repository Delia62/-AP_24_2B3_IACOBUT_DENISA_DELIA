import java.time.LocalTime;

public class Client{     //Iacobut Denisa-Delia
    public enum ClientType {
        REGULAR,
        PREMIUM;
    }
    private ClientType type;
    private String name;
    private LocalTime minTime;
    private LocalTime maxTime;
    public Client(String name, ClientType type){
        this.name = name;
        this.type = type;
        this.minTime = null;
        this.maxTime = null;
    }
    public Client(){}
    public Client(String name, LocalTime minTime, LocalTime maxTime){
        this.name = name;
        this.maxTime = maxTime;
        this.minTime = minTime;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public ClientType getType(){
        return this.type;
    }
    public void setType(ClientType type){
        this.type = type;
    }
    public void setMinTime(LocalTime minTime){
        this.minTime = minTime;
    }
    public void setMaxTime(LocalTime maxTime){
        this.maxTime = maxTime;
    }
    public LocalTime getMinTime(){
        return this.minTime;
    }
    public LocalTime getMaxTime(){
        return this.maxTime;
    }

    @Override
    public String toString() {
        return "Client{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", minTime=" + minTime +
                ", maxTime=" + maxTime +
                '}';
    }
}

