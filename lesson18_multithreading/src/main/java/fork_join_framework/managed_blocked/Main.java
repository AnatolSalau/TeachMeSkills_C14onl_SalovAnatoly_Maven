package fork_join_framework.managed_blocked;

import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ThreadFactory;

import static java.util.concurrent.TimeUnit.MINUTES;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        CommonResource commonResource = new CommonResource();
        RecursiveTaskEx recursiveTaskEx1 = new RecursiveTaskEx(1,commonResource);
        RecursiveTaskEx recursiveTaskEx2 = new RecursiveTaskEx(2,commonResource);
        RecursiveTaskEx recursiveTaskEx3 = new RecursiveTaskEx(3,commonResource);

        int parallelism = 4;
/*        ForkJoinPool.ForkJoinWorkerThreadFactory defaultForkJoinWorkerThreadFactory = ForkJoinPool.defaultForkJoinWorkerThreadFactory;
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = Thread.currentThread().getUncaughtExceptionHandler();
        ForkJoinPool forkJoinPool = new ForkJoinPool(
                parallelism,
                defaultForkJoinWorkerThreadFactory,
                uncaughtExceptionHandler,
                false, 1, 1, 1, null, 1, MINUTES
        );*/
        ForkJoinPool forkJoinPool = new ForkJoinPool(parallelism);
        forkJoinPool.execute(commonResource.fork());
        forkJoinPool.execute(recursiveTaskEx1.fork());
        forkJoinPool.execute(recursiveTaskEx2.fork());
        forkJoinPool.execute( recursiveTaskEx3.fork());

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
