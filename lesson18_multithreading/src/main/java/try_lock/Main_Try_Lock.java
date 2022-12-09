package try_lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Thread two starts only after thread one by tryLock
 */
public class Main_Try_Lock {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        ThreadTryLockOne threadTryLockOne= new ThreadTryLockOne(lock);
        ThreadTryLockTwo threadTryLockTwo= new ThreadTryLockTwo(lock);
        Thread threadOne = new Thread(threadTryLockOne::print,"one");
        Thread threadTwo = new Thread(threadTryLockTwo::print,"two");
        threadOne.start();
        threadTwo.start();
    }
}
