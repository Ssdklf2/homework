package ex6;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.Scanner;

/**
 * Есть интерфейс
 * <p>
 * public interface Worker {
 * void doWork();
 * }
 * <p>
 * Необходимо написать программу, выполняющую следующее:
 * <p>
 * 1. Программа с консоли построчно считывает код метода doWork. Код не должен требовать импорта дополнительных классов.
 * <p>
 * 2. После ввода пустой строки считывание прекращается и считанные строки добавляются в тело метода public void doWork() в файле SomeClass.java.
 * <p>
 * 3. Файл SomeClass.java компилируется программой (в рантайме) в файл SomeClass.class.
 * <p>
 * 4. Полученный файл подгружается в программу с помощью кастомного загрузчика
 * <p>
 * 5. Метод, введенный с консоли, исполняется в рантайме (вызывается у экземпляра объекта подгруженного класса)
 */
public class Start {
    public static final String CLASS_JAVA = "src\\ex6\\SomeClass.java";

    public static void main(String[] args) {
        Start s = new Start();
        s.doWorker();
    }

    /**
     * Пишется код из консоли в тело метода doWork в файл SomeClass.java,
     * файл SomeClass.java компилируется, загружается загрузчиком Loader и запускается метод doWork
     */
    public void doWorker() {
        Scanner sc = new Scanner(System.in);
        String s;
        StringBuilder builder = new StringBuilder();
        do {
            s = sc.nextLine();
            builder.append(s);
        } while (!s.isEmpty());

        String code = "package ex6; \n public class SomeClass implements Worker{ " +
                "\n @Override \n public void doWork()  " +
                "{" + builder + "  } } ";
        File fileClass = new File(CLASS_JAVA);
        try (FileWriter writer = new FileWriter(fileClass)) {
            writer.write(code);
        } catch (IOException e) {
            throw new RuntimeException();
        }

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        compiler.run(null, System.out, System.err, "-d", "out", CLASS_JAVA);

        Properties p = System.getProperties();
        String path = p.getProperty("user.dir") + "\\out\\ex6";
        Loader loader = new Loader(path);
        try {
            Class<?> classObj = Class.forName("ex6.SomeClass", true, loader);
            Object obj = classObj.newInstance();
            Method doWork = classObj.getDeclaredMethod("doWork");
            doWork.invoke(obj);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
