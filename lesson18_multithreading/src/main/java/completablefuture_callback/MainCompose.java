package completablefuture_callback;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Compose two feature and CallBack their result
 */
public class MainCompose {
    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10; i++) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println(i);
            CompletableFuture<String> printSum = sumArray()                    // Result from FirstFeature
                    .thenCompose( sumArr -> sumToString(sumArr))    // Put Result from FirstFeature to SecondFeature
                    .thenApply(                                                // CallBack result from SecondFeature
                            s -> {System.out.println(s);return s;}
                    );
        }
    }
    //FirstFeature for creating Integer
    public static CompletableFuture<Integer> sumArray() {
        return CompletableFuture.supplyAsync(() -> {
            int count = 0;
            for (int i = 0; i < 5; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                count ++;
            }
            return count;
        });
    }
    //Second feature for creating String from Integer
    public static CompletableFuture<String> sumToString(Integer sumArray) {
        return CompletableFuture.supplyAsync(()-> {
            return "Sum in array is : " + sumArray;
        });
    }
}
