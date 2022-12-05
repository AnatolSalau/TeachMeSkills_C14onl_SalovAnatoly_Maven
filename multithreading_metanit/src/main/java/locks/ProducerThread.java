package locks;

import lombok.Getter;

@Getter
public class ProducerThread implements Runnable {
    private final StoreThread storeThread;
    private final Thread currentThread;

    public ProducerThread(StoreThread storeThread) {
        this.storeThread = storeThread;
        currentThread = new Thread(this::run, "PRODUCER_THREAD");
        currentThread.start();
    }

    @Override
    public void run() {

        while (storeThread.isOpen() == true) {
            storeThread.putProductAutomatic();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
