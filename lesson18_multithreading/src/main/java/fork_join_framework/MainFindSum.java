package fork_join_framework;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

@Slf4j
public class MainFindSum {
    public static void main(String[] args) {
        //ThreadQuantity
        int threadQuantity = 5;
        //Create array
        int[] arr = new int[10];

        int minPart = arr.length/threadQuantity;
        int sumArr = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
            sumArr += i;
        }
        log.info(Arrays.toString(arr));
        log.info("Summ Arr is {}",sumArr);

        FindSummArrayRecursiveTaskEx findMaxTask = new FindSummArrayRecursiveTaskEx(0, arr.length,arr,threadQuantity, 0, minPart);
        ForkJoinPool forkJoinPool = new ForkJoinPool(threadQuantity);

        //put Task in our ForkJoinPool
        Integer maxInArr = forkJoinPool.invoke(findMaxTask);
        log.info("Summ arr recursive is : {}", maxInArr);
    }
}
