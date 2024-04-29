package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GenreDAO {
    private String name;

    GenreDAO() {

    }

    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into genre (name) values (?)")) { //???am tabela genre?
            pstmt.setString(1, name);
            pstmt.executeUpdate();
        }
    }
}
