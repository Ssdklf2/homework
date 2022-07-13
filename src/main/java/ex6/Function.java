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

public class Function {
    public static final String CLASS_JAVA = "src\\ex6\\SomeClass.java";

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
