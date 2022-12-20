package fork_join_framework.find_summ;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveTask;

/**
 * Find summ all elements array by recursion by ForkJoinFramework
 */
@Slf4j
public class FindSummArrayRecursiveTaskEx extends RecursiveTask<Integer> {
    //left boundary
    private int left;
    //right boundary
    private int right;
    //
    private int[] arr;
    //min length of part for summ in array
    private int minLengthOfPart;

    public FindSummArrayRecursiveTaskEx(int left, int right, int[] arr, int minLengthOfPart) {
        this.left = left;
        this.right = right;
        this.arr = arr;
        this.minLengthOfPart = minLengthOfPart;
    }

    @Override
    protected Integer compute() {
        // 1. First of all - we need to create quit from recursion
        //
        if (right - left <= minLengthOfPart) {
            System.out.println(Thread.currentThread().getName());
            // Find max in arr
            int currSumm = 0;
            for (int i = left; i <= right; i++) {
                currSumm += arr[i];
            }
            return currSumm;
        }   else {
            // if we more than minimal part - we must create recursion
            // Enter in recursion - split array
            int mid = (left + right) / 2;

            FindSummArrayRecursiveTaskEx leftTask = new FindSummArrayRecursiveTaskEx(left, mid, arr, minLengthOfPart);
            FindSummArrayRecursiveTaskEx rightTask = new FindSummArrayRecursiveTaskEx(mid + 1, right, arr, minLengthOfPart);

            //Put our recursive logic in ExecutorThreadPool
            leftTask.fork();
            rightTask.fork();
            return leftTask.join() + rightTask.join();

            //like Oracle documentation
/*          leftTask.fork();
            return rightTask.compute() + leftTask.join();*/
        }
    }
}
