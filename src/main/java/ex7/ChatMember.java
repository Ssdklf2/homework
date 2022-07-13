package ex7;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatMember implements Runnable, Connection {
    private final Socket socket;
    private final BufferedWriter writer;
    private final BufferedReader reader;

    public ChatMember(Socket socket) {
        this.socket = socket;
        try {
            this.reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            this.writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        try {
            connectToChat(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Подключает клиента к чату, добавляет к списку участников,
     * обеспечивает отправку и получение сообщений
     *
     * @param clientSocket сокет клиента
     */
    @Override
    public void connectToChat(Socket clientSocket) throws IOException {
        writeLine(">>> Name: ");
        String name = reader.readLine();
        Server.chatMembers.add(this);
        sendToAllMembers(">>> " + name + " connected to chat\r\n");
        sendToAllMembers(">>> The number of chat members: " + Server.chatMembers.size() + "\r\n");
        while (true) {
            String message = reader.readLine();
            if (itQuit(name, message)) {
                break;
            }
            if (!message.isEmpty()) {
                sendToAllMembers(name + ": " + message + "\r\n");
            }
        }
        Connection.closeAll(clientSocket, reader, writer);
    }

    /**
     * Проверяет, если пользователь отправляет quit в сообщении,
     * то его отключает от чата и удаляют из списка участников
     *
     * @param name    имя пользователя
     * @param message сообщение
     * @return true если сообщение равняется quit
     */
    private boolean itQuit(String name, String message) {
        if (message.trim().equalsIgnoreCase("quit")) {
            sendToAllMembers(">>> " + name + " disconnected from the chat\r\n");
            Server.chatMembers.remove(this);
            sendToAllMembers(">>> The number of chat members: " + Server.chatMembers.size() + "\r\n");
            return true;
        }
        return false;
    }

    /**
     * Посылает сообщение всем участникам чата
     *
     * @param s сообщение
     */
    private void sendToAllMembers(String s) {
        if (!s.isEmpty()) {
            System.out.println(s);
            for (ChatMember chatMember : Server.chatMembers) {
                chatMember.writeLine(s);
            }
        }
    }

    /**
     * Отправляет строку
     *
     * @param s строка
     */
    private void writeLine(String s) {
        try {
            writer.write(s);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
