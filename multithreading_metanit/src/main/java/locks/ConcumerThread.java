package locks;

import lombok.Getter;

@Getter
public class ConcumerThread extends Thread {
    private final StoreThread storeThread;
    private final Thread currentThread;

    public ConcumerThread(StoreThread storeThread) {
        this.storeThread = storeThread;
        new Thread(this, "CONCUMER_THREAD").start();
        currentThread = Thread.currentThread();
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
