import java.time.LocalDate;
import java.util.Map;

public class Museum extends Attraction implements Visitable, Payable{  //Iacobut Denisa-Delia
    private String address;
    private String description;
    private double ticketPrice = 10;
    private Map<LocalDate, TimeInterval> timetable;

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
    public double getTicketPrice() {
        return this.ticketPrice;
    }

    @Override
    public Map<LocalDate, TimeInterval> getTimetable() {
        return this.timetable;
    }

    public void setTimetable(Map<LocalDate, TimeInterval> timetable) {
        this.timetable = timetable;
    }

    public void setTimetable() {

        this.timetable = getTimetable();
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }


    public Museum(String name){
        super(name);
    }
}
