package ex7;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Создать многопользовательский чат, в котором участвует произвольное количество клиентов.
 * Каждый клиент после запуска отправляет свое имя серверу. После чего начинает отправлять ему сообщения.
 * Каждое сообщение сервер подписываем именем клиента и рассылает всем клиентам.
 * <p>
 * Доп. задание: после ввода в клиенте "quit" клиент завершает свою работу.
 * <p>
 * Требования к заданию:
 * <p>
 * Работоспособность кода
 * Архитектурная корректность (ООП)
 * Документация (JavaDoc)
 * Следование Java Code Convention
 * Многопоточность
 * Корректная работа с ресурсами
 * Предусмотреть возникновение исключительных ситуаций
 */
public class Server {
    public static final int PORT = 8000;
    static List<ChatMember> chatMembers = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ChatMember chatMember = new ChatMember(clientSocket);
                new Thread(chatMember).start();
            }
        }
    }
}








