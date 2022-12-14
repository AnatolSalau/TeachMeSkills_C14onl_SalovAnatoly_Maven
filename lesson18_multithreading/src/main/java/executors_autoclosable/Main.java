package executors_autoclosable;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) throws Exception {
        CommonResource commonResource = new CommonResource();
        Thread commonResourceThread = new Thread(commonResource);
        ReadResourceThreadFactory readResourceThreadFactory = new ReadResourceThreadFactory();

        commonResourceThread.start();
        commonResource.setIsRun(true);
        commonResource.add("1", "1");
        commonResource.add("2", "2");
        commonResource.add("3", "3");
        commonResource.add("4", "4");
        commonResource.add("5", "5");
        commonResource.add("6", "6");
        commonResource.add("7", "7");
        commonResource.add("8", "8");
        try(ReadResourceService readResourceService =
                    new ReadResourceService(commonResource, readResourceThreadFactory)) {
            CompletableFuture<List<String>> listCompletableFuture = readResourceService.readInNewThread();
            CompletableFuture<List<String>> listCompletableFuture2 = readResourceService.readInNewThread();
            CompletableFuture<List<String>> listCompletableFuture3 = readResourceService.readInNewThread();
            CompletableFuture<List<String>> listCompletableFuture4 = readResourceService.readInNewThread();

            //If we don't call method get - the main thread - don't wait us
            listCompletableFuture.get();
            listCompletableFuture2.get();
            listCompletableFuture3.get();
            listCompletableFuture4.get();
        }

        commonResource.setIsRun(false);
    }

}
