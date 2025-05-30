package org.example;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class Repository {
    private File rootDirectory;
    private Map<Person, List<DocumentNew>> documents;

    public Repository(File rootPath) {
        this.rootDirectory = rootPath;
        this.documents = new HashMap<>();
        if (!rootDirectory.exists() || !rootDirectory.isDirectory()) {
            throw new IllegalArgumentException("Root directory does not exist or is not a directory.");
        }
    }
    private void getEmployeesDocuments() {
        File[] employeeDirectories = rootDirectory.listFiles();
        if (employeeDirectories != null) {
            for (File employeeDirectory : employeeDirectories) {
                if (employeeDirectory.isDirectory()) {
                    int id = Integer.parseInt(employeeDirectory.getName());
                    Person person = new Person("Marian", id);
                    List<DocumentNew> employeeDocuments = new ArrayList<>();
                    File[] documents = employeeDirectory.listFiles();
                    if (documents != null) {
                        for (File document : documents) {
                            if (document.isFile()) {
                                String fileName = document.getName();
                                String fileFormat = fileName.substring(fileName.lastIndexOf('.') + 1);
                                employeeDocuments.add(new DocumentNew(fileName, fileFormat));
                            }
                        }
                    }
                    this.documents.put(person, employeeDocuments);
                }
            }
        }
    }
    public void listDocuments() {
        try {
            getEmployeesDocuments();
            for (Map.Entry<Person, List<DocumentNew>> entry : documents.entrySet()) {
                Person person = entry.getKey();
                List<DocumentNew> documents1 = entry.getValue();
                System.out.println("Employee: id " + person.getId() );
                for (DocumentNew document : documents1) {
                    System.out.println("  - [name]:" + document.getName() + ", [format]: " + document.getFormat());
                }
                System.out.println();
            }
        } catch (NullPointerException e) {
            System.out.println("Employees not found..");
        }
    }

    public void viewDocument(String personName, String documentName) throws IOException {
        // Implementarea pentru vizualizarea unui document folosind Desktop API
        File personDirectory = new File(rootDirectory, personName);
        File documentFile = new File(personDirectory, documentName);
        Desktop.getDesktop().open(documentFile);
    }

    public void generateReport() throws IOException {
        // Implementarea pentru generarea raportului HTML utilizând FreeMarker
        // Configurarea FreeMarker și obținerea șablonului HTML
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\Delia\\Documents\\GitHub\\-AP_24_2B3_IACOBUT_DENISA_DELIA\\Lab5PA\\src"));
        configuration.setDefaultEncoding("UTF-8");

        // Crearea unui model de date pentru șablon
        Template template = configuration.getTemplate("report_template.ftl.html");
        Map<String, Object> data = new HashMap<>();
        data.put("documents", documents);

        // Procesarea șablonului și scrierea rezultatului într-un fișier HTML
        File outputHtmlFile = new File(rootDirectory, "report.html");
        try (Writer fileWriter = new FileWriter(outputHtmlFile)) {
            template.process(data, fileWriter);
        } catch (TemplateException e) {
            System.out.println("Error in generating output..");
        }
        // Deschiderea fișierului HTML în browser
        Desktop.getDesktop().open(outputHtmlFile);
    }
    public void exportToJson(File outputFile) throws IOException {
        // Implementarea pentru exportul documentelor în format JSON utilizând Jackson

        getEmployeesDocuments();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(outputFile, documents);
    }
    public void shell() {
        Scanner scanner = new Scanner(System.in);
        Repository repository = new Repository(rootDirectory);


        while (true) {
            System.out.print("Introdu comanda: ");
            String comanda = scanner.nextLine();

            if (comanda.equals("exit")) {
                System.out.println("S-a terminat programul!");
                break;
            }

            String[] parametrii = comanda.split(" ");
            try {
                Command comandaExecutata = null;
                if (parametrii[0].equals("list")) {
                    comandaExecutata = new ListDocuments(repository);
                } else if (parametrii[0].equals("view")) {
                    if (parametrii.length < 3) {
                        throw new FolderException("Numar insuficient de parametri pentru comanda 'view'.");
                    }
                    comandaExecutata = new ViewDocumentCommand(repository, parametrii[1], parametrii[2]);
                } else if (parametrii[0].equals("report")) {
                    comandaExecutata = new GenerateReportCommand(repository);
                } else if (parametrii[0].equals("export")) {
                    if (parametrii.length < 2) {
                        throw new FolderException("Numar insuficient de parametri pentru comanda 'export'.");
                    }
                    comandaExecutata = new Export(repository, new File(parametrii[1]));
                } else {
                    throw new FolderException("Comanda gresita!");
                }
                comandaExecutata.execute();
            } catch (IOException | FolderException e) {
                System.out.println("A aparut eroarea: " + e.getMessage());
            }
        }
    }



}
