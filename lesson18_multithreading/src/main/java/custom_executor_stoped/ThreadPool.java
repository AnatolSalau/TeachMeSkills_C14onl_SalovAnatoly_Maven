package custom_executor_stoped;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * This class just create as match as needed ThreadPoolRunnable
 * every ThreadPoolRunnable in own thread
 */
public class ThreadPool {
    private BlockingQueue blockingQueueOfTasks;

    private List<ThreadPoolRunnable> runnables = new ArrayList<>();

    private  boolean isStopped = false;

    private List<Thread> threads = new ArrayList<>();

    /**
     * Constructor
     * @param numOfThreads - max quantity threads = max parallelism
     * @param maxOfTasks - capacity of queue, max quantity tasks in buffer
     */
    public ThreadPool (int numOfThreads, int maxOfTasks) {
        // create buffer for tasks with capacity as match as we needed
        blockingQueueOfTasks = new ArrayBlockingQueue(maxOfTasks);
        //Create ThreadPoolRunnable as match as we needed
        for (int i = 0; i < numOfThreads; i++) {
            ThreadPoolRunnable threadPoolRunnable =
                    new ThreadPoolRunnable(blockingQueueOfTasks);
            runnables.add(threadPoolRunnable);
        }
        //Create ThreadPoolRunnable as match as we needed
        for (ThreadPoolRunnable runnable :
             runnables) {
            Thread thread = new Thread(runnable);
            thread.start();
            threads.add(thread);
        }
    }

    /**
     * Add task in buffer
     * @param task - task in which we execute method run
     */
    public synchronized void execute(Runnable task) {
        //Throw exception if Thread pool is stopped
        if(this.isStopped) {
            throw new IllegalStateException("Thread pool is stopped");
        }
        this.blockingQueueOfTasks.add(task);
    }

    /**
     * Add task in buffer or wait if queue is full
     * @param task - task in which we execute method run
     * @param millisecondsTimeout - as match milliseconds we wait if queue is full
     */
    public synchronized void executeOrWaitIfQueueIsFull(Runnable task, int millisecondsTimeout) {
        //Throw exception if Thread pool is stopped
        if(this.isStopped) {
            throw new IllegalStateException("Thread pool is stopped");
        }

        boolean offerWasDone = false;
        //Wait while offer in buffer is false
        while (offerWasDone == false) {
            offerWasDone = this.blockingQueueOfTasks.offer(task);
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

    /**
     * wait while buffer has tasks and then in every ThreadPoolRunnable execute method stop
     */
    public synchronized void waitUntilAllTaskFinishedAndStop() {
        while (this.blockingQueueOfTasks.size() > 0) {
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
