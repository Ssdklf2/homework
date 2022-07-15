package ex9.dao;

public interface RequestConstants {

    String INSERT_STUDENT_NAME_DATE =
            "INSERT INTO students (student_name, birth_date) " +
            "VALUES ( ?, ? )";
    String DELETE_STUDENT_WHERE_ID = "DELETE FROM students WHERE student_id = ?";

    String UPDATE_STUDENTS =
            "UPDATE students " +
            "SET student_name = ?, birth_date = ?" +
            "WHERE student_id = ?";
    String SELECT_FROM_STUDENTS =
            "SELECT student_name, birth_date " +
            "FROM students";

    String UPDATE_STUDENTS_SUBJECTS =
            "UPDATE students_subjects " +
            "SET subject_id = ? " +
            "WHERE student_id = ? AND subject_id = ?";

    String DELETE_FROM_STUDENTS_SUBJECTS =
            "DELETE FROM students_subjects " +
            "WHERE student_id = ? AND subject_id = ?";
    String SELECT_STUDENT_NAME_SUBJECT_DESC =
            "SELECT student_name, subject_desc " +
            "FROM students_subjects " +
            "JOIN students USING (student_id) " +
            "JOIN subjects USING (subject_id) " +
            "ORDER BY subject_desc";
    String SELECT_SUBJECT_ID_DESC =
            "SELECT subject_id, subject_desc " +
            "FROM students_subjects " +
            "JOIN students USING (student_id) " +
            "JOIN subjects USING (subject_id) " +
            "WHERE student_id = ?";
    String SELECT_STUDENT_WHERE_SUBJECT =
            "SELECT student_id, student_name, birth_date " +
            "FROM students_subjects " +
            "JOIN students USING (student_id) " +
            "JOIN subjects USING (subject_id) " +
            "WHERE subject_id = ?";

    String INSERT_SUBJECT_DESC =
            "INSERT INTO subjects (subject_desc) " +
            "VALUES (?)";
    String DELETE_FROM_SUBJECTS =
            "DELETE FROM subjects " +
            "WHERE subject_id = ?";
    String SELECT_SUBJECT_DESC =
            "SELECT subject_desc " +
            "FROM subjects";
    String INSERT_STUDENT_ID_SUBJECT_ID =
            "INSERT INTO students_subjects (student_id, subject_id) " +
            "VALUES ( ?, ?)";
     String UPDATE_SUBJECTS =
             "UPDATE subjects SET subject_desc = ? " +
             "WHERE subject_id = ?";


}
