package callable_future_future_task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<Integer> callable = new MyCallable();
        FutureTask futureTask = new FutureTask(callable);
        System.out.println("Start future task");
        new Thread(futureTask).start();
        System.out.println("Start work in Main after starting future task");
        Thread.sleep(500);
        System.out.println("End work in Main after starting future task");

        System.out.println("Result from FutureTask " + futureTask.get());


    }
}
