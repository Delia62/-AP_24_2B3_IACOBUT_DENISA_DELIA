import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class TravelPlan {  //Iacobut Denisa-Delia
    private List<Attraction> attractions;
    private  LocalDate date;

    public TravelPlan(List<Attraction> attractions, LocalDate date) {
        this.attractions = attractions;
        this.date = date;
    }

    public TravelPlan() {
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
     public void addAttractions(Attraction attraction){
        this.attractions.add(attraction);
     }

    @Override
    public String toString() {
        return "TravelPlan{" +
                "attractions=" + attractions +
                ", date=" + date +
                '}';
    }
}
