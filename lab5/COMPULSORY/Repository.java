package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private String directory;
    private List<Person> persons;
    private Map<Person, List<Document>> documents = new HashMap<>();

    public Repository(String directory) {
        this.directory = directory;
        this.persons = new ArrayList<>();
        loadDocuments();
    }

    private void loadDocuments() {
        File masterDir = new File(directory);
        File[] personDirs = masterDir.listFiles(File::isDirectory);
        //cat timp mai am directoare in masterDirectory le parcurg
        if (personDirs != null) {
            for (File personDir : personDirs) {
                String[] nameAndIndex = personDir.getName().split("_");
                String personName = nameAndIndex[0];
                String personId = nameAndIndex[1];

                //creez o persoana pe care o adaug intr o lista pentru a tine evidenta angajatilor
                Person person = new Person(personId, personName);
                persons.add(person);

                // Load documents for each person
                File[] docFiles = personDir.listFiles();
                if (docFiles != null) {
                    List<Document> personDocuments = new ArrayList<>();
                    for (File docFile : docFiles) {
                        personDocuments.add(new Document(docFile.getName()));
                    }
                    documents.put(person, personDocuments);
                }
            }
        }
    }

    public void displayRepositoryContent() {
        System.out.println("Content of the repository:");
        for (Person person : persons) {
            System.out.println("Person: " + person.name() + ", ID: " + person.id());
            List<Document> personDocs = documents.get(person);
            if (personDocs != null) {
                for (Document document : personDocs) {
                    System.out.println("  Document: " + document.fileName());
                }
            }
        }
    }
}

