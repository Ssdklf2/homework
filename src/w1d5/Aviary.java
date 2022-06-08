package w1d5;

import java.io.Serializable;

public class Aviary implements Serializable {
    private static final long serialVersionUID = 8251692306685143357L;
    int id;
    int size;

    public Aviary(int id, int size) {
        this.id = id;
        this.size = size;
    }

    @Override
    public String toString() {
        return "Aviary: " + "id=" + id + ", size=" + size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
