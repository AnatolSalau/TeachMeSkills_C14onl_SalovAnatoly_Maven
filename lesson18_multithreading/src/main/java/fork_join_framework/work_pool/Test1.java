package fork_join_framework.work_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class Test1 {

    private static ForkJoinPool pool = new ForkJoinPool(2);

    private static class SomeAction extends RecursiveAction {

        private int counter;         //recursive counter
        private int childrenCount=80;//amount of children to spawn
        private int idx;             // just for displaying

        private SomeAction(int counter, int idx) {
            this.counter = counter;
            this.idx = idx;
        }

        @Override
        protected void compute() {

            System.out.println(
                    "counter=" + counter + "." + idx +
                            " activeThreads=" + pool.getActiveThreadCount() +
                            " runningThreads=" + pool.getRunningThreadCount() +
                            " poolSize=" + pool.getPoolSize() +
                            " queuedTasks=" + pool.getQueuedTaskCount() +
                            " queuedSubmissions=" + pool.getQueuedSubmissionCount() +
                            " parallelism=" + pool.getParallelism() +
                            " stealCount=" + pool.getStealCount());
            System.out.println(Thread.currentThread().getName());
            if (counter <= 0) return;

            List<SomeAction> list = new ArrayList<>(childrenCount);
            for (int i=0;i<childrenCount;i++){
                SomeAction next = new SomeAction(counter-1,i);
                list.add(next);
                next.fork();
            }


            for (SomeAction action:list){
                action.join();
            }
        }
    }

    public static void main(String[] args) throws Exception{
        pool.invoke(new SomeAction(2,0));
    }
}