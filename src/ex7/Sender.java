package ex7;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class Sender implements Runnable {

    BufferedWriter writer;

    public Sender(BufferedWriter writer) {
        this.writer = writer;
    }

    @Override
    public void run() {
        send(writer);
    }

    /**
     * Посылает сообщения,
     * и если пользователь посылает quit, то поток завершает свою работу
     *
     * @param writer для отправки сообщений
     */
    private void send(BufferedWriter writer) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (sc.hasNext()) {
                String msg = sc.nextLine();
                if (msg.trim().equalsIgnoreCase("quit")) {
                    writeNewLine(writer, "quit".trim());
                    break;
                }
                writeNewLine(writer, msg.trim());
            }
        }
    }

    /**
     * Отправляет строку
     *
     * @param writer для отправки сообщений
     * @param s      строка, которую нужно отправить
     */
    static void writeNewLine(BufferedWriter writer, String s) {
        try {
            writer.write(s);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
