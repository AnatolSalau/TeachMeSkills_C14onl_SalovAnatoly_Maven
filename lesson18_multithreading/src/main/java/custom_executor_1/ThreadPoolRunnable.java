package custom_executor_1;

import java.util.concurrent.BlockingQueue;

/**
 * Class for creating thread and execute tasks in this thread
 */
public class ThreadPoolRunnable implements Runnable {
    //current Thread
    private Thread thread;

    //queue tasks for executing
    private BlockingQueue queueOfTasks;
    private boolean isStopped;

    public ThreadPoolRunnable(BlockingQueue queueOfTasks) {
        this.thread = null;
        this.queueOfTasks = queueOfTasks;
        this.isStopped = false;
    }

    @Override
    public void run() {
        //create Thread
        this.thread = Thread.currentThread();
        //Get tasks from queue by loop if isStopped = false
        while (!isStopped()) {
            //Task from queue
            try {
                //run task for execution in this thread
                System.out.println("Hello");
                Runnable runnable = (Runnable) queueOfTasks.take();
                runnable.run();
            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }
    }

    //stop current thread
    public synchronized void doStop() {
        this.isStopped = true;
        this.thread.interrupt();
    }

    //get isStopped
    public synchronized boolean isStopped() {
        return isStopped;
    }
}
