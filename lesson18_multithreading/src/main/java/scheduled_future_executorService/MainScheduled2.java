package scheduled_future_executorService;

import java.util.Date;
import java.util.concurrent.*;

public class MainScheduled2 {
    private static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        //Create scheduled executor
        ScheduledExecutorService scheduledExecutorService = Executors
                .newSingleThreadScheduledExecutor();

        Runnable runnable = () -> {
            System.out.println("Running task - " + new Date());
            count++;
        };
        Runnable runnable2 = () -> {
            System.out.println("Running task fixed delay - " + new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
        };
        //Run task with period in 5 seconds
        //In scheduleAtFixedRate we can put only scheduleAtFixedRate
        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.scheduleAtFixedRate(
                runnable, 0, 5, TimeUnit.SECONDS
        );

        //Create task with scheduledFixedDelay - we run next task only after 3 seconds after
        //the end of previous task
        scheduledExecutorService.scheduleWithFixedDelay(
                runnable2, 0,3, TimeUnit.SECONDS
        );


        while (true) {
            if( count >= 10) {
                System.out.println("Count is more than 5 = break");
                //Cancel scheduleAtFixedRate
                scheduledFuture.cancel(true);
                scheduledExecutorService.shutdown();
                break;
            }
            System.out.println(count);
            TimeUnit.SECONDS.sleep(1);

        }

        scheduledExecutorService.awaitTermination(1, TimeUnit.HOURS);
    }
}
