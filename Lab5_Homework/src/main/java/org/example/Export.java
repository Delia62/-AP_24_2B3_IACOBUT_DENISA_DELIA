package org.example;

import java.io.File;
import java.io.IOException;

public class Export implements Command {
    private Repository repository;
    private File outputFile;

    public Export(Repository repository, File outputFile) {
        this.repository = repository;
        this.outputFile = outputFile;
    }
    @Override
    public void execute() throws IOException {
        repository.exportToJson(outputFile);
    }
}
