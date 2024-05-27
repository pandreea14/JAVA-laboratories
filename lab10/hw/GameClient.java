package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    public static void main(String[] args) {
        String hostName = "localhost"; // or the server IP
        int portNumber = 8100; // same port as the server

        try (
                Socket socket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            String userInput;
            System.out.println("Enter commands (type 'exit' to quit):");
            while ((userInput = stdIn.readLine()) != null) {
                if (userInput.equalsIgnoreCase("exit")) {
                    break;
                }
                out.println(userInput);
                String serverResponse = in.readLine();
                System.out.println("Server response: " + serverResponse);
                if (userInput.startsWith("move ")) {
                    // Display the board after making a move
                    String[] tokens = userInput.split(" ");
                    String gameId = tokens[1];
                    String playerName = tokens[2];
                    out.println("board " + gameId + " " + playerName);
                    serverResponse = in.readLine();
                    System.out.println("Current board state:\n" + serverResponse);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//public class GameClient {
//    public static void main(String[] args) {
//        String serverAddress = "127.0.0.1"; // Adresa IP a serverului
//        int PORT = 8100; // Portul serverului
//
//        try (Socket socket = new Socket(serverAddress, PORT);
//             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//
//            String command;
//            do {
//                System.out.print("Enter command (type 'exit' to quit): ");
//                command = reader.readLine();
//                out.println(command);
//
//                // Dacă comanda este "exit", închide conexiunea și iese din buclă
//                if (command.equals("exit")) {
//                    System.out.println("Closing connection...");
//                    break;
//                }
//
//                // Așteaptă răspunsul de la server
//                String response = in.readLine();
//                System.out.println(response);
//
//                //stop -> facem exit la client / ne da eroare
//                if (command.equals("stop")) {
//                    System.out.println("Closing connection...");
//                    break;
//                }
//
//            } while (true);
//
//        } catch (IOException e) {
//            System.err.println("Error: " + e);
//        }
//    }
//}