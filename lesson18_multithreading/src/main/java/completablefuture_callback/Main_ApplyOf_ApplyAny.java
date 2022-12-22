package completablefuture_callback;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**+
 * CompletableFuture allOff - run result after the end each future in the list
 */
public class Main_ApplyOf_ApplyAny {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            list.add(i);
        }
        long start = System.currentTimeMillis();
        //Create list - completableFuture for each value in list
        List<CompletableFuture<Integer>> sqrFutureList = list.stream()
                .map(integer -> getSqr(integer))
                .collect(Collectors.toList());

        //Create AllOCompletableFuture
        CompletableFuture<Void> allOfSqrFutureList = CompletableFuture.allOf(
                sqrFutureList.toArray(new CompletableFuture[0])
        );

        //Run each task in allOfSqrFutureList
        CompletableFuture<List<Integer>> allValues = allOfSqrFutureList.thenApply(
                sqr -> sqrFutureList.stream()
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())
        );
        //Print values
        allValues.get().forEach(System.out::println);

        long end = System.currentTimeMillis();
        System.out.println("end - start : " + (end - start));
    }

    public static CompletableFuture<Integer> getSqr(Integer integer) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return integer*integer;
        });
    }
}
