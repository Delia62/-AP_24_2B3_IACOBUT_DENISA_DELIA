import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            if (personDirectory.exists() && personDirectory.isDirectory()) {
                for (File file : personDirectory.listFiles()) {
                    if (file.isFile()) {
                        String fileName = file.getName();
                        String fileFormat = fileName.substring(fileName.lastIndexOf('.') + 1);
                        documents1.add(new Document(fileName, fileFormat));
                    }
                }
            }
            documents.put(person, documents1);
        }
    }

    public void displayRepository(List<Person> persons) {
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
    }
}
