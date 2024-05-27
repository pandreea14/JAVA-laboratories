package org.example;

public class Board {
    private static final int SIZE = 10;
    private char[][] grid;
    private int shipsCount;

    public Board() {
        grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = '~'; // Water
            }
        }
        // Add ships to the board (simplified)
        // Example: one ship of size 3 at position (0,0), (0,1), (0,2)
        grid[0][0] = grid[0][1] = grid[0][2] = 'S';
        grid[1][2] = grid[2][2] = 'S';
        shipsCount = 2; // One ship in this example
    }

    public synchronized String attack(int x, int y) {
        if (grid[x][y] == 'S') {
            grid[x][y] = 'X'; // hit
            if (--shipsCount == 0) {
                return "Hit! You sank the last ship. Game over.";
            }
            return "Hit!";
        } else if (grid[x][y] == '~') {
            grid[x][y] = 'O'; // miss
            return "Miss!";
        } else {
            return "Already attacked here!";
        }
    }

    public synchronized String getBoardState() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sb.append(grid[i][j]).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
