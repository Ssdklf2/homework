package ex9.dao;

import java.sql.*;

public class RequestsStudent implements Requests {

    @Override
    public void add() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/StudentsAndSubjects",
                    "postgres", "][po=-09");
            Statement st = connection.createStatement();
            //insert into students (student_name) VALUES ('Lala');
            ResultSet resultSet = st.executeQuery("SELECT * FROM students");
//            where student_id = " +
//                    "(SELECT MIN(student_id) FROM students)"
            while (resultSet.next()){
                System.out.println(
                        "Name: " + resultSet.getString("student_name") + "\n" +
                        "Date of Birth:" + resultSet.getDate("birth_date") +
                                "\n____");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete() {

    }

    @Override
    public void readTable() {

    }

    @Override
    public void change() {

    }
}
