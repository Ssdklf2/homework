package w1d5;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

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
        System.out.println(f.deserialize(filename));
    }

    /**
     * Сериалиазация объекта в файл
     *
     * @param object   - объект, который сериализуется
     * @param filename - путь к файлу
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
     * Ищет все поля объекта кроме объявленных как transient и static
     *
     * @param object объект в котором идет поиск
     * @return строку, содержащую тип, название и значение всех полей
     */
    private byte[] searchFields(Object object) {
        StringBuilder stringBuilder = new StringBuilder();
        Class<?> classObj = object.getClass();
        Field[] fields = classObj.getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isTransient(field.getModifiers()) || Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            if (!field.isAccessible()) {
                field.setAccessible(true);
            }
            try {
                stringBuilder.append(field.getType().getName())
                        .append(": ")
                        .append(field.getName())
                        .append("| Value: ")
                        .append(field.get(object))
                        .append("\n");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        byte[] bytes = String.valueOf(stringBuilder).getBytes(Charset.defaultCharset());
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] +=1345;
        }
        return bytes;
    }

    /**
     * Десериализация объекта
     *
     * @param filename - путь к файлу
     * @return объект, который десериализован из файла
     */
    @Override
    public String deserialize(String filename) {
        String s;
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(filename)))) {
            byte[] b = (byte[]) ois.readObject();
            for (int i = 0; i < b.length; i++) {
                b[i] -=1345;
            }
            s = new String(b);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return s;
    }
}
