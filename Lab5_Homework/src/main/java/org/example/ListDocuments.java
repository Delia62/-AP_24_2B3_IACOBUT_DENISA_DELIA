package org.example;

import java.io.IOException;

public class ListDocuments implements Command{
    private Repository repository;
    @Override
    public void execute() throws IOException {
        repository.listDocuments();
    }

    public ListDocuments(Repository repository) {
        this.repository = repository;
    }
}
