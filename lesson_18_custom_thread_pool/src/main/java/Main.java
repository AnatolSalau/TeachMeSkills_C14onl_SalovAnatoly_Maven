import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws TimeoutException {
        int runnableCount = 10;
        SimpleThreadpool threadpool = SimpleThreadpool.getInstance(3);
        AtomicInteger count = new AtomicInteger(0);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                count.getAndIncrement();
                System.out.println("Thread name : " + Thread.currentThread().getName() + ", number is " + count);
            }
        };
        for (int i = 0; i < runnableCount; i++) {
            threadpool.execute(r);
        }
        threadpool.stop();
        threadpool.awaitTermination();
    }
}
