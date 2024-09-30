package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int rowsA, colsA, rowsB, colsB;

        try {
            rowsA = getParam("Enter the number of rows for matrix A: ");
            colsA = getParam("Enter the number of columns for matrix A: ");
            rowsB = getParam("Enter the number of rows for matrix B: ");
            colsB = getParam("Enter the number of columns for matrix B: ");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error during console input: " + e.getMessage());
        }

        int[][] matrixA = getRandomMatrix(rowsA, colsA);
        int[][] matrixB = getRandomMatrix(rowsB, colsB);

        int[][] result = new int[0][];

        try {
            result = multiplyMatrices(matrixA, matrixB);
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

    public static int sumOfMaxElementsInColumns(int[][] matrix) {
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

    public static int[][] getRandomMatrix(int rows, int cols) {
        Random random = new Random();
        int[][] matrix = new int[rows][cols];
        final int minValue = -20;
        final int maxValue = 20;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextInt(maxValue - minValue + 1) + minValue;
            }
        }

        return matrix;
    }

    public static int getParam(String message) throws IllegalArgumentException {
        Scanner scanner = new Scanner(System.in);
        System.out.printf(message);
        if (!scanner.hasNextInt()) {
            scanner.close();
            throw new IllegalArgumentException("Only number types are allowed.");
        }
        int param = scanner.nextInt();
        if (param <= 0) {
            scanner.close();
            throw new IllegalArgumentException("Only positive numbers are allowed.");
        }
        return param;
    }
}