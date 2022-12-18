package fork_join_framework.managed_blocked;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CommonResource commonResource = new CommonResource();

        RecursiveTaskEx recursiveTaskEx1 = new RecursiveTaskEx(1,commonResource);
        RecursiveTaskEx recursiveTaskEx2 = new RecursiveTaskEx(2,commonResource);
        RecursiveTaskEx recursiveTaskEx3 = new RecursiveTaskEx(3,commonResource);
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);


        forkJoinPool.execute(commonResource.fork());

        forkJoinPool.execute(recursiveTaskEx1.fork());
        forkJoinPool.execute(recursiveTaskEx2.fork());
        forkJoinPool.execute(recursiveTaskEx3.fork());

        Thread.sleep(3000);
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
