package matrixSum;

import matrixSum.strategy.ColumnSumCalcStrategy;
import matrixSum.strategy.ParallelColumnSumCalcStrategy;
import matrixSum.strategy.SerialColumnSumCalcStrategy;

import java.util.Random;

public class MatrixSumCalcTask {
    private final Random random;
    private final int n;
    private final int m;
    private int firstMatrix[][];
    private int secondMatrix[][];
    private int resMatrix[][];
    private ColumnSumCalcStrategy columnSumCalcStrategy;

    public MatrixSumCalcTask(int SIZE) {
        this.n = SIZE;
        this.m = SIZE;
        random = new Random();
        firstMatrix = new int[n][m];
        secondMatrix = new int[n][m];
        resMatrix = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                firstMatrix[i][j] = random.nextInt() % 100;
                secondMatrix[i][j] = random.nextInt() % 100;
                resMatrix[i][j] = random.nextInt() % 100;
            }
        }
        /*System.out.println("Input matrices:");
        System.out.println("First matrix:");
        printMatrix(firstMatrix, n, m);
        System.out.println("Second matrix:");
        printMatrix(secondMatrix, n, m);*/
    }

    public ColumnSumCalcStrategy getColumnSumCalcStrategy() {
        return columnSumCalcStrategy;
    }

    public void setColumnSumCalcStrategy(ColumnSumCalcStrategy columnSumCalcStrategy) {
        this.columnSumCalcStrategy = columnSumCalcStrategy;
    }

    public void executeTaskSerial() {
        long startTime = System.currentTimeMillis();
        setColumnSumCalcStrategy(new SerialColumnSumCalcStrategy(n, m, firstMatrix, secondMatrix, resMatrix));
        columnSumCalcStrategy.initAndStartWorkers();
        long resTime = System.currentTimeMillis() - startTime;
        System.out.println("Res time is: " + resTime);
        //System.out.println("Result matrix is:");
        //printMatrix(resMatrix, n, m);
    }

    public void executeTaskParallel(int THREADS_NUM) {
        long startTime = System.currentTimeMillis();
        setColumnSumCalcStrategy(new ParallelColumnSumCalcStrategy(n, m, THREADS_NUM, firstMatrix, secondMatrix, resMatrix));
        columnSumCalcStrategy.initAndStartWorkers();
        long resTime = System.currentTimeMillis() - startTime;
        System.out.println("Res time is: " + resTime);
        //System.out.println("Result matrix is:");
        //printMatrix(resMatrix, n, m);
    }

    public void printMatrix(int[][] matrix, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(String.format("% 5d", matrix[i][j]));
            }
            System.out.println();
        }
    }
}
