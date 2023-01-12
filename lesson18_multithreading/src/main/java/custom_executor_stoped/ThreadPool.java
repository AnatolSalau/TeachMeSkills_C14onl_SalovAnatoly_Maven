package custom_executor_stoped;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
    private BlockingQueue blockingQueue;
    private List<ThreadPoolRunnable> runnables =
            new ArrayList<>();
    private  boolean isStopped = false;

    private List<Thread> threads = new ArrayList<>();

    public ThreadPool (int numOfThreads, int maxOfTasks) {
        blockingQueue = new ArrayBlockingQueue(maxOfTasks);

        for (int i = 0; i < numOfThreads; i++) {
            ThreadPoolRunnable threadPoolRunnable =
                    new ThreadPoolRunnable(blockingQueue);
            runnables.add(threadPoolRunnable);
        }

        for (ThreadPoolRunnable runnable :
             runnables) {
            Thread thread = new Thread(runnable);
            thread.start();
            threads.add(thread);
        }
    }

    public synchronized void execute(Runnable task) {
        if(this.isStopped) {
            throw new IllegalStateException("Thread pool is stopped");
        }
        this.blockingQueue.add(task);
    }

    public synchronized void executeOrWaitIfQueueIsFull(Runnable task, int millisecondsTimeout) {
        if(this.isStopped) {
            throw new IllegalStateException("Thread pool is stopped");
        }

        boolean offer = false;
        while (offer == false) {
            offer = this.blockingQueue.offer(task);
            try {
                TimeUnit.MILLISECONDS.sleep(millisecondsTimeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public synchronized void forceStopWithoutWaiting() {
        this.isStopped = true;
        for (ThreadPoolRunnable threadPoolRunnable :
        runnables) {
            threadPoolRunnable.doStop();
        }
    }

    public synchronized void waitUntilAllTaskFinishedAndStop() {
        while (this.blockingQueue.size() > 0) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.isStopped = true;
        for (ThreadPoolRunnable threadPoolRunnable :
                runnables) {
            threadPoolRunnable.doStop();
        }
    }

    public synchronized List<ThreadPoolRunnable> getRunnables() {
        return runnables;
    }

    public synchronized List<Thread> getThreads() {
        return threads;
    }
}
