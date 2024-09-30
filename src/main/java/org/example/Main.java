package org.example;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int rows, cols;

        try {
            rows = getParam("Enter the number of rows for matrices: ");
            cols = getParam("Enter the number of columns for matrices: ");
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error during console input: " + e.getMessage());
        }

        short[][] matrixA = getRandomMatrix(rows, cols);
        System.out.println("\nMatrix A:");
        printMatrix(matrixA);

        short[][] matrixB = getRandomMatrix(rows, cols);
        System.out.println("\nMatrix B:");
        printMatrix(matrixB);

        short[][] result = new short[rows][cols];

        try {
            result = addMatrices(matrixA, matrixB);
            System.out.println("\nResult of matrix addition:");
            printMatrix(result);
        } catch (IllegalArgumentException e) {
            System.out.println("Error during matrix addition: " + e.getMessage());
        }

        try {
            int sum = sumOfMaxElementsInColumns(result);
            System.out.println("\nSum of the largest elements of each column: " + sum);
        } catch (IllegalArgumentException e) {
            System.out.println("Error during calculation of the sum of the largest elements of each column: " + e.getMessage());
        }
    }

    public static short[][] addMatrices(short[][] matrixA, short[][] matrixB) throws IllegalArgumentException {
        if (isNotRectangularMatrix(matrixA) || isNotRectangularMatrix(matrixB)) {
            throw new IllegalArgumentException("The matrices must be rectangular.");
        }

        if (!haveSameDimensions(matrixA, matrixB)) {
            throw new IllegalArgumentException("The matrices must have the same dimensions.");
        }

        int rows = matrixA.length;
        int cols = matrixA[0].length;

        short[][] result = new short[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = (short) (matrixA[i][j] + matrixB[i][j]);
            }
        }

        return result;
    }

    public static void printMatrix(short[][] matrix) {
        for (short[] row : matrix) {
            for (short element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static int sumOfMaxElementsInColumns(short[][] matrix) {
        if (isNotRectangularMatrix(matrix)) {
            throw new IllegalArgumentException("The matrix must be rectangular.");
        }

        int cols = matrix[0].length;
        int sum = 0;

        for (int j = 0; j < cols; j++) {
            int max = Integer.MIN_VALUE;
            for (short[] shorts : matrix) {
                if (shorts[j] > max) {
                    max = shorts[j];
                }
            }
            sum += max;
        }
        return sum;
    }

    public static short[][] getRandomMatrix(int rows, int cols) {
        Random random = new Random();
        short[][] matrix = new short[rows][cols];
        final int minValue = -20;
        final int maxValue = 20;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = (short) (random.nextInt(maxValue - minValue + 1) + minValue);
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

    public static boolean isNotRectangularMatrix(short[][] matrix) {
        int length = matrix[0].length;
        for (short[] row : matrix) {
            if (row.length != length) return true;
        }
        return false;
    }

    public static boolean haveSameDimensions(short[][] matrix1, short[][] matrix2) {
        return (matrix1.length == matrix2.length) && (matrix1[0].length == matrix2[0].length);
    }
}