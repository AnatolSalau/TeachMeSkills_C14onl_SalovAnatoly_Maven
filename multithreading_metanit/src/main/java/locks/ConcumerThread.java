package locks;

import lombok.Getter;

@Getter
public class ConcumerThread implements Runnable {
    private final StoreThread storeThread;
    private final Thread currentThread;

    public ConcumerThread(StoreThread storeThread) {
        this.storeThread = storeThread;
        currentThread = new Thread(this::run, "CONCUMER_THREAD");
        currentThread.start();
    }

    @Override
    public void run() {
        while (storeThread.isOpen() == true) {
            storeThread.getProductAutomatic();
            try {

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
