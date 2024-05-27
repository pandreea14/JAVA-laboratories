package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private ServerSocket serverSocket;
    private boolean running = true;
    private GameManager gameManager = new GameManager();

    public static void main(String[] args) {
        int port = 8100;
        GameServer server = new GameServer();
        server.start(port);
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("Server started on port " + port);

            while (running) {
                Socket clientSocket = serverSocket.accept();
                new ClientThread(clientSocket, gameManager).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        running = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


//public class GameServer {
//    public static final int PORT = 8100;
//
//    public GameServer() {
//        ServerSocket serverSocket = null;
//        try {
//            serverSocket = new ServerSocket(PORT);
//            System.out.println("Server started on port " + PORT);
//            while (true) {
//                System.out.println("Waiting for a client ...");
//                Socket socket = serverSocket.accept();
//                ClientThread clientThread = new ClientThread(socket);
//                clientThread.start();
//            }
//        } catch (IOException e) {
//            System.err.println("Error: " + e);
//        } finally {
//            try {
//                if (serverSocket != null) {
//                    serverSocket.close();
//                }
//            } catch (IOException e) {
//                System.err.println("Error: " + e);
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        new GameServer();
//    }
//}