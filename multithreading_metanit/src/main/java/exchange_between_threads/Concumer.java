package exchange_between_threads;

import java.util.concurrent.Exchanger;

public class Concumer implements Runnable {
    private final Exchanger<String> exchanger;
    private String string;

    public Concumer(Exchanger<String> exchanger, String string) {
        this.exchanger = exchanger;
        this.string = string;
        //Run current object in new thread
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Concumer start");
        try {
            System.out.println("Old value concumer : " + string);
            string = exchanger.exchange(string);
            System.out.println("New value concumer : " + string);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Concumer end");
    }
}
