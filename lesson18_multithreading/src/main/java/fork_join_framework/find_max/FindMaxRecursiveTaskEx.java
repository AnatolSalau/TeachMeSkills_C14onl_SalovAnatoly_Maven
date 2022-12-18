package fork_join_framework.find_max;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveTask;

/**
 *
 */
@Slf4j
@AllArgsConstructor
public class FindMaxRecursiveTaskEx extends RecursiveTask<Integer> {
    private int left;
    private int right;
    private int[] arr;

    private int task;

    @Override
    protected Integer compute() {
        // 1. First of all - we need to create quit from recursion
        if (right - left <= 5) {

            // Find max in arr
            int currMax = 0;
            for (int i = 0; i < right; i++) {
                currMax = Math.max(currMax,arr[i]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            log.info("Quit from recursion, TASK : {}, left is {}, right is {}",this.task, this.left, this.right);
            return currMax;
        }
            log.info("Enter in recursion, TASK : {}, left is {}, right is {}",this.task, this.left, this.right);
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
