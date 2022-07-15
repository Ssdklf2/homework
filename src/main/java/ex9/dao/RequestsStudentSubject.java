package ex9.dao;

import ex9.entity.Student;
import ex9.entity.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class RequestsStudentSubject implements Requests, RequestConstants {

    @Override
    public void add() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add student to subject list:");
        try (PreparedStatement st = getPreparedStatement(INSERT_STUDENT_ID_SUBJECT_ID)) {
            System.out.print("Student id: ");
            int idStud = sc.nextInt();
            System.out.print("Subject id: ");
            int idSubj = sc.nextInt();
            st.setInt(1, idStud);
            st.setInt(2, idSubj);
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Remove student from a subject list:");
        System.out.print("Enter student id: ");
        int idStud = sc.nextInt();
        System.out.print("Enter subject id: ");
        int idSubj = sc.nextInt();
        try (PreparedStatement st = getPreparedStatement(DELETE_FROM_STUDENTS_SUBJECTS)) {
            st.setInt(1, idStud);
            st.setInt(2, idSubj);
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void readTable() {
        System.out.println("____\nList of student names and subjects: \n____");
        try (PreparedStatement st = getPreparedStatement(SELECT_STUDENT_NAME_SUBJECT_DESC)) {
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                String subj = resultSet.getString("subject_desc");
                String stud = resultSet.getString("student_name");
                System.out.printf("Subject: %s \n" +
                        "Student name: %s", subj, stud);
                System.out.println("\n__________");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void change() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Change student data (another subject):");
        try (PreparedStatement st = getPreparedStatement(UPDATE_STUDENTS_SUBJECTS)) {
            System.out.print("Student id: ");
            int idStud = sc.nextInt();
            System.out.print("Subject id: ");
            int idSubj = sc.nextInt();
            System.out.print("Enter new subject id: ");
            int idNewSubj = sc.nextInt();
            st.setInt(1, idNewSubj);
            st.setInt(2, idStud);
            st.setInt(3, idSubj);
            st.execute();
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
        try (PreparedStatement st = getPreparedStatement(SELECT_SUBJECT_ID_DESC)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setDescription(rs.getString("subject_desc"));
                subjectsList.add(subject);
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
        try (PreparedStatement st = getPreparedStatement(SELECT_STUDENT_WHERE_SUBJECT)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("student_id"));
                student.setName(rs.getString("student_name"));
                student.setBirthDate(rs.getDate("birth_date"));
                studentsList.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentsList;
    }
}
