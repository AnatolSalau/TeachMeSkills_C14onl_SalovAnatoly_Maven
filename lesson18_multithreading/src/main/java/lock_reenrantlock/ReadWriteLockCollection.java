package lock_reenrantlock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCollection {

    /*
     * ReadWriteLock have two locks
     * first lock - read lock - this lock block all writing operations, because if we read data ->
     * we must don't write information at the same time
     *
     * second lock - write lock - this lock block all reading operations, because if we write data  ->
     * we must don't read information at the same time
     * */

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private String[] arr = new String[100000];

    public void read() {
        lock.readLock().lock();
        for (String s:
                arr) {
            System.out.println("read");
        }
        lock.readLock().unlock();
    }

    public void write() {
        lock.writeLock().lock();
        for (String s:
             arr) {
            System.out.println("write");
        }
        lock.writeLock().unlock();
    }
}
