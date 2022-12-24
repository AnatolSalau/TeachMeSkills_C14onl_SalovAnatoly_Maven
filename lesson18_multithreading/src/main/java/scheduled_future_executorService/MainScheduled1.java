package scheduled_future_executorService;

import java.util.concurrent.*;

public class MainScheduled1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Create scheduled executor
        ScheduledExecutorService scheduledExecutorService = Executors
                .newSingleThreadScheduledExecutor();

        Callable<Void> callable = () -> {
            System.out.println("Running task 2");
            return null;
        };
        System.out.println("Running task 1");
        //Run task after 5 seconds = with delay 5 seconds
        ScheduledFuture<Void> schedule = scheduledExecutorService.schedule(callable, 5, TimeUnit.SECONDS);
        System.out.println("Running task 3");
        System.out.println(schedule.get());
        //We must shutdown executor service
        scheduledExecutorService.shutdown();
    }
}
