package ex5;

/**
 * Необходимо разработать класс, реализующий следующий интерфейс:
 * <p>
 * void serialize (Object object, String file);
 * <p>
 * Object deSerialize(String file);
 * <p>
 * Методы выполняют сериализацию объекта Object в файл file и десериализацию объекта из этого файла.
 * <p>
 * Обязательна сериализация и десериализация "плоских" объектов (все поля объекта - примитивы, или String).
 * <p>
 * Задание "Под звездочкой": работать c любыми типами полей (полями могут быть ссылочные типы).
 * <p>
 * Использовать готовые реализации (Jaxb, jackson и т.д.) нельзя!
 * <p>
 * Требования к заданию:
 * <p>
 * Работоспособность кода
 * Архитектурная корректность (ООП)
 * Документация (JavaDoc)
 * Следование Java Code Convention
 * Корректная работа с ресурсами
 * Предусмотреть возникновение исключительных ситуаций
 */
public class Start {
    public static final String filename = "src\\w1d5\\object.xml";

    public static void main(String[] args) {
        Animal lion = new Animal("Panther", "Alex", 321, new Aviary(12, 30));
        Function f = new Function();
        f.serialize(lion, filename);
        Animal animal = (Animal) f.deserialize(filename);
        System.out.println(animal.getInfoAboutAnimal());
    }
}