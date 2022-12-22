package completablefuture_callback;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture .anyOf
 */
public class Main_anyOf {
    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(8);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "future1";
                }
        );
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(6);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "future2";
                }
        );
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(4);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "future3";
                }
        );
        //Create compositeFuture anyOf
        CompletableFuture<Object> anyOfFuture = CompletableFuture
                .anyOf(future1, future2, future3);

        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //Run callBack anyOf
            anyOfFuture.thenApply(result -> {
                System.out.println(result);
                return result;
            });
        }
    }
}
