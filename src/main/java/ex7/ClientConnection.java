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
    public void connectToChat(Socket clientSocket) throws IOException {
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(clientSocket.getOutputStream(), StandardCharsets.UTF_8));
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream(), StandardCharsets.UTF_8));

        greeting(writer, reader);
        Thread sender = new Thread(new Sender(writer));
        Thread receiver = new Thread(new Receiver(clientSocket, reader, writer, sender));
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
        Sender.writeNewLine(writer, name);
        System.out.println("...CHAT...");
        reader.readLine();
    }
}
