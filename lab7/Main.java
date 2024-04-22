package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int playerCount = 3;

    public static void main(String[] args) {
        final int n = 5;
        Bag bag = new Bag(n);
        List<Player> players = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        Object turnLock = new Object();

        // init players
        for (int i = 0; i < playerCount; i++) {
            Player player = new Player("Player " + i, bag, turnLock, i);
            players.add(player);
            Thread thread = new Thread(player);
            threads.add(thread);
        }

        //also start the timekeeper right before starting the threads for each player
        Thread timekeeperThread = new Thread(new Timekeeper(threads, 60000));
        timekeeperThread.setDaemon(true);
        timekeeperThread.start();

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join(); //waits for other threads to finish
            } catch (InterruptedException e) {
                System.out.println("Main thread was interrupted.");
            }
        }

        for (Player player : players) {
            System.out.println(player.getName() + " longest sequence: " + player.getLongestSequence());
        }

        int maxSequence = 0;
        List<String> winners = new ArrayList<>();
        for (Player player : players) {
            if (player.getLongestSequence() > maxSequence) {
                maxSequence = player.getLongestSequence();
                winners.clear();
                winners.add(player.getName());
            } else if (player.getLongestSequence() == maxSequence) {
                winners.add(player.getName());
            }
        }

        if (winners.size() > 1) {
            System.out.println("It's a tie between: ");
            for (String winner : winners) {
                System.out.println(winner);
            }
        } else if (winners.size() == 1) {
            System.out.println("The winner is: " + winners.get(0));
        } else {
            System.out.println("No winner found");
        }
    }
}
