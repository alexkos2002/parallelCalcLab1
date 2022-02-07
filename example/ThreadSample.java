package example;

public class ThreadSample {
    public static int SIZE = 1000;
    public static int NUNMBER_THREADS = 7;
    public static void main(String [] args ) throws InterruptedException{

        double vectA[] = new double [SIZE];
        double vectB[] = new double [SIZE];
        int rand_min=1;
        int rand_max=42;

        for(int i =0; i<SIZE; i++){ //початкове заповнення векторів випадковими величинами з зазначеного проміжку
            vectA[i]= rand_min + (int) (Math.random() * rand_max);
            vectB[i]= rand_min + (int) (Math.random() * rand_max);
        }

        double serialResult =0;
        for( int i=0; i< SIZE; i++){
            serialResult+=vectA[i]*vectB[i];
        }

        System.out.println("Serial result:   " + serialResult);

        ThreadCacl TreadArray[] = new ThreadCacl[NUNMBER_THREADS];

        for(int i = 0; i < NUNMBER_THREADS; i++){ //розбиття на потоки
            TreadArray[i] = new ThreadCacl(vectA ,vectB,
                    SIZE/NUNMBER_THREADS * i,
                    i==(NUNMBER_THREADS - 1)?SIZE:SIZE/NUNMBER_THREADS * (i + 1)); //тернарна умовна операція
            TreadArray[i].start();
        }
        for(int i = 0; i < NUNMBER_THREADS; i++){ //очікування завершення усіх потоків
            TreadArray[i].join();
        }
        double parallelResult = 0;
        for(int i = 0; i < NUNMBER_THREADS; i++){ //збір результатів паралельної роботи
            parallelResult += TreadArray[i].getResult();
        }

        System.out.println("Parallel result: " + parallelResult);
    }
}

