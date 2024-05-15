package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket socket = null;
    private String request;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            System.out.println("Client connected");
            // Get the request from the input stream: client → server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Send the response to the output stream: server → client
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            String receivedRequest;
            while ((receivedRequest = in.readLine()) != null) {
                System.out.println("Received request from client: " + receivedRequest);
                request = receivedRequest;

                if (request.equals("stop")) {
                    //inchidem thread-ul
                    System.out.println("Stopping server...");
                    out.println("Server stopped");
                    socket.close(); // închideți socket-ul pentru a opri bucla
                    System.exit(0);
                } else {
                    String response = "Server received the request: \"" + request + "\"";
                    out.println(response);
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        }
    }
}
