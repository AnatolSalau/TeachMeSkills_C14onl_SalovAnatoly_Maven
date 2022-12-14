package executors_autoclosable;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;


public class CommonResource implements Runnable{
    private AtomicBoolean isRun = new AtomicBoolean(false);
    private ConcurrentHashMap <String, String> concurrentHashMap = new ConcurrentHashMap<>();


    public void add(String key, String value) {
        if (isRun.get()) {
            concurrentHashMap.putIfAbsent(key,value);
        }
        else {
            System.out.println("isRun = false");
        }
    }

    public ConcurrentHashMap <String, String> getConcurrentHashMap() {
        if (isRun.get()) {
            return concurrentHashMap;
        }
        else {
            System.out.println("isRun = false");
            return null;
        }
    }

    public void setIsRun(boolean isRun) {
        this.isRun.set(isRun);
    }

    @Override
    public void run() {
        while (isRun.get()) {

        }
    }
}
