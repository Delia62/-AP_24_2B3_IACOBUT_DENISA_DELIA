package org.example;

import java.util.List;

public class Driver  {
    private String name;
    private int age;
    private List<String> destinations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<String> destinations) {
        this.destinations = destinations;
    }

    public Driver(List<String> destinations, String name, int age) {
        this.destinations = destinations;
        this.name = name;
        this.age = age;
    }

    public Driver() {
    }

    @Override
    public String toString() {
        return "Driver{" +
                "name='" + name + '\'' +
                ", destinations=" + destinations +
                '}';
    }
}
