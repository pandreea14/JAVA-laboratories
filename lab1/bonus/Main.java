import java.util.*;

public class Main {
    public static void main(String[] args) {
        int n = 6;

        int[][] matrix = createAdjacencyMatrix(n);

        System.out.println("Adjacency Matrix:");
        printMatrix(matrix);
    }
    public static int[][] createAdjacencyMatrix(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matrix[i][j] = 0; //diag e 0
                } else if (i == 0 || j == 0) {
                    matrix[i][j] = 1; // nodul din centru, cel legat cu toti ceilalti
                } else if (Math.abs(i - j) == 1 || Math.abs(i - j) == n - 1) {
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j]=0;
                }
            }
        }
        matrix[1][n-1] = 1;
        matrix[n-1][1] = 1;

        return matrix;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}
