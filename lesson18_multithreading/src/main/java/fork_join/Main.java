package fork_join;

import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        OneThreadTask oneThreadTask = new OneThreadTask();
/*        oneThreadTask.run();*/
        //Get quantity of threads from our system
        final int quantityThread = Runtime.getRuntime().availableProcessors();
        System.out.println(quantityThread);
        //Set in ForkJoinPool constructor - quantity of threads
        ForkJoinPool forkJoinPool = new ForkJoinPool(quantityThread);

        forkJoinPool.invoke(new RecursiveTaskService(
                -4_000_000_000_0L,8_000_000_000_0L
        ));

        forkJoinPool.shutdown();

    }
}
