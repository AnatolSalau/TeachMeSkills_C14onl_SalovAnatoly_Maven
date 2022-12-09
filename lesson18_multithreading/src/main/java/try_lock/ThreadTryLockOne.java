package try_lock;

import lombok.AllArgsConstructor;

import java.util.concurrent.locks.Lock;

/**
 * Current thread do work 5 milliseconds, after his unlock - start thread two
 */
@AllArgsConstructor
public class ThreadTryLockOne {
    private final Lock lock;

    public void print() {
        lock.lock();
        System.out.println("Thread " + Thread.currentThread().getName() + " started");
        try {
            System.out.println("Thread " + Thread.currentThread().getName() + " start work");
            Thread.sleep(5);
            System.out.println("Thread " + Thread.currentThread().getName() + " finish work");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " finished");
        lock.unlock();
    }
}
