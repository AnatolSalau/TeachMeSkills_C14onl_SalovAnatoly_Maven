package conditions;

import lombok.AllArgsConstructor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@AllArgsConstructor
public class ThreadIncrement {
    private Lock lock;
    private CommonResource commonResource;

    public void increment() {
        lock.lock();
        if(commonResource.getCount().intValue() <= 10 ) {
            for (int i = 0; i < 10; i++) {
                commonResource.getCount().addAndGet(1);
            }
        }
        lock.unlock();
    }
}
