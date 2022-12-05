package locks;

import lombok.Getter;

@Getter
public class ProducerThread extends Thread {
    private final StoreThread storeThread;
    private final Thread currentThread;

    public ProducerThread(StoreThread storeThread) {
        this.storeThread = storeThread;
        new Thread(this::run, "PRODUCER_THREAD").start();
        currentThread = Thread.currentThread();
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
