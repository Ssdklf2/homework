package ex7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

public class Receiver implements Runnable {

    Socket clientSocket;
    BufferedReader reader;
    BufferedWriter writer;
    Thread sender;

    public Receiver(Socket clientSocket, BufferedReader reader, BufferedWriter writer, Thread sender) {
        this.clientSocket = clientSocket;
        this.reader = reader;
        this.writer = writer;
        this.sender = sender;
    }

    @Override
    public void run() {
        receive(clientSocket, reader, writer, sender);
    }


    /**
     * Получает системные сообщения и сообщения чата
     * Если поток, посылающий сообщения, завершает свою работу,
     * то этот метод выходит из бесконечного цикла получения сообщений
     *
     * @param clientSocket сокет клиента
     * @param writer       для отправки сообщений
     * @param reader       для получения сообщений
     * @param sender       поток посылающий сообщения
     */
    private void receive(Socket clientSocket, BufferedReader reader, BufferedWriter writer, Thread sender) {
        try {
            do {
                System.out.println(reader.readLine());
            } while (sender.isAlive());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Connection.closeAll(clientSocket, reader, writer);
    }
}
