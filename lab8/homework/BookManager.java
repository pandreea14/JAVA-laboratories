package org.example;

import java.util.List;

public class BookManager {

    public static void main(String[] args) {
        // import data from CSV file
        Database.importData("M:\\FACULTATE\\ANUL2\\SEM2\\JAVA\\lab8\\data.csv");

        // fetch data and add authors to the authors table
        AuthorDAO authorDAO = new AuthorDAO();
        authorDAO.fetchData();

        // fetch all authors
        List<Author> allAuthors = authorDAO.getAllAuthors();
        System.out.println("All Authors:");
        for (Author author : allAuthors) {
            System.out.println(author.getId() + ": " + author.getName());
        }

        // close connection pool
        Database.closeConnection();
    }
}
