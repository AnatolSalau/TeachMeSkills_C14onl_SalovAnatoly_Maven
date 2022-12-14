package executors_autoclosable;

import java.util.concurrent.ThreadFactory;

public class ReadResourceThreadFactory implements ThreadFactory {
    private static int count = 0;

    @Override
    public Thread newThread(Runnable r) {
        String name = "ReadResource Thread : " + count;
        count++;
        return new Thread(r, name);
    }
}
