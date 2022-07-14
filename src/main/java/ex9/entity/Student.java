package ex9.entity;

import java.sql.Date;

public class Student {
    private final int id;
    private final String name;
    private final Date birthDate;

    public Student(int id, String name, Date birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return "{id = " + id + ", name = " + name + '\'' +
                ", date of birth = " + birthDate + "}";
    }
}
