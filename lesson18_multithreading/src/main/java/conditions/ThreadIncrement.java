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
        System.out.println("Increment  is running");
        while (commonResource == null || commonResource.getIsAvalible().get() == false) {
            //wait when common resource is running
                System.out.println("common resource is not creating - stop increment thread");
                lock.lock();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }

        }
        lock.lock();
        condition.signalAll();
        lock.unlock();
    }
}
