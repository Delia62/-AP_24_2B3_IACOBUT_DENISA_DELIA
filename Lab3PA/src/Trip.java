import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Trip {  //Iacobut Denisa-Delia
    private String city;
    private LocalDate start, end;
    private List<Attraction> attractions = new ArrayList<>();

    public Trip() {
    }

    public Trip(String city, LocalDate start, LocalDate end) {
        this.city = city;
        this.start = start;
        this.end = end;
    }

    public Trip(String city, LocalDate start, LocalDate end, List<Attraction> attractions) {
        this.city = city;
        this.start = start;
        this.end = end;
        this.attractions = attractions;
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

    public List<Attraction> getAttractions() {
        return attractions;
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

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }
    public void OrderedAttractions(){
        List<Attraction> nonPayableAttractions = new ArrayList<>();
        for (Attraction attraction : attractions) {
            if (attraction instanceof Visitable && !(attraction instanceof Payable)) {
                nonPayableAttractions.add(attraction);
            }
        }
        for (Attraction attraction : nonPayableAttractions) {
            attraction.compareTo(attraction);
        }
       /* nonPayableAttractions.sort((attraction1, attraction2) -> {
            LocalTime openHour1 = attraction1.getOpenHour(start);
            LocalTime openHour2 = attraction2.getOpenHour(start);
            return openHour1.compareTo(openHour2);
        });*/


        System.out.println("Not payable visitable attractions:");
        for (Attraction attraction : nonPayableAttractions) {
            System.out.println("Location: " + attraction );
        }

    }
    @Override
    public String toString() {
        return "Trip{" +
                "city='" + city + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", attractions=" + attractions +
                '}';
    }

}