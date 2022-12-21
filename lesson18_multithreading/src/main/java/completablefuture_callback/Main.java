package completablefuture_callback;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> function =CompletableFuture
                .supplyAsync(() -> {
                    Integer result = 0;
                    for (int i = 0; i < 5; i++) {
                        try {
                            result +=1;
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    return result;
        });
        int count = 0;
        for (int i = 0; i < 10 ; i++){
            //Вызов Callback функции --------------
            CompletableFuture<Integer> completableFuture = function.thenApply(
                    resultFromFunction -> {
                        System.out.println("Result from function : " + resultFromFunction);
                        return resultFromFunction;
                    }
            );
            //---------------------------------------
            System.out.println(count++);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
