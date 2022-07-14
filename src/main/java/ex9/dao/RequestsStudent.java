package ex9.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RequestsStudent implements Requests {

    @Override
    public void add() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add student data: ");
        System.out.print("Enter the student`s name: ");
        String name = sc.nextLine();
        System.out.print("Date of birth: ");
        String date = sc.nextLine();
        try (Statement st = getStatement()) {
            st.execute("INSERT INTO students (student_name, birth_date) " +
                    "VALUES ( '" + name + "', '" + date + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Delete student data:");
        System.out.print("Enter the student`s id: ");
        int id = sc.nextInt();
        try (Statement st = getStatement()) {
            st.execute("DELETE FROM students WHERE student_id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void readTable() {
        System.out.println("____\nStudents list: \n____");
        try (Statement st = getStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT * FROM students");
            while (resultSet.next()) {
                int id = resultSet.getInt("student_id");
                String name = resultSet.getString("student_name");
                Date date = resultSet.getDate("birth_date");
                System.out.println("ID: " + id + "\n" +
                        "Name: " + name + "\n" +
                        "Date of Birth: " + date +
                        "\n__________");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void change() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Change student data:");
        try (Statement st = getStatement()) {
            System.out.print("ID: ");
            String idString = sc.nextLine();
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Date of birth: ");
            String date = sc.nextLine();
            st.execute("UPDATE students " +
                    "SET student_name = ' " + name + "', birth_date = '" + date + "' " +
                    "WHERE student_id =" + Integer.parseInt(idString));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
