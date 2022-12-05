package locks;

import java.lang.management.ManagementFactory;

public class MainLocks {
    public static void main(String[] args) throws InterruptedException {
        ManagementFactory. getPlatformMBeanServer() ;
        //Thread extends Thread
        System.out.println("START");
        StoreThread storeThread = new StoreThread();
        ProducerThread producerThread = new ProducerThread(storeThread);
        ConcumerThread concumerThread = new ConcumerThread(storeThread);


        Thread.sleep(3000);

        storeThread.setOpen(false);
        System.out.println("Magazine is closed");

        Thread.sleep(3000);
        System.out.println("storeThread : " + storeThread);
        System.out.println("storeThread is alive : " + storeThread.getCurrentThread().isAlive());
        System.out.println("producerThread is alive : " + producerThread.getCurrentThread().isAlive());
        System.out.println("concumerThread is alive : " + concumerThread.getCurrentThread().isAlive());
        /*
        storeThread is alive : true
        producerThread is alive : true
        concumerThread is alive : true
         */
    }
}
