package ex9;

import ex9.dao.RequestsStudent;
import ex9.dao.RequestsStudentSubject;
import ex9.dao.RequestsSubject;
import ex9.entity.Student;
import ex9.entity.Subject;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        RequestsStudent rqStud = new RequestsStudent();
        rqStud.readTable();

        rqStud.delete();
        rqStud.add();
        rqStud.change();

        rqStud.readTable();

//        RequestsSubject rqSubj = new RequestsSubject();
//        rqSubj.readTable();
//
//        rqSubj.add();
//        rqSubj.delete();
//        rqSubj.change();
//
//        rqSubj.readTable();
//
//        RequestsStudentSubject rqStudSubj = new RequestsStudentSubject();
//        rqStudSubj.readTable();

//        rqStudSubj.add();
//        rqStudSubj.delete();
//        rqStudSubj.change();
//
//        Collection<Student> students = rqStudSubj.getAllStudents();
//        System.out.println(students);

//        Collection<Subject> subjects = rqStudSubj.getAllSubjects();
//        System.out.println(subjects);

//        rqStudSubj.readTable();
    }
}
