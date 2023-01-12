package custom_executor_stoped;

import java.util.concurrent.BlockingQueue;

public class ThreadPoolRunnable implements Runnable{
    private Thread thread = null;
    private BlockingQueue blockingQueue;
    private boolean isStopped = false;

    public ThreadPoolRunnable(BlockingQueue blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        thread = Thread.currentThread();
        while (!isStopped) {
            try {
                if(blockingQueue.isEmpty()) {
                    continue;
                }
                Runnable runnable =
                        (Runnable) blockingQueue.take();
                //Run task in current thread
                runnable.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

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
                ", blockingQueue=" + blockingQueue +
                ", isStopped=" + isStopped +
                '}';
    }
}
