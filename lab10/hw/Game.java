package org.example;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private String gameId;
    private Player player1;
    private Player player2;
    private Player currentPlayer;
    private Map<String, Player> players = new HashMap<>();
    private boolean gameStarted;

    public Game(String gameId) {
        this.gameId = gameId;
    }

    public String getGameId() {
        return gameId;
    }

    public synchronized boolean addPlayer(Player player) {
        if (players.size() < 2) {
            players.put(player.getName(), player);
            if (players.size() == 1) {
                player1 = player;
            } else {
                player2 = player;
                gameStarted = true;
                currentPlayer = player1; // player1 starts
            }
            return true;
        }
        return false;
    }

    public synchronized String submitMove(String playerName, int x, int y) {
        if (currentPlayer.getName().equals(playerName)) {
            Player opponent = currentPlayer == player1 ? player2 : player1;
            String result = opponent.getBoard().attack(x, y);
            currentPlayer = opponent;
            return result;
        }
        return "Not your turn!";
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public String getCurrentPlayer() {
        return currentPlayer.getName();
    }

    public synchronized String getBoardState(String playerName) {
        Player player = players.get(playerName);
        if (player != null) {
            return player.getBoard().getBoardState();
        }
        return "Player not found.";
    }
}