package producer_consumer;

import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Producer class
 * We must create one current Thread in Executors.newSingleThreadExecutor()
 */
public class Producer {
    private final ConcurrentLinkedQueue<String> queue;
    private final AtomicBoolean isStop;
    private final String producerName;
    private final ExecutorService executorService;

    private final  int gap;

    public Producer(ConcurrentLinkedQueue<String> queue, AtomicBoolean isStop, String producerName, ExecutorService executorService, int gap) {
        this.queue = queue;
        this.isStop = isStop;
        this.producerName = producerName;
        this.executorService = executorService;
        this.gap = gap;
    }

    public Producer(ConcurrentLinkedQueue<String> queue, AtomicBoolean isStop, String producerName, int gap) {
        this.queue = queue;
        this.isStop = isStop;
        this.producerName = producerName;
        this.gap = gap;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void produce() {
        //our task
        final Runnable runnable = () -> {
            while (!isStop.get()) {
                //add task in queue
                queue.add(producerName + " at " + new Date());
                try {TimeUnit.MILLISECONDS.sleep(gap);}
                catch (InterruptedException e) {}

            }
            //Destroy executor service when isStop is true
            executorService.shutdown();
        };
        //Run our task in executor
        this.executorService.submit(runnable);
    }
}
