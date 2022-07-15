package ex9.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RequestsStudent implements Requests, RequestConstants {

    @Override
    public void add() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add student data: ");
        System.out.print("Enter student name: ");
        String name = sc.nextLine();
        System.out.print("Date of birth: ");
        Date date = Date.valueOf(sc.nextLine());
        try (PreparedStatement st = getPreparedStatement(INSERT_STUDENT_NAME_DATE)) {
            st.setString(1, name);
            st.setDate(2, date);
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Delete student data:");
        System.out.print("Enter student id: ");
        int id = sc.nextInt();
        try (PreparedStatement st = getPreparedStatement(DELETE_STUDENT_WHERE_ID)) {
            st.setInt(1, id);
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void readTable() {
        System.out.println("____\nStudents list: \n____");
        try (PreparedStatement st = getPreparedStatement(SELECT_FROM_STUDENTS)) {
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("student_name");
                Date date = resultSet.getDate("birth_date");
                System.out.printf(
                        "Name: %s \n Date of Birth: %tF %n ", name, date);
                System.out.println("__________");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void change() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Change student data:");
        try (PreparedStatement st = getPreparedStatement(UPDATE_STUDENTS)) {
            System.out.print("ID: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Date of birth: ");
            Date date = Date.valueOf(sc.nextLine());
            st.setString(1, name);
            st.setDate(2, date);
            st.setInt(3, id);
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
