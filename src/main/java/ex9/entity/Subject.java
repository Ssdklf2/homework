package ex9.entity;

public class Subject {
    private final int id;
    private final String description;

    public Subject(int id, String description) {
        this.id = id;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "{(id: " + id + ") " + description + '}';
    }
}
