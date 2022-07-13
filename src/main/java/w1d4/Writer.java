package w1d4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Writer implements Runnable {

    public static final String SENTENCES_TXT = "src\\w1d4\\sentences.txt";
    private final ConcurrentLinkedQueue<String> stringConcurrentLinkedQueue;

    public Writer(ConcurrentLinkedQueue<String> stringConcurrentLinkedQueue) {
        this.stringConcurrentLinkedQueue = stringConcurrentLinkedQueue;
    }

    /**
     * Записывает предложения в новый файл
     *
     * @param stringConcurrentLinkedQueue - все предложения со словами из массива, которые были
     *                                    найдены в файле
     */
    public void write(ConcurrentLinkedQueue<String> stringConcurrentLinkedQueue) {
        Iterator<String> iterator = stringConcurrentLinkedQueue.iterator();
        try (FileWriter fileWriter = new FileWriter(SENTENCES_TXT, true)) {
            while (iterator.hasNext()) {
                fileWriter.write(iterator.next() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        write(stringConcurrentLinkedQueue);
    }
}