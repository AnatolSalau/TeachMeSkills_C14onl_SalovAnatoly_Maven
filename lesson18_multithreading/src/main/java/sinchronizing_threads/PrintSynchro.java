package sinchronizing_threads;

public class PrintSynchro {
    //synchronized method - if one thread use this method, the rest will be waiting
    synchronized public void print() throws InterruptedException {
        Thread.sleep(100);
        System.out.println("-----------------Print " + 1 + "---------------------");
        Thread.sleep(100);
        System.out.println("-----------------Print " + 2 + "---------------------");
        Thread.sleep(100);
        System.out.println("-----------------Print " + 3 + "---------------------");
        Thread.sleep(100);
        System.out.println("-----------------Print " + 4 + "---------------------");
        Thread.sleep(100);
    }
}
