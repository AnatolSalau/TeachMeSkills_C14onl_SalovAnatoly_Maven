package fork_join_framework.managed_blocked;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class RecursiveTaskEx extends RecursiveTask<Integer> {
    private CommonResource commonResource;
    private final AtomicInteger taskID;
    private AtomicBoolean isRun;

    ReentrantLock lock;

    public RecursiveTaskEx(int taskID, CommonResource commonResource) {
        this.taskID = new AtomicInteger(taskID);
        this.isRun = new AtomicBoolean(true);
        this.commonResource = commonResource;
        this.lock = new ReentrantLock();
    }

    @Override
    protected Integer compute() {
        while (isRun.get()) {
            System.out.println("Task " + taskID + " is run in thread + " + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}

            if (!commonResource.isContain(taskID.get())) {
                System.out.println("Task " + taskID + " is doing something work ");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            } else {
                try {
                    ForkJoinPool.managedBlock(new ManagedBlockerImpl(lock));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }
}
