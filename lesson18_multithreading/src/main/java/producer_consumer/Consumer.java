package producer_consumer;

import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Consumer {
    private final ConcurrentLinkedQueue<String> queue;
    private final AtomicBoolean isStop;
    private final String consumerName;
    private final ExecutorService executorService;

    public Consumer(ConcurrentLinkedQueue<String> queue, AtomicBoolean isStop, String consumerName, ExecutorService executorService, int gap) {
        this.queue = queue;
        this.isStop = isStop;
        this.consumerName = consumerName;
        this.executorService = executorService;
        this.gap = gap;
    }

    public Consumer(ConcurrentLinkedQueue<String> queue, AtomicBoolean isStop, String consumerName, int gap) {
        this.queue = queue;
        this.isStop = isStop;
        this.consumerName = consumerName;
        this.gap = gap;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    private final  int gap;

    void consume() {
        final Runnable runnable = () -> {
            while (!isStop.get()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(gap);
                } catch (InterruptedException e) {
                }
                //get task from queue
                System.err.println(consumerName + " get task : "
                        + queue.poll());
            }
            //Destroy executor service when isStop is true
            executorService.shutdown();
        };
        //Run our task in executor
        this.executorService.submit(runnable);
    }
}
