package sinchronizing_threads.sinchronizingbywaitnotify;

public class PrintSynchroWaitNotify {

/*    synchronized method - if one thread use this method, the rest will be waiting
    wait() - the thread that went inside will fall asleep
    and make room (place) for another thread
    notify - run thread in the same order in which they entered
    notifyAll() - run all threads
    works only in synchronized block area
    */

    boolean isSleep = false;

    synchronized public void print() throws InterruptedException {
        Thread.sleep(100);
        System.out.println("-----------------Print " + 1 + "---------------------");
        Thread.sleep(100);
        if (isSleep == false) {
            isSleep = true;
            this.wait();
        }
        System.out.println("-----------------Print " + 2 + "---------------------");
        Thread.sleep(100);
        System.out.println("-----------------Print " + 3 + "---------------------");
        Thread.sleep(100);
        System.out.println("-----------------Print " + 4 + "---------------------");
        Thread.sleep(100);

        //start another thread
        this.notify();
    }
}

