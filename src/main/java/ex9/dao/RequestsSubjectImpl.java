package ex9.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RequestsSubjectImpl extends DBConnection implements Requests {

    @Override
    public void add() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Add subject:");
        System.out.print("Description of the subject: ");
        String desc = sc.nextLine();
        try (PreparedStatement st = getPreparedStatement(INSERT_SUBJECT_DESC)) {
            st.setString(1, desc);
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Delete subject:");
        System.out.print("Enter subject id: ");
        int id = sc.nextInt();
        try (PreparedStatement st = getPreparedStatement(DELETE_FROM_SUBJECTS)) {
            st.setInt(1, id);
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void readTable() {
        System.out.println("____\nSubjects list: \n____");
        try (PreparedStatement st = getPreparedStatement(SELECT_SUBJECT_DESC)) {
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                String desc = resultSet.getString("subject_desc");
                System.out.printf("Description: %s ", desc);
                System.out.println("\n__________");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void change() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Change subject:");
        try (PreparedStatement st = getPreparedStatement(UPDATE_SUBJECTS)) {
            System.out.print("ID: ");
            int id = Integer.parseInt(sc.nextLine());
            System.out.print("Description: ");
            String desc = sc.nextLine();
            st.setString(1, desc);
            st.setInt(2, id);
            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
