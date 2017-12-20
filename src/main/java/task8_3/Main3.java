package task8_3;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main3 {

    public final static int threadsNum = 3;
    static Map<String, Integer> simpleMap = new HashMap<>();
    //static Map<String, Integer> simpleMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {
        long start = System.nanoTime();
        ExecutorService service = Executors.newFixedThreadPool(threadsNum);

        MapReader[] readers = new MapReader[threadsNum];
        MapWriter[] writers = new MapWriter[threadsNum];
        for (int i = 0; i < threadsNum; i++) {
            writers[i] = new MapWriter(i);
            readers[i] = new MapReader(i);
            service.execute(new MapWriter(i));
            service.execute(new MapReader(i));
        }

        service.shutdown();
        service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
        long timeUsed = (System.nanoTime() - start) / 1000000L;
        System.out.println("All threads are completed in " + timeUsed + " ms");
    }

    static class MapReader implements Runnable {

        private int number;

        public MapReader(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1_000_000; i++) {
                simpleMap.get("key" + i);
            }
        }
    }

    static class MapWriter implements Runnable {

        private int number;

        public MapWriter(int number) {
            this.number = number;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1_000_000; i++) {
                simpleMap.put("key" + i, i * number - 1);
            }
        }
    }
}