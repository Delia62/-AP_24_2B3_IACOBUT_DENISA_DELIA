import java.time.LocalDate;
import java.util.Map;
import java.time.Duration;
import java.time.LocalTime;

public interface Visitable {  //Iacobut Denisa-Delia
    Map<LocalDate, TimeInterval> getTimetable();
    default LocalTime getOpeningHour(LocalDate date) {
        Map<LocalDate, TimeInterval> timetable = getTimetable();
        TimeInterval interval = timetable.get(date);
        if (interval != null) {
            return interval.getFirst();
        }
        return null;
    }

    default LocalTime getClosingHour(LocalDate date) {
        Map<LocalDate, TimeInterval> timetable = getTimetable();
        TimeInterval interval = timetable.get(date);
        if (interval != null) {
            return interval.getSecond();
        }
        return null;
    }

    static Duration getVisitingDuration(LocalTime openingHour, LocalTime closingHour) {
        return Duration.between(openingHour, closingHour);
    }
}

