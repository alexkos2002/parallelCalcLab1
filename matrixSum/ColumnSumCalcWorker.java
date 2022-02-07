package matrixSum;

public class ColumnSumCalcWorker extends Thread {
    private final int START_LINE_NUM;
    private final int END_LINE_NUM;
    private final int LINE_LENGTH;
    private int[][] firstMatrix;
    private int[][] secondMatrix;
    private int[][] resMatrix;

    public ColumnSumCalcWorker(int START_LINE_NUM, int END_LINE_NUM, int LINE_LENGTH,
                               int[][] firstMatrix, int[][] secondMatrix, int[][] resMatrix) {
        this.START_LINE_NUM = START_LINE_NUM;
        this.END_LINE_NUM = END_LINE_NUM;
        this.LINE_LENGTH = LINE_LENGTH;
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.resMatrix = resMatrix;
    }

    @Override
    public void run() {
        for (int i = START_LINE_NUM; i < END_LINE_NUM; i++) {
            if (isInterrupted()) {
                break;
            }
            for (int j = 0; j < LINE_LENGTH; j++) {
                resMatrix[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
            }
        }
    }
}
