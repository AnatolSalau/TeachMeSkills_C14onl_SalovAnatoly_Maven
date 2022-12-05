package locks;

import java.lang.management.ManagementFactory;

/**
 * Condition condition from RerentalLock
 *  await() = wait()
 *  signall() = notify()
 * Current program cant stop producer thread
 */
public class MainLocks {
    public static void main(String[] args) throws InterruptedException {
        ManagementFactory. getPlatformMBeanServer() ;
        //Thread extends Thread
        System.out.println("START");
        StoreThread storeThread = new StoreThread();
        ProducerThread producerThread = new ProducerThread(storeThread);
        ConcumerThread concumerThread = new ConcumerThread(storeThread);
        producerThread.start();
        concumerThread.start();

        Thread.sleep(4000);

        storeThread.closeMagazine();

        System.out.println("Magazine is closed");

        Thread.sleep(2000);
        producerThread.interrupt();
        System.out.println("storeThread is alive : " + storeThread.getCurrentThread().isAlive());


        /*
        storeThread is alive : true
        producerThread is alive : true
        concumerThread is alive : true
         */
    }
}
