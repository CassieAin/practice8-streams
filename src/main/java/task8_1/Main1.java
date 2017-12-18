package task8_1;

import static java.lang.Thread.sleep;

public class Main1 {

    public static void main(String [] args){
        Thread myThread = new Thread(() -> {
            try {
                for (int i = 10; i > 0; i--) {
                    System.out.print(i + " ");
                    sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("\nBomb");
        });
        myThread.start();
    }
}
