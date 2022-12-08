package try_lock;

import lombok.AllArgsConstructor;

import java.util.concurrent.locks.Lock;

/**
 * Method print only if lock is false
 */
@AllArgsConstructor
public class ThreadTryLockTwo {
    private Lock lock;

    public void print() {
        while (true) {
            if (lock.tryLock()) {
                for (int i = 0; i < 10; i++) {
                    System.out.println("ThreadTryLockTwo : ");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            }

        }
    }
}
