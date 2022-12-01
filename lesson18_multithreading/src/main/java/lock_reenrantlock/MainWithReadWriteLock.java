package lock_reenrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainWithReadWriteLock {
    public static void main(String[] args) {
            ReadWriteLockService readWriteLockService = new ReadWriteLockService();

        Thread threadWrite = new Thread(readWriteLockService::write);
        Thread threadRead= new Thread(readWriteLockService::read);
        threadWrite.start();
        threadRead.start();
    }
}
