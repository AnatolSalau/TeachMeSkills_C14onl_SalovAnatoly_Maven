package custom_executor_1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Custom pool for ThreadPoolRunnable
 */
public class CustomThreadPool {
    //queue tasks for executing
    private BlockingQueue queueOfTasks;
    private List<ThreadPoolRunnable> listOfRunnables;
    private boolean isStopped;

    public CustomThreadPool(int numOfThreads, int maxOfTasks) {
        this.queueOfTasks = new ArrayBlockingQueue(maxOfTasks);
        this.listOfRunnables = new ArrayList<>();
        this.isStopped = false;

        //Create ThreadPoolRunnable and add it in listOfRunnables
        for (int i = 0; i < numOfThreads; i++) {
            //Create new ThreadPoolRunnable
            ThreadPoolRunnable threadPoolRunnable =
                    new ThreadPoolRunnable(queueOfTasks);
            listOfRunnables.add(threadPoolRunnable);
        }
        //Run every ThreadPoolRunnable from listOfRunnables im new Thread
        for (ThreadPoolRunnable threadPoolRunnable : listOfRunnables) {
            Thread thread = new Thread(threadPoolRunnable);
            thread.start();
        }
    }

    public  synchronized  void execute(Runnable task) {
        //check work or not CustomThreadPool
        if(this.isStopped) {
            throw new IllegalStateException("Thread pool is stopped");
        }
        //add task in queue of tasks
        this.queueOfTasks.add(task);
    }

    public synchronized void stop() {
        // change isStopped to true
        this.isStopped = true;
        // change isStopped in every ThreadPoolRunnable from listOfRunnables
        for (ThreadPoolRunnable threadPoolRunnable : this.listOfRunnables) {
            threadPoolRunnable.doStop();
        }
    }

    public synchronized void waitUntilAllTasksFinished() {
        // wait while queueOfTasks is not empty
        while (this.queueOfTasks.size() > 0) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
