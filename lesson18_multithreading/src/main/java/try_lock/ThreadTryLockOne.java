package try_lock;

import lombok.AllArgsConstructor;

import java.util.concurrent.locks.Lock;

@AllArgsConstructor
public class ThreadTryLockOne {
    private Lock lock;

    public void print() {
       lock.lock();
       if (lock.tryLock()) {
           for (int i = 0; i < 10; i++) {
               System.out.println("ThreadTryLockOne : ");
               try {
                   Thread.sleep(10);
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
           }
       }
       lock.unlock();
    }
}
