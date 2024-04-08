package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String masterDirectoryPath = "M:\\FACULTATE\\ANUL2\\SEM2\\JAVA\\lab5\\master_directory";
        Person person1 = new Person("Andreea", 1);
        Person person2 = new Person("Damon", 2);
        Person person3 = new Person("Lorenzo", 3);

        createDirectory(masterDirectoryPath, person1);
        createDirectory(masterDirectoryPath, person2);
        createDirectory(masterDirectoryPath, person3);

        Repository repo1 = new Repository(masterDirectoryPath + "/" + person1.name());
        Repository repo2 = new Repository(masterDirectoryPath + "/" + person2.name());
        Repository repo3 = new Repository(masterDirectoryPath + "/" + person3.name());

        repo1.displayContent();
        repo2.displayContent();
        repo3.displayContent();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            String[] parts = input.split("\\s+");
            String commandName = parts[0].toLowerCase();

            try {
                switch (commandName) {
                    case "view":
                        if (parts.length < 2) {
                            throw new IllegalArgumentException("Document name is missing.");
                        }
                        new ViewCommand(repo1, parts[1]).execute();
                        break;
                    case "report":
                        new ReportCommand(repo1).execute();
                        break;
                    case "export":
                        if (parts.length < 2) {
                            throw new IllegalArgumentException("File name is missing.");
                        }
                        new ExportCommand(repo1, parts[1]).execute();
                        break;
                    default:
                        System.out.println("Invalid command.");
                }
            } catch (CommandExecutionException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void createDirectory(String masterDirectoryPath, Person person) {
        Path path = Paths.get(masterDirectoryPath, person.name());
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}