import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Trip {  //Iacobut Denisa-Delia
    private String city;
    private LocalDate start, end;
    private List<Visitable> visitables = new ArrayList<>();

    public Trip() {
    }

    public Trip(String city, LocalDate start, LocalDate end) {
        this.city = city;
        this.start = start;
        this.end = end;
    }

    public Trip(String city, LocalDate start, LocalDate end, List<Visitable> visitables) {
        this.city = city;
        this.start = start;
        this.end = end;
        this.visitables = visitables;
    }

    public String getCity() {
        return city;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public List<Visitable> getAttractions() {
        return visitables;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public void setAttractions(List<Visitable> visitables) {
        this.visitables = visitables;
    }
    public void orderedAttractions(List<Visitable> visitables, LocalDate date){
        List<Visitable> nonPayableAttractions = new ArrayList<>();
        for (Visitable visitable : visitables) {
            if (visitable.getOpeningHour(date) != null && !(visitable instanceof Payable)) {
                nonPayableAttractions.add(visitable);
            }
        }

        Collections.sort(nonPayableAttractions, new Comparator<Visitable>() {
            @Override
            public int compare(Visitable v1, Visitable v2) {
                return v1.getOpeningHour(date).compareTo(v2.getOpeningHour(date));
            }
        });
        System.out.println("Not payable visitable attractions:");
        for (Visitable attraction : nonPayableAttractions) {
            System.out.println("Location: " + attraction );
        }
    }

    @Override
    public String toString() {
        return "Trip{" +
                "city='" + city + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", attractions=" + visitables +
                '}';
    }

}