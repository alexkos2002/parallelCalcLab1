package example;

public class ThreadCacl extends Thread{

    double vectA[];
    double vectB[];
    int startIndex;
    int endIndex;
    double result;

    public ThreadCacl(double[] vectA, double[] vectB, int startIndex, int endIndex) { //конструктор класу, приймає дані для обчислень
        this.vectA = vectA;
        this.vectB = vectB;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public double getResult() {
        return result;
    }

    @Override
    public void run(){ //обрахунки, що здійснюватимуться в зазначеному потоці
        for(int i = startIndex; i < endIndex; i++ ){
            result += vectA[i] * vectB[i];
        }
    }

}
