package org.example;

import org.example.Command;

import java.io.IOException;

public class ViewDocumentCommand implements Command {
    private Repository repository;
    private String person;
    private String documentName;

    @Override
    public void execute() throws IOException {
        repository.viewDocument(person,documentName);
    }

    public ViewDocumentCommand(Repository repository, String person, String documentName) {
        this.repository = repository;
        this.person = person;
        this.documentName = documentName;
    }
}
