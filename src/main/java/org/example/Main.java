package org.example;

public class Main {
    public static void main(String[] args) {
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6}
        };

        int[][] matrix2 = {
                {7, 8},
                {9, 10},
                {11, 12}
        };

        int[][] result = new int[0][];

        try {
            result = multiplyMatrices(matrix1, matrix2);
            System.out.println("Result of matrix multiplication:");
            printMatrix(result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error during matrix multiplication: " + e.getMessage());
        }

        int sum = sumOfMaxElementsInColumns(result);
        System.out.println("Sum of the largest elements of each column: " + sum);
    }

    public static int[][] multiplyMatrices(int[][] matrixA, int[][] matrixB) throws IllegalArgumentException {
        if (matrixA[0].length != matrixB.length) {
            throw new IllegalArgumentException("The number of columns of the matrix A must be equal to the number of rows of the matrix B.");
        }

        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;

        int[][] result = new int[rowsA][colsB];

        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                for (int k = 0; k < colsA; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return result;
    }

    public static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static int sumOfMaxElementsInColumns(int[][] matrix) throws IllegalArgumentException {
        int cols = matrix[0].length;
        int sum = 0;

        for (int j = 0; j < cols; j++) {
            int max = Integer.MIN_VALUE;
            for (int[] ints : matrix) {
                if (ints[j] > max) {
                    max = ints[j];
                }
            }
            sum += max;
        }
        return sum;
    }
}