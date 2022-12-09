package conditions;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


@Getter
public class CommonResource {
    private  AtomicInteger count;
    private AtomicBoolean isAvalible;

    private Condition condition;
    private Lock lock;

    public CommonResource(Condition condition, Lock lock) {
        this.condition = condition;
        this.lock = lock;
        this.isAvalible = new AtomicBoolean(true);
        this.count = new AtomicInteger(0);

        lock.lock();
        condition.signal();
        lock.unlock();
    }

    public void start() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            isAvalible.set(false);
            lock.lock();
            condition.signalAll();
            lock.unlock();
        }
    }
}
