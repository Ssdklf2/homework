package ex7;

import java.io.IOException;
import java.net.Socket;

public class Client {
    public static final String HOST = "localhost";

    public static void main(String[] args) {
        ClientConnection clientConnection = new ClientConnection();
        try {
            Socket clientSocket = new Socket(HOST, Server.PORT);
            clientConnection.connectToChat(clientSocket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
