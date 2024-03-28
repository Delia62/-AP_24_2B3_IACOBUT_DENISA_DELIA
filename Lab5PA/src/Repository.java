import java.io.File;
import java.util.*;

public class Repository {
    private File rootDirectory;
    private Map<Person, List<Document>> documents;

    public Repository(File rootPath) {
        this.rootDirectory = rootPath;
        this.documents = new HashMap<>();
        if (!rootDirectory.exists() || !rootDirectory.isDirectory()) {
            throw new IllegalArgumentException("Root directory does not exist or is not a directory.");
        }
    }

    public void getEmployeesDocuments(List<Person> employees) {
        for (Person person : employees) {
            List<Document> documents1 = new ArrayList<>();
            File personDirectory = new File(rootDirectory, String.valueOf(person.getId()));
            try {
                if (personDirectory.exists() && personDirectory.isDirectory()) {
                    for (File file : personDirectory.listFiles()) {
                        try {
                            if (file.isFile()) {
                                String fileName = file.getName();
                                String fileFormat = fileName.substring(fileName.lastIndexOf('.') + 1);
                                documents1.add(new Document(fileName, fileFormat));
                            }
                        } catch (NullPointerException  e) {
                            System.out.println("File not found...");
                        }
                    }
                }
            } catch (SecurityException e) {
                System.out.println("Person directory not found..");
            }
            documents.put(person, documents1);
        }
    }

    public void displayRepository(List<Person> persons) {
        try {
            getEmployeesDocuments(persons);
            for (Map.Entry<Person, List<Document>> entry : documents.entrySet()) {
                Person person = entry.getKey();
                List<Document> documents1 = entry.getValue();
                System.out.println("Employee: " + person.getName() + " [id: " + person.getId() + "]");
                for (Document document : documents1) {
                    System.out.println("  - [name]:" + document.getName() + ", [format]: " + document.getFormat());
                }
                System.out.println();
            }
        } catch (NullPointerException e) {
            System.out.println("Employees not found..");
        }
    }
}