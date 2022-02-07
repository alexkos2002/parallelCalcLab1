import example.ThreadSample;
import matrixSum.MatrixSumCalcTask;

public class Main {

    public static void main(String[] args) throws InterruptedException{
        //ThreadSample.main(new String[]{});
        MatrixSumCalcTask task = new MatrixSumCalcTask(16000);
        task.executeTaskSerial();
        task.executeTaskParallel(4);
    }

}
