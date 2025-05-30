package org.example;


import java.io.IOException;

public class GenerateReportCommand implements Command {
    private Repository repository;

    public GenerateReportCommand(Repository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() throws IOException {
        repository.generateReport();
    }
}
