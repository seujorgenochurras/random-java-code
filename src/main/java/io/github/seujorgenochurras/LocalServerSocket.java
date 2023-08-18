package io.github.seujorgenochurras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class LocalServerSocket {
    private final ServerSocket serverSocket;

    private LocalServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static LocalServerSocket initLocalHostServer(int port) {
        try {
            return new LocalServerSocket(new ServerSocket(port));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void onConnection() {
        new Thread(() -> {
            while (!serverSocket.isClosed()) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter output = new PrintWriter(clientSocket.getOutputStream())) {
                    String message = input.readLine();
                    System.out.println("receive message " + message);
                    output.println("receive message: " + message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}

