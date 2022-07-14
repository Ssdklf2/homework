package ex9.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public interface Requests {
    void add();

    void delete();

    void readTable();

    void change();

    default Statement getStatement() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/StudentsAndSubjects",
                "postgres", "][po=-09");
        return connection.createStatement();
    }
}
