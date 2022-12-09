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
        this.count = new AtomicInteger(0);
        this.isAvalible = new AtomicBoolean(false);
    }

    public void start() {
        try {
            Thread.sleep(10);
            this.isAvalible.setRelease(true);
            Thread.sleep(10);
            lock.lock();
            condition.signalAll();
            lock.unlock();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {

        }
    }
}
