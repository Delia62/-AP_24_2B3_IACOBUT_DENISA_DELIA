import java.time.LocalDate;
import java.util.Map;

public class Statue extends Attraction implements Visitable {  //Iacobut Denisa-Delia
    private String description;
    private Map<LocalDate, TimeInterval> timetable;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Statue(String name, String description) {
        super(name);
        this.description = description;
    }

    public void setTimetable(Map<LocalDate, TimeInterval> timetable) {
        this.timetable = timetable;
    }

    public Statue(String name) {
        super(name);
    }

    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return this.timetable;
    }



}
