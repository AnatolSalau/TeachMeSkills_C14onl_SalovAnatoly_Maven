package fork_join_framework.managed_blocked_common_pool;

import java.util.concurrent.ForkJoinPool;

/**
 * ForkJoinPool without recursion, and we use ForkJoinPool.commonPool();
 * .commonPool() - Returns the common pool instance. This pool is statically constructed;
 * its run state is unaffected by attempts to shutdown() or shutdownNow().
 * However this pool and any ongoing processing are automatically terminated
 * upon program System.exit(int). Any program that relies on asynchronous task processing
 * to complete before program termination should invoke commonPool().awaitQuiescence, before exit.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Create tasks or actions by RecursiveTask or RecursiveAction
        CommonResource commonResource = new CommonResource();
        RecursiveTaskEx recursiveTaskEx1 = new RecursiveTaskEx(1,commonResource);
        RecursiveTaskEx recursiveTaskEx2 = new RecursiveTaskEx(2,commonResource);
        RecursiveTaskEx recursiveTaskEx3 = new RecursiveTaskEx(3,commonResource);

        //Create default static pool
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();


        //Run tasks without recursion, right here
        forkJoinPool.execute(commonResource.fork());
        forkJoinPool.execute(recursiveTaskEx1.fork());
        forkJoinPool.execute(recursiveTaskEx2.fork());
        forkJoinPool.execute( recursiveTaskEx3.fork());

        Thread.sleep(2000);
        //block thread by ManagedBlocked
        System.out.println("Block task 1");
        commonResource.addIdTask(1);
        commonResource.printAllID();

        Thread.sleep(2000);
        //delete block thread
        commonResource.deleteIdTask(1);
        System.out.println("Release task 1");
        commonResource.printAllID();

        Thread.sleep(2000);

        commonResource.addIdTask(1);
        commonResource.printAllID();

        Thread.sleep(2000);

        commonResource.deleteIdTask(1);
        commonResource.printAllID();

        //Stop while in common resource
        commonResource.stop();

    }
}
