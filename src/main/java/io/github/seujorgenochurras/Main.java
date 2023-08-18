package io.github.seujorgenochurras;

public class Main {
    public static void main(String[] args) {
        LocalServerSocket.initLocalHostServer(2969)
                .onConnection();
    }
}