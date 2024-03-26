package org.example;
import java.util.*;
import java.util.stream.Collectors;
import com.github.javafaker.Faker;

public class Main {
    public static void main(String[] args) {

        Faker faker = new Faker();
        List<Driver> drivers = new ArrayList<>();
        List<Person> passengers = new ArrayList<>();

        List<String> driverDestination = new ArrayList<>();


        for (int i = 0; i < 5; i++) {
            String driverName = faker.name().fullName();
            int driverAge = faker.number().numberBetween(18, 60);
            for( int j=0; j<1; j++){
                String destination = faker.address().city();
                driverDestination.add(destination);
            }
            drivers.add(new Driver(driverDestination, driverName, driverAge));

            String passengerName = faker.name().fullName();
            int passengerAge = faker.number().numberBetween(10, 80);
            String passengerDestination = faker.address().city();
            passengers.add(new Person(passengerName, passengerDestination, passengerAge));
        }
        Problem problem = new Problem(drivers, passengers);

        Map<String, List<String>> destinationMap = problem.getDestinationMap();
        System.out.println("Destination map is " + destinationMap);

        problem.getDriverDestinations();

        System.out.println("Match drivers and passengers: ");
        problem.matchDriversAndPassengers();


        /*
        Proba sa vad daca merge bine cand sunt destinatii care se intersecteaza
        Person person1 = new Person("Iasi","Ana",20);
        Person person2 = new Person("Roman","Ionel", 35);
        Person person3 = new Person("Brasov", "Paul",23);
        Person person4 = new Person("Cluj", "Kira",18);

        List<String> destination1 = new ArrayList<>();
        destination1.add("Bacau");
        destination1.add("Iasi");
        destination1.add("Suceava");

        List<String> destination2 = new ArrayList<>();
        destination2.add("Opar");
        destination2.add("Bucuresti");
        destination2.add("Brasov");

        List<String> destination3 = new ArrayList<>();
        destination3.add("Constanta");
        destination3.add("Mngalz");
        destination3.add("Suc");

        List<String> destination4 = new ArrayList<>();

        destination4.add("Panama");
        destination4.add("Roman");
        destination4.add("Italia");



        Driver driver1 = new Driver(destination1,"Ioan", 23);
        Driver driver2 = new Driver(destination2,"Marian", 28);
        Driver driver3 = new Driver(destination3,"Ioana", 30);
        Driver driver4 = new Driver(destination4,"Paul", 18);

        drivers.add(driver1);
        drivers.add(driver2);
        drivers.add(driver3);
        drivers.add(driver4);


       passengers.add(person1);
       passengers.add(person2);
       passengers.add(person3);
       passengers.add(person4);*/


       /*
       //Compulsory
       Person person1 = new Person("Iasi","Ana",20);
        Person person2 = new Person("Roman","Ionel", 35);
        Person person3 = new Person("Iasi", "Paul",23);
        Person person4 = new Person("Cluj", "Kira",18);


        people.add(person1);
        people.add(person2);
        people.add(person3);
        people.add(person4);

        List<Person> drivers = people
                .stream()
                .filter(person -> person.getDestination().equals("Iasi"))
                .collect(Collectors.toList());

        TreeSet<Person> passengers = people
                .stream()
                .filter(person -> !person.getDestination().equals("Iasi"))
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getName))));

        drivers.sort(Comparator.comparingInt(Person::getAge));
        System.out.println("Passegers: ");
        passengers.forEach(System.out::println);
        System.out.println("Drivers: ");
        drivers.forEach(System.out::println);
        */
    }
}
