package ex2;

import java.util.ArrayList;
import java.util.Objects;

public class ObjectBox<T> {
    private final ArrayList<T> object = new ArrayList<>();
    public ArrayList<T> getObject() {
        return object;
    }

    //метод добавления элементов
    public void addObject(T x, ArrayList<T> someList) {
        someList.add(x);
        System.out.println("добавлен элемент: " + x);
    }

    //метод удаления элементов
    public void deleteObject(T y, ArrayList<T> someList){
        if (someList.contains(y)){
            someList.remove(y);
            System.out.println("удален элемент: " + y);
        }else {
            System.out.println("попытка удалить несуществующий элемент: " + y);
        }

    }

    //метод вывода коллекции
    public void dump(ArrayList<T> someList){
        System.out.print("Список элементов в ObjectBox: ");
        for(Object iC : someList){
             System.out.print(iC + " ");
        }
        System.out.print(" | Коллекции: ");
    }

    @Override
    public String toString() {
        return "ObjectBox {" + " object = " + object + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectBox<?> objectBox = (ObjectBox<?>) o;
        return object.equals(objectBox.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(object);
    }
}
