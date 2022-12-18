package fork_join_framework.find_max;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

@Slf4j
public class MainFindMax {
    public static void main(String[] args) {
        //Create array
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        log.info(Arrays.toString(arr));

        FindMaxRecursiveTaskEx findMaxTask = new FindMaxRecursiveTaskEx(0, arr.length,arr,1);
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);

        //put Task in our ForkJoinPool
        Integer maxInArr = forkJoinPool.invoke(findMaxTask);
        log.info("Max in array : {}", maxInArr);

    }
}
