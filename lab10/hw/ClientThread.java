package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket clientSocket;
    private GameManager gameManager;

    public ClientThread(Socket socket, GameManager gameManager) {
        this.clientSocket = socket;
        this.gameManager = gameManager;
    }

    public void run() {
        try (
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String request;
            while ((request = in.readLine()) != null) {
                String response = handleRequest(request);
                out.println(response);
                if (request.equalsIgnoreCase("stop")) {
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String handleRequest(String request) {
        String[] tokens = request.split(" ");
        String command = tokens[0];
        switch (command.toLowerCase()) {
            case "create":
                String gameId = tokens[1];
                gameManager.createGame(gameId);
                return "Game " + gameId + " created.";
            case "join":
                gameId = tokens[1];
                String playerName = tokens[2];
                Player player = new Player(playerName);
                boolean joined = gameManager.joinGame(gameId, player);
                return joined ? playerName + " joined game " + gameId : "Failed to join game " + gameId;
            case "move":
                gameId = tokens[1];
                playerName = tokens[2];
                int x = Integer.parseInt(tokens[3]);
                int y = Integer.parseInt(tokens[4]);
                Game game = gameManager.getGame(gameId);
                if (game != null && game.isGameStarted()) {
                    String result = game.submitMove(playerName, x, y);
                    return result + "\n" + game.getBoardState(playerName);
                }
                return "Invalid move.";
            case "board":
                gameId = tokens[1];
                playerName = tokens[2];
                game = gameManager.getGame(gameId);
                if (game != null) {
                    return game.getBoardState(playerName);
                }
                return "Game or player not found.";
            default:
                return "Unknown command.";
        }
    }
}

//public class ClientThread extends Thread {
//    private Socket socket = null;
//    private String request;
//
//    public ClientThread(Socket socket) {
//        this.socket = socket;
//    }
//
//    public void run() {
//        try {
//            System.out.println("Client connected");
//            // Get the request from the input stream: client → server
//            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            // Send the response to the output stream: server → client
//            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//
//            String receivedRequest;
//            while ((receivedRequest = in.readLine()) != null) {
//                System.out.println("Received request from client: " + receivedRequest);
//                request = receivedRequest;
//
//                if (request.equals("stop")) {
//                    //inchidem thread-ul
//                    System.out.println("Stopping server...");
//                    out.println("Server stopped");
//                    socket.close(); // închideți socket-ul pentru a opri bucla
//                    System.exit(0);
//                } else {
//                    String response = "Server received the request: \"" + request + "\"";
//                    out.println(response);
//                }
//            }
//        } catch (IOException e) {
//            System.err.println("Communication error... " + e);
//        }
//    }
//}
