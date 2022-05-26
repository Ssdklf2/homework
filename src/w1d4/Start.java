package w1d4;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Start {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executorReader = Executors.newFixedThreadPool(10);
        Future<ConcurrentLinkedQueue<String>> submit = executorReader.submit(new Reader());
        executorReader.shutdown();
        Writer writer = new Writer(submit.get());
        Thread threadWriter = new Thread(writer);
        ExecutorService executorWriter = Executors.newFixedThreadPool(10);
        executorWriter.execute(threadWriter);
        executorWriter.shutdown();
    }
}
