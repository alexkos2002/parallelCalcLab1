package matrixSum.strategy;

public abstract class ColumnSumCalcStrategy {

    protected final int n;
    protected final int m;
    protected int firstMatrix[][];
    protected int secondMatrix[][];
    protected int resMatrix[][];

    public ColumnSumCalcStrategy(int n, int m, int[][] firstMatrix, int[][] secondMatrix, int[][] resMatrix) {
        this.n = n;
        this.m = m;
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.resMatrix = resMatrix;
    }

    public abstract void initAndStartWorkers();
}
