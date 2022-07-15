package ex9;

import ex9.dao.RequestsStudentImpl;
import ex9.dao.RequestsStudentSubjectImpl;
import ex9.dao.RequestsSubjectImpl;
import ex9.entity.Student;
import ex9.entity.Subject;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {
        RequestsStudentImpl rqStud = new RequestsStudentImpl();
        rqStud.readTable();
        rqStud.delete();
        rqStud.add();
        rqStud.change();

        RequestsSubjectImpl rqSubj = new RequestsSubjectImpl();
        rqSubj.readTable();
        rqSubj.add();
        rqSubj.delete();
        rqSubj.change();

        RequestsStudentSubjectImpl rqStudSubj = new RequestsStudentSubjectImpl();
        rqStudSubj.readTable();
        rqStudSubj.add();
        rqStudSubj.delete();
        rqStudSubj.change();
        Collection<Student> students = rqStudSubj.getAllStudents();
        System.out.println(students);
        Collection<Subject> subjects = rqStudSubj.getAllSubjects();
        System.out.println(subjects);
    }
}
