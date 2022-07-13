package ex7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;

public interface Connection {
    void connectToChat(Socket clientSocket) throws IOException;

    /**
     * Закрывает все потоки
     */
    static void closeAll(Socket clientSocket, BufferedReader reader, BufferedWriter writer) {
        try {
            writer.close();
            reader.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
