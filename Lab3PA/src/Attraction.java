import java.time.LocalDate;
import java.util.Map;
import java.time.Duration;
import java.time.LocalTime;

public abstract class Attraction
        implements Comparable<Attraction> {  //Iacobut Denisa-Delia
    private String name;

    Map<LocalDate, TimeInterval> getSchedule() {
        return null;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Attraction(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Attraction{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Attraction other) {
        if(other.name != null){
            return this.name.compareTo(other.name);
        }
        return 0;

    }
}
