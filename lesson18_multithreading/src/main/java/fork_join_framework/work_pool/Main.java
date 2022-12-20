package fork_join_framework.work_pool;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Execute Tasks by Recursive ForkJoinPool with ThreadFactory and UncaughtExceptionHandler
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String,Boolean> tasks = new ConcurrentHashMap<>();

        //Create ForkJoinThreadFactory with ThreadFactory and UncaughtExceptionHandler
        ForkJoinPool.ForkJoinWorkerThreadFactory defaultForkJoinWorkerThreadFactory =
                ForkJoinPool.defaultForkJoinWorkerThreadFactory;
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler =
                Thread.currentThread().getUncaughtExceptionHandler();

        ForkJoinPool forkJoinPool = new ForkJoinPool(
                3,
                defaultForkJoinWorkerThreadFactory,
                uncaughtExceptionHandler,
                false);
        ReentrantLock lock = new ReentrantLock();
        TaskPool taskPool1 = new TaskPool("1", 3, forkJoinPool);
        tasks.putIfAbsent("1", false);

        //TaskPool taskPool2 = new TaskPool("2", 3, forkJoinPool, tasks, lock);


        TaskPoolWithBlocking taskPoolWithBlockin1 = new TaskPoolWithBlocking("1",1,forkJoinPool, tasks);
        TaskPoolWithBlocking taskPoolWithBlockin2 = new TaskPoolWithBlocking("2",1,forkJoinPool, tasks);
        //forkJoinPool.execute(taskPool1);
        //forkJoinPool.execute(taskPool2);
        forkJoinPool.execute(taskPoolWithBlockin1);
        //forkJoinPool.execute(taskPoolWithBlockin2);

        Thread.sleep(1000);

        //taskPool1.stop();
        //taskPool2.stop();
        taskPoolWithBlockin1.stop();
        //taskPoolWithBlockin2.stop();
    }
}
