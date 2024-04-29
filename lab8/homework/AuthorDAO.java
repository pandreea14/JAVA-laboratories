package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    public void fetchData() {
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT authors FROM books";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String authors = resultSet.getString("authors");
                        String[] authorList = authors.split(";");
                        for (String author : authorList) {
                            create(author.trim());
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean authorExists(Connection connection, String name) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(
                "SELECT COUNT(*) AS count FROM authors WHERE author_name = ?")) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    return count > 0;
                }
            }
        }
        return false;
    }
    public void create(String name) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            if (!authorExists(connection, name)) {
                try (PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO authors (author_name) VALUES (?)")) {
                    statement.setString(1, name);
                    statement.executeUpdate();
                }
            } else {
                System.out.println("Author already exists in the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT author_id FROM authors WHERE author_name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
    }
    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT author_name FROM authors WHERE author_id='" + id + "'")) {
            return rs.next() ? rs.getString(2) : null;
        }
    }

    // Method to get all authors from the database
    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Getting a connection from the database manager
            connection = Database.getConnection();

            // Example SQL query
            String query = "SELECT * FROM authors";

            // Creating a prepared statement
            preparedStatement = connection.prepareStatement(query);

            // Executing the query
            resultSet = preparedStatement.executeQuery();

            // Processing the result set
            while (resultSet.next()) {
                int id = resultSet.getInt("author_id");
                String name = resultSet.getString("author_name");

                // Creating an Author object and adding it to the list
                Author author = new Author(id, name);
                authors.add(author);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Closing the resources
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return authors;
    }
}