package org.example;

import java.util.HashMap;
import java.util.Map;

public class GameManager {
    private Map<String, Game> games = new HashMap<>();

    public synchronized Game createGame(String gameId) {
        Game game = new Game(gameId);
        games.put(gameId, game);
        return game;
    }

    public synchronized Game getGame(String gameId) {
        return games.get(gameId);
    }

    public synchronized boolean joinGame(String gameId, Player player) {
        Game game = games.get(gameId);
        if (game != null) {
            return game.addPlayer(player);
        }
        return false;
    }
}