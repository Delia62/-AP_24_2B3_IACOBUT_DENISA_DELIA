package org.example;

import java.io.File;

public class Person {
    private String name;
    private int id;
    private File directory;

    public Person(String name, int id, File directory) {
        this.name = name;
        this.id = id;
        this.directory = directory;
    }

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public File getDirectory() {
        return directory;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }
}
