package org.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        File directory = new File("C:\\Users\\Delia\\Documents\\DirectorPA");
       /* Person person1 = new Person("Marian", 123, directory);
        Person person2 = new Person("Ana", 456, directory);

        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);

        Repository repository = new Repository(directory);

        repository.listDocuments();
*/
        File rootDirectory = new File("C:\\Users\\Delia\\Documents\\DirectorPA");
        Repository repository = new Repository(rootDirectory);


        Command listCommand = new ListDocuments(repository);
        Command viewCommand = new ViewDocumentCommand(repository, "123", "dummy.pdf");
        Command reportCommand = new GenerateReportCommand(repository);
        Command exportCommand = new Export(repository, new File("output.json"));


        //listCommand.execute();
        //viewCommand.execute();
        //reportCommand.execute();
        //exportCommand.execute();
    }
}