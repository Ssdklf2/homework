package ex9.dao;

import java.sql.*;

public interface Requests {

    void add();

    void delete();

    void readTable();

    void change();

    default PreparedStatement getPreparedStatement(String sql) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/StudentsAndSubjects",
                "postgres", "");
        return connection.prepareStatement(sql);
    }
}
