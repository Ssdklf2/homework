package w1d5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

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
public class Start implements InterfaceSerialize {
    public static final String filename = "src\\w1d5\\object.txt";

    public static void main(String[] args) {
        Animal lion = new Animal("Panther", "Alex", 321, new Aviary(12, 30));
        Start f = new Start();
        f.serialize(lion, filename);
        Animal newLion = new Animal();
        f.setFields(newLion, f.deserialize(filename));
        System.out.println(newLion.getInfoAboutAnimal());
        System.out.println(newLion.getInfoAboutAviary());
    }

    /**
     * Сериалиазация объекта в файл
     *
     * @param object   объект, который сериализуется
     * @param filename путь к файлу
     */
    @Override
    public void serialize(Object object, String filename) {
        if (object == null) {
            throw new NullPointerException();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(filename)))) {
            oos.writeObject(searchFields(object));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Десериализация объекта
     *
     * @param filename путь к файлу
     * @return карта в которой ключи - названия полей, значения - значения этих полей
     */
    @Override
    public Map<String, Object> deserialize(String filename) {
        Map<String, Object> objectMap;
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))) {
            objectMap = (Map<String, Object>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return objectMap;
    }
}
