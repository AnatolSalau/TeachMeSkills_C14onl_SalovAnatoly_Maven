package custom_executor_stoped;

import java.util.List;

/**
 * Program start point
 */
public class Main {
    //Create Thread Pool
    private static ThreadPool  threadPool = new ThreadPool(5,10);

    // Program start point method
    public static void main(String[] args) throws InterruptedException {
        //Create tasks and add task in buffer
        for (int i = 0; i < 100; i++) {
            int taskNumber = i;
            threadPool.executeOrWaitIfQueueIsFull(() -> {
                System.out.println(
                        Thread.currentThread().getName() + " number : " + taskNumber
                );
            }, 0);
        }
        //finish all threads after queue of tasks is empty
        threadPool.waitUntilAllTaskFinishedAndStop();
    }

    private static void  printThreads() {
        List<ThreadPoolRunnable> runnables = threadPool.getRunnables();
        for (ThreadPoolRunnable runnable :
                runnables) {
            System.out.println(runnable);
        }
    }
}
