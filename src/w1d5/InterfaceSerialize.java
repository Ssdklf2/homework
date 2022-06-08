package w1d5;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public interface InterfaceSerialize {

    /**
     * Сериалиазация объекта в файл
     *
     * @param object   объект, который сериализуется
     * @param filename путь к файлу
     */
    void serialize(Object object, String filename);

    /**
     * Десериализация объекта
     *
     * @param filename путь к файлу
     * @return объект, который десериализован из файла
     */
    Object deserialize(String filename);


    /**
     * Ищет все поля объекта кроме объявленных как transient и static
     *
     * @param object объект в котором идет поиск
     * @return карта в которой ключи - названия полей, значения - значения этих полей
     */
    default Map<String, Object> searchFields(Object object) {
        Map<String, Object> mapField = new HashMap<>();
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
                mapField.put(field.getName(), field.get(object));
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return mapField;
    }

    /**
     * Подставляет значения в поля экземпляра класса
     *
     * @param object    экземпляр класса
     * @param objectMap содержатся ключи-названия полей и их значения
     */
    default void setFields(Object object, Map<String, Object> objectMap) {
        Class<?> classObj = object.getClass();
        try {
            Field[] fields = classObj.getDeclaredFields();
            for (Field field : fields) {
                if (Modifier.isTransient(field.getModifiers()) || Modifier.isStatic(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                field.set(object, objectMap.get(field.getName()));
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);

        }
    }

}
