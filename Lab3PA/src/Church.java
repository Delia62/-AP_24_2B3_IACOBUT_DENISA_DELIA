import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Church extends Attraction implements Visitable {  //Iacobut Denisa-Delia
    private String address;
    private Map<LocalDate, TimeInterval> timetable;
    private String description;

    public void setTimetable(Map<LocalDate, TimeInterval> timetable) {
        this.timetable = timetable;
    }

    public Church(String name, String address, String description) {
        super(name);
        this.address = address;
        this.description = description;
    }

    public Church(String name) {
        super(name);
    }



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public Map<LocalDate,TimeInterval> getTimetable() {
        return timetable;
    }


}

