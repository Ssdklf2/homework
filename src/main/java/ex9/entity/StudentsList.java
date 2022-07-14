package ex9.entity;

import java.util.Collection;

public class StudentsList {
    private Student student;
    private Collection<Student> studentsList;

    public StudentsList(Student student, Collection<Student> studentsList) {
        this.student = student;
        this.studentsList = studentsList;
    }
}
