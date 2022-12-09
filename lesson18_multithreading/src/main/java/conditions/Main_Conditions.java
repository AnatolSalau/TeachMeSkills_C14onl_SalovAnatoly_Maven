package conditions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main_Conditions {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        CommonResource commonResource = new CommonResource();
        ThreadDecrement threadDecrement = new ThreadDecrement(lock,commonResource);
        ThreadIncrement threadIncrement = new ThreadIncrement(lock,commonResource);

        Thread increment = new Thread(threadIncrement::increment);
        Thread decrement = new Thread(threadDecrement::decrement);

        System.out.println("Common resource before increment " + commonResource.getCount());
        increment.start();
        decrement.start();

        while (increment.isAlive() & decrement.isAlive()) {
            Thread.sleep(100);
        }
        System.out.println("Common resource after increment " + commonResource.getCount());

    }
}
