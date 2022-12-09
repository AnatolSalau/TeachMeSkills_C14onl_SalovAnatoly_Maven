package conditions;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main_Conditions {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        CommonResource commonResource = new CommonResource(condition,lock);
        ThreadDecrement threadDecrement = new ThreadDecrement(lock,commonResource);
        ThreadIncrement threadIncrement = new ThreadIncrement(lock,commonResource,condition);
        Thread common = new Thread(commonResource::start);
        Thread increment = new Thread(threadIncrement::increment);
        Thread decrement = new Thread(threadDecrement::decrement);


        System.out.println("Common resource before increment " + commonResource.getCount());
        increment.start();
        Thread.sleep(10);
        common.start();

        decrement.start();

    }
}
