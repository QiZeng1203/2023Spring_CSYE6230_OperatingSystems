import java.util.Random;

public class Main {
    private static int ARRAY_SIZE = 50000000;
    private static int THREAD_COUNT = 4;
    private static int PART_SIZE = ARRAY_SIZE / THREAD_COUNT;
    private static long[] PART_SUM = new long[THREAD_COUNT];
    private static double[] ARRAY = new double[ARRAY_SIZE];

    static void generateValues() {
        Random random = new Random();
        for (int i = 0; i < ARRAY_SIZE; i++) {
            ARRAY[i] = 0.1 + (0.9 - 0.1) * random.nextDouble();
        }
    }

    public static void main(String[] args) throws Exception {
        generateValues();


        // Calculate the sum of the array in serial
        long startTime = System.currentTimeMillis();
        double sum = 0;
        for (int i = 0; i < ARRAY_SIZE; i++) {
            sum += ARRAY[i];
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Serial sum: " + sum);
        System.out.println("Serial time: " + (endTime - startTime) + "ms");

        // Calculate the sum of the array in parallel with 4 threads
        startTime = System.currentTimeMillis();
        double[] partialSums = new double[THREAD_COUNT];
        Thread[] threads = new Thread[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadIndex = i;
            threads[i] = new Thread(() -> {
                int startIndex = threadIndex * PART_SIZE;
                int endIndex = (threadIndex == THREAD_COUNT - 1) ? ARRAY_SIZE : (threadIndex + 1) * PART_SIZE;
                double partSum = 0;
                for (int j = startIndex; j < endIndex; j++) {
                    partSum += ARRAY[j];
                }
                partialSums[threadIndex] = partSum;
            });
            threads[i].start();
        }
        for (int i = 0; i < THREAD_COUNT; i++) {
            threads[i].join();
        }
        double totalSum = 0;
        for (int i = 0; i < THREAD_COUNT; i++) {
            totalSum += partialSums[i];
        }
        endTime = System.currentTimeMillis();
        System.out.println("Parallel sum: " + totalSum);
        System.out.println("Parallel time: " + (endTime - startTime) + "ms");

    }
}