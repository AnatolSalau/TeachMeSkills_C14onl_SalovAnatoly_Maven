package fork_join_framework.work_pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public class TaskPoolWithBlocking extends RecursiveAction {

    private int counter;         //recursive counter
    private ForkJoinPool pool;
    private String id;
    private AtomicBoolean isRun;
    private final boolean isRecursion;

    public TaskPoolWithBlocking(String id, int counter, ForkJoinPool forkJoinPool) {
        this.id = id;
        this.counter = counter;
        this.pool = forkJoinPool;
        this.isRun = new AtomicBoolean(false);
        this.isRecursion = false;
    }
    @Deprecated
    public TaskPoolWithBlocking(String id, int counter, ForkJoinPool forkJoinPool, boolean isRecursion) {
        this.counter = counter;
        this.id = id;
        this.pool = forkJoinPool;
        this.isRecursion = isRecursion;
    }

    @Override
    protected void compute() {
        while (isRecursion) {
            System.out.println(toString());
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
        }


        //break;
        if (counter <= 0) return;

        for (int i = 0; i <= counter+1; i++) {
            TaskPoolWithBlocking next = new TaskPoolWithBlocking(id,counter-1, pool, true);
            next.fork();
        }

    }

    public void stop() {
        isRun.set(false);
    }

    @Override
    public String toString() {
        return "TaskPoolWithBlocking{" +
                "counter=" + counter +
                ", pool=" + pool +
                ", id='" + id + '\'' +
                ", isRun=" + isRun +
                ", isRecursion=" + isRecursion +
                '}';
    }
}
