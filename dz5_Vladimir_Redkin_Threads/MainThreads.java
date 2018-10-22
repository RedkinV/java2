package ru.geekbrains.java2.lesson5;

public class MainThreads {
    public static void main(String[] args) {

        final int A=1000000;
        final int THREADS=4;
        float[] flArr=new float[A];
        float[][] flArrThreads =new float[THREADS][];

        for (int i = 0; i <flArr.length ; i++) {
            flArr[i]=1;
        }

        long a = System.currentTimeMillis();
        for (int i = 0; i <flArr.length ; i++) {
            flArr[i]= calc(flArr[i],i);
        }
        System.out.println("Однопоточное выполнение. Время: "+(System.currentTimeMillis()-a));

        for (int i = 0; i <flArr.length ; i++) {
            flArr[i]=1;
        }

        a = System.currentTimeMillis();             //засекаем время

        Thread[] t = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            flArrThreads[i]=new float[A/THREADS];
            System.arraycopy(flArr,(i*THREADS),flArrThreads[i],0,A/THREADS);

            int finalI = i;                     // компилятор настоял, чтобы переменная i была final. Почему?
            t[i]=new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j <flArrThreads[finalI].length ; j++) {
                        flArrThreads[finalI][j]= calc(flArrThreads[finalI][j],j);
                    }
                }
            });
        }
        for (int i = 0; i <THREADS ; i++) { // запускаем потоки
            t[i].start();
        }

        for (int i = 0; i <THREADS ; i++) { // сливаем потоки в один
            try {
                t[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i <THREADS ; i++) { // объединяем массивы в один
           System.arraycopy(flArrThreads[i],0,flArr,i*(A/THREADS),A/THREADS);
        }

        System.out.println("Многопоточное выполнение. Потоков- " + THREADS+" Время: "+(System.currentTimeMillis()-a));


//        for (int i = 0; i < THREADS; i++) {
//            for (int j = 0; j < A/THREADS; j++) {
//                System.out.print(flArrThreads[i][j]+", ");
//            }
//            System.out.println();
//        }



    }
    public static float calc(float v, int i){
        return (float)(v * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }

}
