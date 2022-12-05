package locks;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ConcumerThread extends Thread {
    private final StoreThread storeThread;

    public ConcumerThread(StoreThread storeThread) {
        this.storeThread = storeThread;
    }

    @Override
    public void run() {
        while (storeThread.isOpen()) {
            storeThread.getProductAutomatic();
            try {

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
