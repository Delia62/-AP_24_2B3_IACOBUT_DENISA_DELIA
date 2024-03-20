import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;



public class Main {  //Iacobut Denisa-Delia
    public static void main(String args[]) {
        Museum museum = new Museum("Museum1");
        LocalDate date = LocalDate.of(2024,9,21);
        museum.setTicketPrice(20);
        Map<LocalDate,TimeInterval> localDateTimeIntervalMap = new HashMap<>();
        TimeInterval timeIntervalForMuseum = new TimeInterval(LocalTime.of(8,10),LocalTime.of(19,10));
        localDateTimeIntervalMap.put(LocalDate.of(2024,9,21), timeIntervalForMuseum);
        museum.setTimetable(localDateTimeIntervalMap);


        Statue statue = new Statue("Statuie1");
        TimeInterval timeIntervalForStatue = new TimeInterval(LocalTime.of(8,30),LocalTime.of(23,10));
        localDateTimeIntervalMap.put(LocalDate.of(2024,9,21), timeIntervalForStatue);
        statue.setTimetable(localDateTimeIntervalMap);

        Church church = new Church("Church1");
        TimeInterval timeIntervalForChurch = new TimeInterval(LocalTime.of(12,30),LocalTime.of(20,10));
        localDateTimeIntervalMap.put(LocalDate.of(2024,9,21), timeIntervalForChurch);
        church.setTimetable(localDateTimeIntervalMap);


        System.out.println(church.getOpeningHour(LocalDate.of(2024,9,21)));



        Concert concert = new Concert("Concert1");
        TimeInterval timeIntervalForConcert = new TimeInterval(LocalTime.of(19,30),LocalTime.of(22,40));
        localDateTimeIntervalMap.put(LocalDate.of(2024,9,21), timeIntervalForConcert);
        concert.setTimetable(localDateTimeIntervalMap);

        List<Visitable> attractions = new ArrayList<>();
        attractions.add(statue);
        attractions.add(museum);
        attractions.add(church);
        attractions.add(concert);

        Trip trip = new Trip();
        trip.setCity("Roma");
        trip.setAttractions(attractions);
        trip.setStart(LocalDate.of(2024,9,21));
        trip.setEnd(LocalDate.of(2024,12,20));

        System.out.println(museum);
        System.out.println(church);
        System.out.println(statue);
        System.out.println(concert);
        System.out.println(museum.getTicketPrice());
        System.out.println(trip);


       trip.orderedAttractions(attractions,date);

        TravelPlan travelPlan = new TravelPlan();
        travelPlan.setDate(LocalDate.of(2025, 9,12));
        travelPlan.setAttractions(attractions);

        System.out.println(travelPlan);

    }
}