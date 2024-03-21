package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class Problem {
    private List<Driver> drivers;
    private List<Person> passengers;

    public Problem(List<Driver> drivers, List<Person> passengers) {
        this.drivers = drivers;
        this.passengers = passengers;
    }

    public void getDriverDestinations() {
        for( Driver driver : drivers){
            System.out.println("[ Driver " + driver.getName() + " is going to " + driver.getDestinations() + "]");
        }
    }

    public Map<String, List<String>> getDestinationMap() {
        Map<String, List<String>> destinationMap = new HashMap<>();
        for (Person passenger : passengers) {
            String destination = passenger.getDestination();
            destinationMap.putIfAbsent(destination, new ArrayList<>());
            destinationMap.get(destination).add(passenger.getName());
        }
        return destinationMap;
    }

    public void matchDriversAndPassengers() {
        for (Driver driver : drivers) {

            List<String> destinations = driver.getDestinations();
            List<Person> matchingPassengers = new ArrayList<>();
            for (String destination : destinations){
                matchingPassengers = passengers.stream()
                        .filter(passenger -> passenger.getDestination().equals(destination))
                        .limit(1)
                        .collect(Collectors.toList());
                if(!matchingPassengers.isEmpty()){
                    break;
                }

            }
            if (!matchingPassengers.isEmpty()) {
                Person passenger = matchingPassengers.getFirst();
                System.out.println("{ Passenger " + passenger.getName() + " is matched with driver " + driver.getName() + "}");
                passengers.remove(passenger);

            }
           else {
                System.out.println("No passenger matched with driver " + driver.getName());
            }
        }
    }
}

