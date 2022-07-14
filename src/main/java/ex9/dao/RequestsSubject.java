package ex9.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class RequestsSubject implements Requests {
    @Override
    public void add() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add subject:");
        System.out.print("Description of the subject: ");
        String desc = sc.nextLine();
        try (Statement st = getStatement()) {
            st.execute("INSERT INTO subjects (subject_desc) " +
                    "VALUES ( '" + desc + "')");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Delete subject:");
        System.out.print("Enter the subject`s id: ");
        int id = sc.nextInt();
        try (Statement st = getStatement()) {
            st.execute("DELETE FROM subjects WHERE subject_id = " + id);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void readTable() {
        System.out.println("____\nSubjects list: \n____");
        try (Statement st = getStatement()) {
            ResultSet resultSet = st.executeQuery("SELECT * FROM subjects");
            while (resultSet.next()) {
                int id = resultSet.getInt("subject_id");
                String desc = resultSet.getString("subject_desc");
                System.out.println("ID: " + id + "\n" +
                                "Description: " + desc +
                                "\n__________");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void change() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Change subject:");
        try (Statement st = getStatement()) {
            System.out.print("ID: ");
            String idString = sc.nextLine();
            System.out.print("Description: ");
            String desc = sc.nextLine();
            st.execute("UPDATE subjects " +
                            "SET subject_desc = ' " + desc + "'" +
                            "WHERE subject_id =" + Integer.parseInt(idString));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
