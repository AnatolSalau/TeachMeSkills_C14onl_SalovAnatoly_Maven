package try_lock;

import lombok.AllArgsConstructor;

import java.util.concurrent.locks.Lock;

/**
 * Method print only if lock is true
 */
@AllArgsConstructor
public class ThreadTryLockTwo {
    private final Lock lock;

    public void print() {
        System.out.println("Thread " + Thread.currentThread().getName() + " started");
        while (true) {
            boolean isLock = lock.tryLock();
            if (isLock) {
                System.out.println("Thread  " + Thread.currentThread().getName() + " start work, lock is " + isLock);
                System.out.println("Thread  " + Thread.currentThread().getName() + " finish work");
                break;
            } else {
                System.out.println("Thread is " + Thread.currentThread().getName() + " waiting, lock is " + isLock );
            }
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " finished");
    }
}
