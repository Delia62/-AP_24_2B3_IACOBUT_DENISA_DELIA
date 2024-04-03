package org.example;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File rootDirectory = new File("C:\\Users\\Delia\\Documents\\DirectorPA");

        Repository manager = new Repository(rootDirectory);
        manager.shell();


       /* Command listCommand = new ListDocuments(repository);
        Command viewCommand = new ViewDocumentCommand(repository, "123", "dummy.pdf");
        Command reportCommand = new GenerateReportCommand(repository);
        Command exportCommand = new Export(repository, new File("output.json"));


        listCommand.execute();
        viewCommand.execute();
        reportCommand.execute();
        exportCommand.execute();*/
    }
}