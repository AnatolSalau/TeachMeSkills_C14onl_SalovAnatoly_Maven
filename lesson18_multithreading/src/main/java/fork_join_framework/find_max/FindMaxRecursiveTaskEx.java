package fork_join_framework.find_max;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveTask;

/**
 * Recursion search max value in array
 */
@Slf4j
@AllArgsConstructor
public class FindMaxRecursiveTaskEx extends RecursiveTask<Integer> {
    //left boundary
    private int left;
    //right boundary
    private int right;
    //arr for search
    private int[] arr;

    private int task;

    @Override
    protected Integer compute() {
        // 1. First of all - we need to create quit from recursion
        // (right - left) - length of array, if length <=5 we must search value
        if (right - left <= 5) {
            // Find max in arr
            int currMax = 0;
            //search in all values between left and right values
            for (int i = left; i < right; i++) {
                currMax = Math.max(currMax,arr[i]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            log.info("Quit from recursion, TASK : {}, left is {}, right is {}",this.task, this.left, this.right-1);
            return currMax;
        }
            log.info("Enter in recursion, TASK : {}, left is {}, right is {}",this.task, this.left, this.right-1);
            //If we pasted condition we must create recursion
            // Enter in recursion - split array
            int mid = (left + right) / 2;

            FindMaxRecursiveTaskEx leftTask = new FindMaxRecursiveTaskEx(left,mid,arr, ++task);
            FindMaxRecursiveTaskEx rightTask = new FindMaxRecursiveTaskEx(mid,right,arr, ++task);

            //Put our recursive logic in ExecutorThreadPool
            /*
            leftTask.fork();
            rightTask.fork();

            return Math.max(leftTask.join(),rightTask.join());
            */

            //like Oracle documentation
            leftTask.fork();
            return Math.max(rightTask.compute(),leftTask.join());
    }
}
