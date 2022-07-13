package ex2;

import java.util.*;

public class MathBox<T extends Number> extends ObjectBox<T>{
    private final Integer[] array = {89,534,12,45,6};
    private final ArrayList<Integer> list = new ArrayList<>(Arrays.asList(array));
    private final ArrayList<Double> doubleArrayList = new ArrayList<> (Arrays.asList(1.45, 12.12, 0.12, 21.44, 7.12));
    private final ArrayList<Float> floatArrayList = new ArrayList<> (Arrays.asList(1.45f, 56.12f, 12.12f, 89.23f));
    public ArrayList<Float> getFloatArrayList() {
        return floatArrayList;
    }

    public ArrayList<Double> getDoubleArrayList() {
        return doubleArrayList;

    }

    public Integer[] getArray() {
        return array;
    }
    public ArrayList<Integer> getList() {
        return list;
    }


    //метод, вовзращающий копию коллекции, элементы которой были поделены на заданный аргумент
    public void splitter(ArrayList<T> list, double del){
        ArrayList<Double> doubleCopyList= new ArrayList<>();
        //создание копии коллекции с типом Double(для более точного возвращения поделенных элементов)
        for (T number : list) {
            Double newNumber = number.doubleValue();
            doubleCopyList.add(newNumber);
        }
        //деление элементов на аргумент del
        doubleCopyList.replaceAll(aDouble -> aDouble / del);
        System.out.println(doubleCopyList.toString());
    }


    //метод, суммирующий элементы коллекции
    public void summator(ArrayList<T> list){
        Number x = 0;
        for (Number i : list) {
            x =  x.doubleValue() + i.doubleValue();
        }
        System.out.println("Сумма: " + x);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MathBox<?> mathBox = (MathBox<?>) o;
        return Arrays.equals(array, mathBox.array) && list.equals(mathBox.list) && doubleArrayList.equals(mathBox.doubleArrayList) && floatArrayList.equals(mathBox.floatArrayList);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), list, doubleArrayList, floatArrayList);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    @Override
    public String toString() {
        return "MathBox{" +
                "array=" + Arrays.toString(array) +
                ", list=" + list +
                ", doubleArrayList=" + doubleArrayList +
                ", floatArrayList=" + floatArrayList +
                '}';
    }
}
