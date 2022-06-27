package ex7;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ClientConnection implements Connection {

    /**
     * Подключает клиента к чату, запускает два потока, которые обеспечивают отправку и получение сообщений
     *
     * @param clientSocket сокет клиента
     */
    @Override
    public void connectToChat(Socket clientSocket) {
        BufferedWriter writer;
        BufferedReader reader;
        try {
            writer = new BufferedWriter(
                    new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8));
            reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));
            greeting(writer, reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Thread sender = new Thread(() -> send(writer));
        Thread receiver = new Thread(() -> receive(clientSocket, reader, writer, sender));
        receiver.start();
        sender.start();
        try {
            sender.join();
            receiver.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Записывает имя пользователя, подключает к чату
     *
     * @param writer для отправки сообщений
     * @param reader для получения сообщений
     */
    private void greeting(BufferedWriter writer, BufferedReader reader) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Name: ");
        String name = sc.nextLine();
        writeNewLine(writer, name);
        System.out.println("...CHAT...");
        reader.readLine();
    }

    /**
     * Посылает сообщения, если пользователь посылает quit, то поток завершает свою работу
     *
     * @param writer для отправки сообщений
     */
    private void send(BufferedWriter writer) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNext()) {
                String msg = sc.nextLine();
                if (msg.equalsIgnoreCase("quit")) {
                    writeNewLine(writer, "quit");
                    break;
                }
                writeNewLine(writer, msg.trim());
            }
        }
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
        closeAll(clientSocket, reader, writer);
    }


    /**
     * Отправляет строку
     *
     * @param writer для отправки сообщений
     * @param s      строка, которую нужно отправить
     */
    private void writeNewLine(BufferedWriter writer, String s) {
        try {
            writer.write(s);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
