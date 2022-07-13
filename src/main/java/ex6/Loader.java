package ex6;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Loader extends ClassLoader {
    private final String path;

    public Loader(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> aClass;
        byte[] bytes = getFileBytes(name);
        if (bytes != null) {
            aClass = defineClass(name, bytes, 0, bytes.length);
        } else {
            throw new ClassNotFoundException();
        }
        return aClass;
    }

    /**
     * Получить байт-код файла
     *
     * @param name имя класса
     * @return массив из байт-кода файла
     */
    private byte[] getFileBytes(String name) {
        String fileName = getFileName(name);
        File file = new File(path, fileName);
        try (InputStream inputStream = Files.newInputStream(file.toPath())) {
            ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[2048];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                arrayOutputStream.write(buffer, 0, length);
            }
            return arrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Получить имя файла
     *
     * @param name имя класса
     * @return имя файла с расширением .class
     */
    private String getFileName(String name) {
        int index = name.lastIndexOf('.');
        if (index == -1) {
            return name + ".class";
        } else {
            return name.substring(index + 1) + ".class";
        }
    }
}
