package task8_2;

public class Main2 {
    static Value variable = new Value();

    public static void main(String[] args) throws InterruptedException {
        Thread counter = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_001; i++) {
                    try {
                        variable.increment();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread printer = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1_000_001; i++) {
                    try {
                        variable.print();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        counter.start();
        printer.start();
    }

    static class Value {
        Integer value = 0;
        boolean lock;

        public synchronized void increment() throws InterruptedException {
            if (lock) wait();
            value++;
            lock = true;
            notify();
        }

        public synchronized void print() throws InterruptedException {
            if (!lock) wait();
            System.out.println(value);
            lock = false;
            notify();
        }
    }
}
