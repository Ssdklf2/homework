package w1d2;

/**Написать класс MathBox , реализующий следующий функционал:

1. Конструктор на вход получает массив Integer. Элементы не могут повторяться. Элементы массива внутри конструктора раскладываются в подходящую коллекцию (выбрать самостоятельно), являющуюся полем MathBox. Элементы должны отсортироваться

2. Существует метод summator, возвращающий сумму всех элементов коллекции

3. Существует метод splitter, выполняющий поочередное деление всех хранящихся в объекте элементов на делитель, являющийся аргументом метода.
Метод возвращает коллекцию с результатами деления. Коллекция внутри mathbox при этом не меняется

4. Необходимо правильно переопределить методы toString , hashCode, equals, чтобы можно было использовать MathBox для вывода данных на экран и хранение объектов этого класса в коллекциях (например, hashMap ). Выполнение контракта обязательно!

5. Есть метод, который получает на вход Integer и если такое значение есть в коллекции, удаляет его.
_______________
Часть 2:

Создать класс ObjectBox, который будет хранить коллекцию Object.

У класса должен быть метод addObject, добавляющий объект в коллекцию.
У класса должен быть метод deleteObject, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
Должен быть метод dump, выводящий содержимое коллекции в строку.

Класс MathBox необходимо доработать так, чтобы он работал не только с Integer, но и с любым Number
_______________

Часть 3, творческая:

Получившиеся классы MathBox и ObjectBox не имеют связи между собой.
Это неправильно. Логичнее было бы сделать MathBox наследником ObjectBox.
Необходимо сделать такую связь, правильно распределить поля и методы.
Функциональность в целом должна сохраниться. Руководствуемся здравым смыслом и своей фантазией.

При попытке положить Object в MathBox должно получаться исключение.

Требования к заданию:

    Работоспособность кода
    Архитектурная корректность (ООП)
    Документация (JavaDoc)
    Следование Java Code Convention
    Поддержка разных типов данных (Integer, Float)
    Предусмотреть возникновение исключительных ситуаций
*/

import java.util.*;

public class Start{
    public static void main(String[] args) {

    //вызов методов ObjectBox
        ObjectBox<Integer> integerObjectBox= new ObjectBox<>();
        ObjectBox<Double> doubleObjectBox = new ObjectBox<>();

        //создании коллекции типа String ObjectBox
        ObjectBox<String> stringObjectBox = new ObjectBox<>();
        stringObjectBox.addObject("a", stringObjectBox.getObject());
        stringObjectBox.addObject("b", stringObjectBox.getObject());
        stringObjectBox.addObject("c", stringObjectBox.getObject());
        stringObjectBox.dump(stringObjectBox.getObject());
        System.out.println("stringObjectBox");


        //добавление элементов
        integerObjectBox.addObject(2, integerObjectBox.getObject());
        integerObjectBox.addObject(6, integerObjectBox.getObject());
        integerObjectBox.addObject(7, integerObjectBox.getObject());
        integerObjectBox.addObject(9, integerObjectBox.getObject());

        //удаление элементов
        integerObjectBox.deleteObject(9, integerObjectBox.getObject());

        //вывод коллекции
        integerObjectBox.dump(integerObjectBox.getObject());
        System.out.println("integerObjectBox");

    //вызов методов MathBox
        MathBox<Integer> integerMathBox = new MathBox<>();
        MathBox<Double> doubleMathBox = new MathBox<>();
        MathBox<Float> floatMathBox = new MathBox<>();

        //методы из ObjectBox
        integerObjectBox.deleteObject( 534, integerMathBox.getList());

        doubleObjectBox.dump(doubleMathBox.getDoubleArrayList());
        System.out.println("doubleArrayList");

        //сортировка коллекции
        Collections.sort(integerMathBox.getList());
        System.out.println("Сортированный список MathBox: " + integerMathBox.getList());

        //вызов метода который покажет сумму коллекции:
        System.out.print("Коллекция типа Integer |");
        integerMathBox.summator(integerMathBox.getList());
        System.out.print("Коллекция типа Double |");
        doubleMathBox.summator(doubleMathBox.getDoubleArrayList());
        System.out.print("Коллекция типа Float |");
        floatMathBox.summator(floatMathBox.getFloatArrayList());

        //вызов метода делящего элементы коллекции на заданный аргумент
        doubleMathBox.splitter(doubleMathBox.getDoubleArrayList(), 10);
        integerMathBox.splitter(integerMathBox.getList(), 10);
        floatMathBox.splitter(floatMathBox.getFloatArrayList(), 10);

    }
}
