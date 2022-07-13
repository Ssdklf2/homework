package ex9;

import ex9.dao.RequestsStudent;
import ex9.dao.RequestsSubject;

public class Main {
    public static void main(String[] args) {
        RequestsStudent rqStud = new RequestsStudent();
        RequestsSubject rqSubj = new RequestsSubject();
        rqSubj.readTable();
        rqStud.readTable();
        rqSubj.add();
        rqStud.add();
    }
}
