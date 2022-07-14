package ex9.entity;

import java.util.Collection;

public class SubjectsList {
    private Subject subject;
    private Collection<Subject> subjectsList;

    public SubjectsList(Subject subject, Collection<Subject> subjectsList) {
        this.subject = subject;
        this.subjectsList = subjectsList;
    }
}
