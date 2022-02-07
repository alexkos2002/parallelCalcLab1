package matrixSum.strategy;

import matrixSum.ColumnSumCalcWorker;

public class ParallelColumnSumCalcStrategy extends ColumnSumCalcStrategy {

    private int THREADS_NUM;
    private ColumnSumCalcWorker[] columnSumCalcWorkers;

    public ParallelColumnSumCalcStrategy(int n, int m, int THREADS_NUM,
                                         int[][] firstMatrix, int[][] secondMatrix, int[][] resMatrix) {
        super(n, m, firstMatrix, secondMatrix, resMatrix);
        this.THREADS_NUM = THREADS_NUM;
        this.columnSumCalcWorkers = new ColumnSumCalcWorker[THREADS_NUM];
        final int linesPerThread = n / THREADS_NUM;
        int workerCounter = 0;
        for (int i = 0; i < n; i += linesPerThread) {
            columnSumCalcWorkers[workerCounter] = new ColumnSumCalcWorker(i, i + linesPerThread, m,
                    firstMatrix, secondMatrix, resMatrix);
            workerCounter++;
        }
    }

    public void setThreadsNum(int THREADS_NUM) {
        if (n % THREADS_NUM != 0) {
            throw new IllegalArgumentException();
        }
        this.THREADS_NUM = THREADS_NUM;
    }

    @Override
    public void initAndStartWorkers() {
        final int linesPerThread = n / THREADS_NUM;
        int workerCounter = 0;
        for (int i = 0; i < n; i += linesPerThread) {
            (columnSumCalcWorkers[workerCounter]).start();
            System.out.println("Starting worker " + (columnSumCalcWorkers[workerCounter]).getName() + "...");
            workerCounter++;
        }

        for (int i = 0; i < THREADS_NUM; i++) {
            try {
                (columnSumCalcWorkers[i]).join();
            } catch (InterruptedException e) {
                System.out.println(e.getCause());
                e.printStackTrace();
                interruptAllThreads();
            }
        }
    }

    public void interruptAllThreads() {
        for (int i = 0; i < THREADS_NUM; i++) {
            (columnSumCalcWorkers[i]).interrupt();
        }
    }
}
