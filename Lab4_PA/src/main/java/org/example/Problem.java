/*package org.example;

import javax.management.relation.Role;
import java.util.*;
import java.util.stream.Collectors;

public class Problem {


    private static List<Person> generateRandomPersons(int count) {
        List<Person> persons = new ArrayList<>();
        Random random = new Random();
        List<String> names = Arrays.asList("Alin", "Bogdan", "Paul", "Denis", "Emanuel", "Xenia", "Lucian", "Peter", "Tera", "Mary");


        for (int i = 0; i < count; i++) {
            //Role role = random.nextBoolean() ? Role.DRIVER : Role.PASSENGER;
            String name = names.get(random.nextInt(names.size()));;
            int age = random.nextInt(50) + 20;
            List<Destination> destinations = generateRandomDestinations(random.nextInt(3) + 1);

            persons.add(new Person(destinations,name, age));
        }

        return persons;
    }

    private static List<Destination> generateRandomDestinations(int count) {
        List<Destination> destinations = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < count; i++) {
            destinations.add(new Destination("Destination " + random.nextInt(100)));
        }

        return destinations;
    }
*/
 /*List<Person> persons = generateRandomPersons(10);
        List<Person> drivers = persons.stream().filter(p -> p.getRole() == Role.DRIVER)
                .sorted(Comparator.comparingInt(Person::getAge))
                .collect(Collectors.toList());
        Set<Person> passengers = new TreeSet<>(Comparator.comparing(Person::getName));
        persons.stream().filter(p -> p.getRole() == Role.PASSENGER)
                .forEach(passengers::add);

        System.out.println("Drivers sorted by age:");
        drivers.forEach(System.out::println);

        System.out.println("\nPassengers sorted by name:");
        passengers.forEach(System.out::println);

        List<Destination> destinations = drivers.stream()
                .flatMap(driver -> driver.getDestinations().stream())
                .distinct()
                .collect(Collectors.toList());
        System.out.println("\nDestinations passed by drivers:");
        destinations.forEach(System.out::println);

        Map<Destination, Set<Person>> destinationMap = new HashMap<>();
        persons.forEach(person -> person.getDestinations().forEach(destination ->
                destinationMap.computeIfAbsent(destination, k -> new HashSet<>()).add(person)));

        System.out.println("\nDestination map:");
        destinationMap.forEach((destination, people) ->
                System.out.println(destination + ": " + people.stream().map(Person::getName).collect(Collectors.joining(", "))));
    }*/