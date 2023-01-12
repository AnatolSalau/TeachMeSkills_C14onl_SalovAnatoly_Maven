package custom_executor_stoped;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    private static ThreadPool  threadPool = new ThreadPool(3,10);
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            int taskNumber = i;
            threadPool.executeOrWaitIfQueueIsFull(() -> {
                System.out.println(
                        Thread.currentThread().getName() + " number : " + taskNumber
                );
            }, 5);
        }

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
