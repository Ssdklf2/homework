package ex9.dao;

import ex9.entity.Student;
import ex9.entity.Subject;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class RequestsStudentSubject implements Requests {
    @Override
    public void add() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add a student to a subject: ");
        try (Statement st = getStatement()) {
            System.out.print("Student`s id: ");
            int idStud = sc.nextInt();
            System.out.print("Subject`s id: ");
           int idSubj = sc.nextInt();
            st.execute("INSERT INTO students_subjects " +
                    "VALUES " + "(" + idStud + "," + idSubj + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Remove a student from a subject:");
        System.out.print("Enter the student`s id: ");
        int idStud = sc.nextInt();
        System.out.print("Enter the subject`s id: ");
        int idSubj = sc.nextInt();
        try (Statement st = getStatement()) {
            st.execute("DELETE FROM students_subjects " +
                    "WHERE student_id = " + idStud + "AND subject_id = " + idSubj);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void readTable() {
        System.out.println("____\nList of student names and subjects: \n____");
        try (Statement st = getStatement()) {
            ResultSet resultSet = st.executeQuery(
                    """
                            SELECT student_name, subject_desc
                            FROM students_subjects\s
                            JOIN students USING (student_id)
                            JOIN subjects USING (subject_id)
                            ORDER BY subject_desc;""");
            while (resultSet.next()) {
                String subj = resultSet.getString("subject_desc");
                String stud = resultSet.getString("student_name");
                System.out.println("Subject: " + subj + "\n" +
                        "Student`s name: " + stud +
                        "\n__________");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void change() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Change student data (another subject):");
        try (Statement st = getStatement()) {
            System.out.print("Student`s id: ");
            int idStud = sc.nextInt();
            System.out.print("Subject`s id: ");
            int idSubj = sc.nextInt();
            System.out.print("Enter new subject id: ");
            int idNewSubj = sc.nextInt();
            st.execute("UPDATE students_subjects " +
                    "SET subject_id = '" + idNewSubj + "' " +
                    "WHERE student_id =" + idStud + "AND subject_id = " + idSubj);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Получить все предметы, которые изучает студент
     *
     * @return список всех предметов студента
     */
    public ArrayList<Subject> getAllSubjects() {
        System.out.println("Get a list of subjects: ");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter student id: ");
        int id = sc.nextInt();
        ArrayList<Subject> subjectsList = new ArrayList<>();
        try (Statement st = getStatement()) {
            ResultSet rs = st.executeQuery(
                    "SELECT subject_id, subject_desc \n" +
                            "FROM students_subjects\n" +
                            "JOIN students USING (student_id)\n" +
                            "JOIN subjects USING (subject_id)\n" +
                            "WHERE student_id = " + id);
            while (rs.next()) {
                int idSubj = rs.getInt("subject_id");
                String desc = rs.getString("subject_desc");
                subjectsList.add(new Subject(idSubj, desc));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return subjectsList;

    }

    /**
     * Узнать всех студентов, которые изучают предмет
     *
     * @return список всех студентов предмета
     */
    public ArrayList<Student> getAllStudents() {
        System.out.println("Get a list of students: ");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter subject id: ");
        int id = sc.nextInt();
        ArrayList<Student> studentsList = new ArrayList<>();
        try (Statement st = getStatement()) {
            ResultSet rs = st.executeQuery(
                    "SELECT student_id, student_name, birth_date \n" +
                            "FROM students_subjects\n" +
                            "JOIN students USING (student_id)\n" +
                            "JOIN subjects USING (subject_id)\n" +
                            "WHERE subject_id = " + id);
            while (rs.next()) {
                int idStud = rs.getInt("student_id");
                String name = rs.getString("student_name");
                Date date = rs.getDate("birth_date");
                studentsList.add(new Student(idStud, name, date));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentsList;
    }
}
