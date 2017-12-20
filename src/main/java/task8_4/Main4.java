package task8_4;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main4 {

    private static int n = 1_000_000;
    private static final int threadsNumber = 10;

    public static void main(String[] args) {
        RecursiveSum.array = generateRandomArray(n);

        ForkJoinPool pool = new ForkJoinPool(threadsNumber);
        long resultSum = pool.invoke(new RecursiveSum(0, n));
        long simpleSum = sumArray(RecursiveSum.array);

        System.out.printf("\t Sum for range 1..%d: calculated sum %d, iterative sum %d %n", n, resultSum, simpleSum);
    }

    static class RecursiveSum extends RecursiveTask<Long> {
        static int[] array;
        int from, to;

        public RecursiveSum(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public Long compute() {
            if ((to - from) <= array.length / threadsNumber) {
                long localSum = 0;
                for (int i = from; i < to; i++) {
                    localSum += array[i];
                }

                System.out.printf("\t Summing of range %d to %d %n", from, to, localSum);
                return localSum;
            } else {
                int mid = (from + to) / 2;
                System.out.printf("\t Forking into two ranges %d to %d and %d to %d %n", from, mid, mid, to);
                RecursiveSum secondHalf = new RecursiveSum(mid + 1, to);
                RecursiveSum firstHalf = new RecursiveSum(from, mid);
                firstHalf.fork();
                secondHalf.fork();
                return firstHalf.join() + secondHalf.join();
            }
        }
    }

    public static int[] generateRandomArray(int number) {
        int[] input = new int[number];
        for (int i = 0; i < input.length; i++) {
            input[i] = new Random().nextInt((100 - 0) + 0);
        }
        return input;
    }

    public static long sumArray(int[] input){
        long sum = 0;
        for(int i = 0; i < input.length; i++){
            sum += input[i];
        }
        return sum;
    }
}
