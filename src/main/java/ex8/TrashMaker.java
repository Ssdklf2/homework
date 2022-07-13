package ex8;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Необходимо создать программу, которая продемонстрирует утечку памяти в Java.
 * При этом объекты должны не только создаваться, но и периодически частично удаляться,
 * чтобы GC имел возможность очищать часть памяти.
 * Через некоторое время программа должна завершиться с ошибкой OutOfMemoryError c пометкой Java Heap Space.
 * <p>
 * Задание со звездочкой: сделать чтобы ошибка OutOfMemoryError была в Permanent Generation (или Metaspace)
 */
public class TrashMaker {

    public static void main(String[] args) throws IOException {
        new TrashMaker().doGarbage();
    }

    public void doGarbage() throws IOException {
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<ArrayList<String>> listOfStrings = new ArrayList<>();
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
        for (int i = 0; i < 1_000_000; i++) {
            String trash = String.valueOf(Math.random() * 10000);
            strings.add(trash.intern());
        }
        for (int i = 0; i < 1_000; i++) {
            listOfStrings.add(strings);
        }
        writer.write(String.valueOf(listOfStrings));
    }
}
