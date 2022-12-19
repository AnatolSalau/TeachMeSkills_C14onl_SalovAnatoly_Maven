package fork_join_framework.managed_blocked_common_pool;

import java.util.concurrent.ForkJoinPool;

/**
 * ForkJoinPool with
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        CommonResource commonResource = new CommonResource();
        RecursiveTaskEx recursiveTaskEx1 = new RecursiveTaskEx(1,commonResource);
        RecursiveTaskEx recursiveTaskEx2 = new RecursiveTaskEx(2,commonResource);
        RecursiveTaskEx recursiveTaskEx3 = new RecursiveTaskEx(3,commonResource);
        RecursiveTaskEx recursiveTaskEx4 = new RecursiveTaskEx(4,commonResource);
        RecursiveTaskEx recursiveTaskEx5 = new RecursiveTaskEx(5,commonResource);
        RecursiveTaskEx recursiveTaskEx6 = new RecursiveTaskEx(6,commonResource);
        RecursiveTaskEx recursiveTaskEx7 = new RecursiveTaskEx(7,commonResource);
        RecursiveTaskEx recursiveTaskEx8 = new RecursiveTaskEx(8,commonResource);
        RecursiveTaskEx recursiveTaskEx9 = new RecursiveTaskEx(9,commonResource);

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        forkJoinPool.execute(commonResource.fork());
        forkJoinPool.execute(recursiveTaskEx1.fork());
        forkJoinPool.execute(recursiveTaskEx2.fork());
        forkJoinPool.execute( recursiveTaskEx3.fork());
        forkJoinPool.execute( recursiveTaskEx4.fork());
        forkJoinPool.execute( recursiveTaskEx5.fork());
        forkJoinPool.execute( recursiveTaskEx6.fork());
        forkJoinPool.execute( recursiveTaskEx7.fork());
        forkJoinPool.execute( recursiveTaskEx8.fork());
        forkJoinPool.execute( recursiveTaskEx9.fork());

        Thread.sleep(2000);
        commonResource.addIdTask(1);
        commonResource.printAllID();

        Thread.sleep(2000);
        commonResource.deleteIdTask(1);
        commonResource.printAllID();

        Thread.sleep(2000);

        commonResource.addIdTask(1);
        commonResource.printAllID();

        Thread.sleep(2000);

        commonResource.deleteIdTask(1);
        commonResource.printAllID();

        commonResource.stop();

    }
}
