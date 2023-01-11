
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests the SimpleThreadpool
 *
 * @author Sriram
 */
public class SimpleThreadpoolTest {
    @Test
    public void testSimpleThreadpool() throws Exception {
        int runnableCount = 10;
        SimpleThreadpool threadpool = SimpleThreadpool.getInstance();
        final AtomicInteger count = new AtomicInteger(0);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                count.getAndIncrement();
            }
        };
        for (int i = 0; i < runnableCount; i++) {
            threadpool.execute(r);
        }
        threadpool.stop();
        threadpool.awaitTermination();
        System.out.println("Runnables executed should be same as runnables sent to threadpool " + runnableCount + " : " + count.get() );
        Assertions.assertEquals(runnableCount, count.get());
    }

    @Test
    public void testSimpleThreadpoolCustomThreadcount() throws Exception {
        int threadCount = 20;
        SimpleThreadpool threadpool = SimpleThreadpool.getInstance(threadCount);
        final AtomicInteger count = new AtomicInteger(0);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                count.getAndIncrement();
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    //Do nothing
                }
            }
        };
        for (int i = 0; i < threadCount; i++) {
            threadpool.execute(r);
        }
        threadpool.stop();
        // Threadpool should stop within 300ms as there are enough threads to handle all runnables
        threadpool.awaitTermination(300);
        System.out.println("All runnables must be executed " + threadCount + " : " + count.get() );
        Assertions.assertEquals(threadCount, count.get());
    }

    @Test
    public void testAwaitTermination() throws Exception {
        int runnableCount = 6;
        SimpleThreadpool threadpool = SimpleThreadpool.getInstance(runnableCount / 2);
        final AtomicInteger count = new AtomicInteger(0);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                count.getAndIncrement();
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    //Do nothing
                }
            }
        };
        for (int i = 0; i < runnableCount; i++) {
            threadpool.execute(r);
        }
        threadpool.stop();
        threadpool.awaitTermination();
        Assertions.assertEquals(runnableCount, count.get());
    }

    @Test()
    public void testAwaitTerminationWithTimeout() throws Exception {
        int runnableCount = 10;
        SimpleThreadpool threadpool = SimpleThreadpool.getInstance(runnableCount / 2);
        final AtomicInteger count = new AtomicInteger(0);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                count.getAndIncrement();
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    //Do nothing
                }
            }
        };
        for (int i = 0; i < runnableCount; i++) {
            threadpool.execute(r);
        }
        threadpool.stop();
        // Threadpool should throw an exception here as the runnables cannot finish execution before the timeout
        TimeoutException exception = assertThrows(TimeoutException.class, () -> {
            threadpool.awaitTermination(300);
        });
        String message = exception.getMessage();
        System.out.println("Execution should await all runnables, message : " + message);
        Assertions.assertEquals("Unable to terminate threadpool within the specified timeout (300ms)", message);
    }

    @Test
    public void testTerminate() throws Exception {
        int runnableCount = 10;
        SimpleThreadpool threadpool = SimpleThreadpool.getInstance(runnableCount / 2);
        final AtomicInteger count = new AtomicInteger(0);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                count.getAndIncrement();
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    //Do nothing
                }
            }
        };
        for (int i = 0; i < runnableCount; i++) {
            threadpool.execute(r);
        }
        threadpool.terminate();
        // Threadpool should terminate before all runnables are executed
        threadpool.awaitTermination(300);
        System.out.println("Threadpool should terminate without executing pending runnables, is false " + (runnableCount == count.get()));
        Assertions.assertFalse(runnableCount == count.get());

    }
}
