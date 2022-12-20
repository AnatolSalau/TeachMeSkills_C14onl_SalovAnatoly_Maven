package fork_join_framework.find_summ;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;

@Slf4j
public class MainFindSum {
    public static void main(String[] args) {
        //ThreadQuantity
        int threadQuantity = 10;
        //Create array
        int[] arr = new int[10000];

        //Minimum length of part array for summ
        int minLengthOfPart = 1000;

        //Summ for checking between simple loop and recursion
        int sumArr = 0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
            sumArr += i;
        }
        //log.info(Arrays.toString(arr));
        log.info("Summ Arr is {}",sumArr);

        FindSummArrayRecursiveTaskEx findMaxTask =
                new FindSummArrayRecursiveTaskEx(0, arr.length-1,arr, minLengthOfPart);
        ForkJoinPool forkJoinPool = new ForkJoinPool(threadQuantity);

        //put Task in our ForkJoinPool
        Integer maxInArr = forkJoinPool.invoke(findMaxTask);
        log.info("Summ arr recursive is : {}", maxInArr);
        log.info("Summ Arr is {}",sumArr);
    }
}
