package conditions;

import lombok.AllArgsConstructor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@AllArgsConstructor
public class ThreadIncrement {
    private Lock lock;
    private CommonResource commonResource;
    private Condition condition;

    public void increment() {
        while (true) {
            if (commonResource == null) {
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if(commonResource.getIsAvalible().get() == false) {
                break;
            }
        }
        lock.lock();
        condition.signalAll();
        lock.unlock();
    }
}
