package custom_executor_stoped;

import java.util.concurrent.BlockingQueue;

/**
 * This class just take tasks from blockingQueueOfTasks
 * and execute their method run in every Task
 */
public class ThreadPoolRunnable implements Runnable{
    //Current thread in which we execute tasks
    private Thread thread = null;
    private BlockingQueue blockingQueueOfTasks;
    private boolean isStopped = false;

    public ThreadPoolRunnable(BlockingQueue blockingQueue) {
        this.blockingQueueOfTasks = blockingQueue;
    }

    @Override
    public void run() {
        thread = Thread.currentThread();
        while (!isStopped) {
            try {
                //skip one iteration if queue is empty
                if(blockingQueueOfTasks.isEmpty()) {
                    continue;
                }
                //get task from queue
                Runnable runnable =
                        (Runnable) blockingQueueOfTasks.take();
                //Run task in current thread
                runnable.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //Stop this thread and stop loop in run() method
    public synchronized void doStop() {
        isStopped = true;
        thread.interrupt();
    }

    public synchronized boolean isStopped() {
        return isStopped;
    }

    @Override
    public String toString() {
        return "ThreadPoolRunnable{" +
                "thread=" + thread +
                ", blockingQueue=" + blockingQueueOfTasks +
                ", isStopped=" + isStopped +
                '}';
    }
}
