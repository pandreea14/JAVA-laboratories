package org.example;

import java.util.*;

public class Player implements Runnable {
    public String getName() {
        return name;
    }
    private String name;
    private Bag bag;
    private Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
    private int longestSequence = 0;
    private final Object turnLock;
    private static int currentPlayerIndex = 0;
    private int playerIndex;

    public Player(String name, Bag bag, Object turnLock, int playerIndex) {
        this.name = name;
        this.bag = bag;
        this.turnLock = turnLock;
        this.playerIndex = playerIndex;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (turnLock) {
                while (currentPlayerIndex != playerIndex) {
                    try {
                        turnLock.wait();
                    } catch (InterruptedException e) {
                        System.out.println(name + " was interrupted but still exists.");
                        return;
                    }
                }

                if (Thread.interrupted()) {
                    System.out.println(name + " was interrupted while active but still exists.");
                    return;
                }

                Token token = bag.extractToken();
                if (token == null) {
                    notifyNext();
                    break;
                }

                System.out.println(name + " extracted: " + token);
                try {
                    Thread.sleep((long) (Math.random() * 1000));
                } catch (InterruptedException e) {
                    System.out.println(name + " was interrupted during eepy time but still exists.");
                    return;
                }
                processToken(token);

                notifyNext();
            }
        }
    }

    private void notifyNext() {
        synchronized (turnLock) {
            currentPlayerIndex = (currentPlayerIndex + 1) % Main.playerCount;
            turnLock.notifyAll();
        }
    }

    private void processToken(Token token) {
        int first = token.getFirst();
        int second = token.getSecond();
        adjacencyList.putIfAbsent(first, new HashSet<>());
        adjacencyList.get(first).add(second);
        findCycles(first);
    }

    private void findCycles(int start) {
        Set<Integer> visited = new HashSet<>();
        Stack<Integer> path = new Stack<>();
        path.push(start);
        dfs(start, start, visited, path);
    }

    private void dfs(int current, int start, Set<Integer> visited, Stack<Integer> path) {
        if (!adjacencyList.containsKey(current)) return;
        for (int neighbor : adjacencyList.get(current)) {
            if (neighbor == start && path.size() > 1) {
                if (path.size() > longestSequence) {
                    longestSequence = path.size();
                    System.out.println(name + " found a new longest sequence of length " + longestSequence);
                }
                continue;
            }
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                path.push(neighbor);
                dfs(neighbor, start, visited, path);
                path.pop();
                visited.remove(neighbor);
            }
        }
    }

    public int getLongestSequence() {
        return longestSequence;
    }
}

