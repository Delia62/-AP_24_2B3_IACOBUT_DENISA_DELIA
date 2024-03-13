

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


public class Main {  //Iacobut Denisa-Delia
    public static void main(String args[]) {
        Museum museum = new Museum("Museum1");
        museum.setTimetable();
        museum.setTicketPrice(20);

/////LA SORTARE NU PREA ESTE DUPA ORA DE DESCHIDERE

        Church church = new Church("Church1");

        Map<LocalDate,TimeInterval> localDateTimeIntervalMap = new HashMap<>();
        TimeInterval timeInterval = new TimeInterval(LocalTime.of(12,30),LocalTime.of(20,10));
        localDateTimeIntervalMap.put(LocalDate.of(2024,8,20), timeInterval);

        church.setTimetable(localDateTimeIntervalMap);
        museum.setTimetable(localDateTimeIntervalMap);

        System.out.println(church.getOpeningHour(LocalDate.of(2024,8,20)));

        Statue statue = new Statue("Statuie1");
        statue.setTimetable(localDateTimeIntervalMap);

        Concert concert = new Concert("Concert1");
        concert.setTimetable(localDateTimeIntervalMap);

        List<Attraction> attractions = new ArrayList<>();
        attractions.add(museum);
        attractions.add(church);
        attractions.add(statue);
        attractions.add(concert);

        Trip trip = new Trip();
        trip.setCity("Roma");
        trip.setAttractions(attractions);
        trip.setStart(LocalDate.of(2024,9,13));
        trip.setEnd(LocalDate.of(2024,12,20));

        System.out.println(museum);
        System.out.println(church);
        System.out.println(statue);
        System.out.println(concert);
        System.out.println(museum.getTicketPrice());
        System.out.println(trip);
        trip.OrderedAttractions();

        TravelPlan travelPlan = new TravelPlan();
        travelPlan.setDate(LocalDate.of(2025, 9,12));
        travelPlan.setAttractions(attractions);

        System.out.println(travelPlan);



    }
}