package org.example;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){
        Person person1 = new Person("Iasi","Ana",20);
        Person person2 = new Person("Roman","Ionel", 35);
        Person person3 = new Person("Iasi", "Paul",23);
        Person person4 = new Person("Cluj", "Kira",18);

        List<Person> people = new ArrayList<>();
        people.add(person1);
        people.add(person2);
        people.add(person3);
        people.add(person4);

        List<Person> drivers = people.stream()
                .filter(person -> person.getDestination().equals("Iasi")).collect(Collectors.toList());

        TreeSet<Person> passengers = people.stream()
                .filter(person -> !person.getDestination().equals("Iasi"))
                .collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Person::getName))));

        drivers.sort(Comparator.comparingInt(Person::getAge));
        System.out.println("Passegers: ");
        passengers.forEach(System.out::println);
        System.out.println("Drivers: ");
        drivers.forEach(System.out::println);
    }


}