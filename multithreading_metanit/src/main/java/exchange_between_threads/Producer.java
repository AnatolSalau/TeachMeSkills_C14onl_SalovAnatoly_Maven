package exchange_between_threads;
import java.util.concurrent.Exchanger;


public class Producer implements Runnable {
    private final Exchanger<String> exchanger;
    private String string;

    public Producer(Exchanger<String> exchanger, String string) {
        this.exchanger = exchanger;
        this.string = string;
        //Run current object in new thread
        new Thread(this).start();
    }

    @Override
    public void run() {
        System.out.println("Producer start");
        try {
            System.out.println("Old value producer : " + string);
                string = exchanger.exchange(string);
            System.out.println("New value producer : " + string);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Producer end");
    }
}
