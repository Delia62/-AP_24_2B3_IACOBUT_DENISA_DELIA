package org.example;

import java.util.List;
import java.util.Objects;

public class Person {
    private String destination;
    private String name;
    private int age;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(String destination, String name, int age) {
        this.destination = destination;
        this.name = name;
        this.age = age;
    }


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "destination='" + destination + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return  Objects.equals(destination, person.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, name, age);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
