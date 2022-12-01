package lock_reenrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainWithReadWriteLock {
    public static void main(String[] args) {

        ReadWriteLock lock = new ReentrantReadWriteLock();
        /*
        * ReadWriteLock have two locks
        * first lock - read lock - this lock block all writing operations, because if we read data ->
        * we must don't write information at the same time
        *
        * second lock - write lock - this lock block all reading operations, because if we write data  ->
        * we must don't read information at the same time
        * */

        PrintSynchroReadWriteLockThread print =
                new PrintSynchroReadWriteLockThread(lock);

        Thread threadOne =
                new Thread(print::print, "one");
        Thread threadTwo =
                new Thread(print::print, "two");

        threadOne.start();
        threadTwo.start();
    }
}
