package task8_2;

public class Synchronization {
    private volatile int number = 0;

    public synchronized void increment() {
        number++;
    }

    public synchronized int getNumber() {
        return number;
    }
}
