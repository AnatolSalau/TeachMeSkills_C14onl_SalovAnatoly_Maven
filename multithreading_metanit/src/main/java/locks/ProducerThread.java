package locks;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProducerThread extends Thread {
    private final StoreThread storeThread;

    public ProducerThread(StoreThread storeThread) {
        this.storeThread = storeThread;
    }

    @Override
    public void run() {

        while (storeThread.isOpen()) {
            storeThread.putProductAutomatic();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
