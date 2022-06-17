package w1d5;

import java.io.Serializable;

public class Aviary implements Serializable {
    private static final long serialVersionUID = 8251692306685143357L;
    int width;
    int height;

    public Aviary() {
    }

    @Override
    public String toString() {
        return width + "," + height;
    }

    public Aviary(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
