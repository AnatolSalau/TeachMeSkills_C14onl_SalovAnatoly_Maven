package lock_reenrantlock;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

@AllArgsConstructor
@NoArgsConstructor
public class PrintSynchroReadWriteLockThread {
    private ReadWriteLock lock;
    
    public void print() {
        lock.lock();
        try {
            Thread.sleep(500);
            System.out.println("-----------------Print " + 1 + "---------------------");
            Thread.sleep(500);
            System.out.println("-----------------Print " + 2 + "---------------------");
            Thread.sleep(500);
            System.out.println("-----------------Print " + 3 + "---------------------");
            Thread.sleep(500);
            System.out.println("-----------------Print " + 4 + "---------------------");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.unlock();
    }

}
