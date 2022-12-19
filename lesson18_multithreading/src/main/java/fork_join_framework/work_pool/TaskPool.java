package fork_join_framework.work_pool;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class TaskPool extends RecursiveAction {

    private int counter;         //recursive counter
    private ForkJoinPool pool;
    private String id;
    private AtomicBoolean isRun;

    public TaskPool(String id, int counter, ForkJoinPool forkJoinPool) {
        this.id = id;
        this.counter = counter;
        this.pool = forkJoinPool;
        this.isRun = new AtomicBoolean(false);
    }

    @Override
    protected void compute() {
        this.isRun.set(true);
        System.out.println(
                "counter=" + counter + " ID : " + id +
                        " activeThreads=" + pool.getActiveThreadCount() +
                        " runningThreads=" + pool.getRunningThreadCount() +
                        " poolSize=" + pool.getPoolSize() +
                        " queuedTasks=" + pool.getQueuedTaskCount() +
                        " queuedSubmissions=" + pool.getQueuedSubmissionCount() +
                        " parallelism=" + pool.getParallelism() +
                        " stealCount=" + pool.getStealCount());
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {}

        //break;
        if (counter <= 0) return;

        while (isRun.get()) {
            TaskPool next = new TaskPool(id,counter-1, pool);
            next.fork();
            next.join();
        }
    }

    public void stop() {
        isRun.set(false);
    }

}
