package producer_consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProducerConsumerCreator {
    private final ConcurrentLinkedQueue<String> queue;
    private final AtomicBoolean isStop;
    private final ScheduledExecutorService scheduledExecutorService;

    private final List<Producer> producers;

    private final List<Consumer> consumers;

    private static int i = 0;

    private final ExecutorService executorService;

    //Constructor with parameters
    public ProducerConsumerCreator() {
        this.queue = new ConcurrentLinkedQueue<>();
        this.isStop = new AtomicBoolean(false);
        this.scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        this.executorService = Executors.newFixedThreadPool(4);
        this.producers = new ArrayList<>();
        this.consumers = new ArrayList<>();
    }

    public void start() throws InterruptedException {
        //Create our producers with loop
        while (i <= 3) {
            Producer producer = new Producer(
                    queue, isStop, "Producer " + i, 500
            );
            Consumer consumer = new Consumer(
                    queue, isStop, "Consumer " + i, 1000
            );
            producers.add(producer);
            consumers.add(consumer);
            producer.produce();
            TimeUnit.MILLISECONDS.sleep(500);
            consumer.consume();
            i ++;
        }
        System.out.println("Producers and Consumers will be created");
        //Change our value is after 10 sec
        scheduledExecutorService.schedule(() -> isStop.set(true),
                10, TimeUnit.SECONDS);
/*        for (int j = 0; j < 4; j++) {
            TimeUnit.SECONDS.sleep(1);
        }*/

        isStop.set(true);
        scheduledExecutorService.shutdown();
    }
}
