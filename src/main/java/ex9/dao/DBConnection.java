package ex9.dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

abstract class DBConnection {

    protected PreparedStatement getPreparedStatement(String sql) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        java.sql.Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/StudentsAndSubjects",
                "postgres", "");
        return connection.prepareStatement(sql);
    }

    public static final String INSERT_STUDENT_NAME_DATE =
            "INSERT INTO students (student_name, birth_date) " +
                    "VALUES ( ?, ? )";
    public static final String DELETE_STUDENT_WHERE_ID = "DELETE FROM students WHERE student_id = ?";

    public static final String UPDATE_STUDENTS =
            "UPDATE students " +
                    "SET student_name = ?, birth_date = ?" +
                    "WHERE student_id = ?";
    public static final String SELECT_FROM_STUDENTS =
            "SELECT student_name, birth_date " +
                    "FROM students";

    public static final String UPDATE_STUDENTS_SUBJECTS =
            "UPDATE students_subjects " +
                    "SET subject_id = ? " +
                    "WHERE student_id = ? AND subject_id = ?";

    public static final String DELETE_FROM_STUDENTS_SUBJECTS =
            "DELETE FROM students_subjects " +
                    "WHERE student_id = ? AND subject_id = ?";
    public static final String SELECT_STUDENT_NAME_SUBJECT_DESC =
            "SELECT student_name, subject_desc " +
                    "FROM students_subjects " +
                    "JOIN students USING (student_id) " +
                    "JOIN subjects USING (subject_id) " +
                    "ORDER BY subject_desc";
    public static final String SELECT_SUBJECT_ID_DESC =
            "SELECT subject_id, subject_desc " +
                    "FROM students_subjects " +
                    "JOIN students USING (student_id) " +
                    "JOIN subjects USING (subject_id) " +
                    "WHERE student_id = ?";
    public static final String SELECT_STUDENT_WHERE_SUBJECT =
            "SELECT student_id, student_name, birth_date " +
                    "FROM students_subjects " +
                    "JOIN students USING (student_id) " +
                    "JOIN subjects USING (subject_id) " +
                    "WHERE subject_id = ?";

    public static final String INSERT_SUBJECT_DESC =
            "INSERT INTO subjects (subject_desc) " +
                    "VALUES (?)";
    public static final String DELETE_FROM_SUBJECTS =
            "DELETE FROM subjects " +
                    "WHERE subject_id = ?";
    public static final String SELECT_SUBJECT_DESC =
            "SELECT subject_desc " +
                    "FROM subjects";
    public static final String INSERT_STUDENT_ID_SUBJECT_ID =
            "INSERT INTO students_subjects (student_id, subject_id) " +
                    "VALUES ( ?, ?)";
    public static final String UPDATE_SUBJECTS =
            "UPDATE subjects SET subject_desc = ? " +
                    "WHERE subject_id = ?";
}