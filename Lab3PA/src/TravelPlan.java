import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class TravelPlan {  //Iacobut Denisa-Delia
    private List<Visitable> visitables;
    private  LocalDate date;

    public TravelPlan(List<Visitable> visitables, LocalDate date) {
        this.visitables = visitables;
        this.date = date;
    }

    public TravelPlan() {
    }

    public List<Visitable> getAttractions() {
        return visitables;
    }

    public void setAttractions(List<Visitable> visitables) {
        this.visitables = visitables;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
     public void addAttractions(Visitable attraction){
        this.visitables.add(attraction);
     }

    @Override
    public String toString() {
        return "TravelPlan{" +
                "attractions=" + visitables +
                ", date=" + date +
                '}';
    }
}
