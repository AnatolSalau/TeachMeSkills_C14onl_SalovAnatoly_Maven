package fork_join_framework.managed_blocked;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommonResource extends RecursiveAction {
    private AtomicBoolean isRun;
    private ConcurrentLinkedQueue<Integer> tasksID;

    public CommonResource() {
        this.isRun = new AtomicBoolean(true);
        this.tasksID = new ConcurrentLinkedQueue<>();
    }

    @Override
    protected void compute() {
        while (isRun.get()){
            System.out.println("CommonResource is run in Thread : "
                    + Thread.currentThread().getName()
            + " isRun is " + this.isRun.get());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {}
        }
    }
    public void addIdTask(int idTask) {
        tasksID.add(idTask);
    }
    public void deleteIdTask(int idTask) {
        tasksID.remove(idTask);
    }

    public boolean isContain(int id) {
        boolean contains = this.tasksID.contains(id);
        return contains;
    }
    public void printAllID() {
        System.out.println(tasksID.toString());
    }
    public void stop() {
        this.isRun.set(false);
    }
}
