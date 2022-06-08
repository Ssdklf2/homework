package w1d5;


import java.io.Serializable;

public class Animal implements Serializable {
    private static final long serialVersionUID = 1427939964795432847L;
    private String genus;
    private String name;
    private int id;
    private transient Aviary aviary;

    public Animal() {
    }

    public Animal(String genus, String name, int id, Aviary aviary) {
        this.genus = genus;
        this.name = name;
        this.id = id;
        this.aviary = aviary;
    }

    /**
     * Получить информацию о животном
     *
     * @return род, имя и id животного
     */
    public String getInfoAboutAnimal() {
        return "Animal: " + "genus='" + genus + '\'' + ", name='" + name + '\'' + ", id=" + id;
    }

    /**
     * Получить информацию о вольере животного
     *
     * @return размер и id вольера
     */
    public String getInfoAboutAviary() {
        return "Aviary: " + aviary;
    }

    public String getGenus() {
        return genus;
    }

    public void setGenus(String genus) {
        this.genus = genus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Aviary getAviary() {
        return aviary;
    }

    public void setAviary(Aviary aviary) {
        this.aviary = aviary;
    }

}
