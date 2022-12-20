package fork_join_framework.work_pool;

import fork_join_framework.managed_blocked_common_pool.ManagedBlockerImpl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;

public class TaskPoolWithBlocking extends RecursiveAction {

    private int counter;         //recursive counter of threads
    private ForkJoinPool pool;
    private String id;
    private AtomicBoolean isRun;
    private final boolean isRecursion;

    private ReentrantLock lock;

    private ConcurrentHashMap<String,Boolean> tasks;

    public TaskPoolWithBlocking(String id, int counter, ForkJoinPool forkJoinPool, ConcurrentHashMap<String,Boolean> tasks) {
        this.id = id;
        this.counter = counter;
        this.pool = forkJoinPool;
        this.isRun = new AtomicBoolean(false);
        this.isRecursion = false;
        this.tasks = tasks;
        this.lock = null;
    }
    @Deprecated
    public TaskPoolWithBlocking(String id, int counter, ForkJoinPool forkJoinPool, boolean isRecursion, ConcurrentHashMap<String,Boolean> tasks) {
        this.counter = counter;
        this.id = id;
        this.pool = forkJoinPool;
        this.isRecursion = isRecursion;
        this.tasks = tasks;
        this.lock = new ReentrantLock();
    }

    @Override
    protected void compute() {
        //if we dont create recursion - we ignore that
        while (isRecursion) {
            //blocking by continue
            if(tasks.get(id)) {
                continue;
            }
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

        //break from recursion;
        if (counter <= 0) return;

        // we create new Task
        for (int i = 0; i < counter; i++) {
            TaskPoolWithBlocking next = new TaskPoolWithBlocking(id,counter-1, pool, true, tasks);
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

    public ReentrantLock getLock() {
        return this.lock;
    }
}
