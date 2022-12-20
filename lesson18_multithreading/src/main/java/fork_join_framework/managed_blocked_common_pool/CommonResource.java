package fork_join_framework.managed_blocked_common_pool;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Common resource - contain Tasks ID
 * If task see own ID - it will be blocked
 * If doesn't - do personal work
 */
public class CommonResource extends RecursiveAction {
    private AtomicBoolean isRun;
    //Pull for tasks ID
    private ConcurrentLinkedQueue<Integer> tasksID;

    public CommonResource() {
        this.isRun = new AtomicBoolean(true);
        this.tasksID = new ConcurrentLinkedQueue<>();
    }

    @Override
    protected void compute() {
        //Our work
        while (isRun.get()){
            System.out.println("CommonResource is run in Thread : "
                    + Thread.currentThread().getName());
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
