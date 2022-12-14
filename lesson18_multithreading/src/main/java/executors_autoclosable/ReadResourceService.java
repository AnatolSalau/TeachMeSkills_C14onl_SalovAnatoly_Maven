package executors_autoclosable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Wrap executor service in Autocloseable
 * Use newFixedThreadPool with ThreadFactory
 * Run every read operation in new thread
 */
public class ReadResourceService implements AutoCloseable{
    private CommonResource commonResource;
    private ExecutorService executorService;
    private ReadResourceThreadFactory readResourceThreadFactory;

    public ReadResourceService(CommonResource commonResource, ReadResourceThreadFactory readResourceThreadFactory)
    {
        this.commonResource = commonResource;
        this.readResourceThreadFactory = readResourceThreadFactory;
        // newFixedThreadPool with Thread Factory
        this.executorService = Executors.newFixedThreadPool(1,readResourceThreadFactory);
    }

    public CompletableFuture<List<String>> readInNewThread() {
        if (commonResource.getConcurrentHashMap() != null) {
            return CompletableFuture.supplyAsync(
                    () -> {
                        List<String> result = new ArrayList<>();
                        commonResource.getConcurrentHashMap().entrySet().forEach(
                                entry -> {
                                    String s = entry.getValue();
                                    result.add(s);
                                    System.out.println("Thread name : " + Thread.currentThread().getName() + " value is " + s);
                                }
                        );
                        return result;
                    }, executorService
            );
        }
        return null;
    }

    //Auto close Executor in Try with Resources
    @Override
    public void close() throws Exception {
        executorService.shutdown();
    }
}
