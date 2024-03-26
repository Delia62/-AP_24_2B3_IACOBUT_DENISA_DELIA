import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
       File directory = new File("C:\\Users\\Delia\\Documents\\DirectorPA");
        Person person1 = new Person("Marian", 123, directory);
        Person person2 = new Person("Ana", 456, directory);

        List<Person> personList = new ArrayList<>();
        personList.add(person1);
        personList.add(person2);

        Repository repository = new Repository(directory);

        repository.displayRepository(personList);
    }
}