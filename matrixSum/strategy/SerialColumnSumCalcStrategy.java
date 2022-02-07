package matrixSum.strategy;

import matrixSum.strategy.ColumnSumCalcStrategy;

public class SerialColumnSumCalcStrategy extends ColumnSumCalcStrategy {

    public SerialColumnSumCalcStrategy(int n, int m, int[][] firstMatrix, int[][] secondMatrix, int[][] resMatrix) {
        super(n, m, firstMatrix, secondMatrix, resMatrix);
    }

    @Override
    public void initAndStartWorkers() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                resMatrix[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
            }
        }
    }
}
