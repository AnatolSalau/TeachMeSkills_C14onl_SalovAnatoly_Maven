package fork_join_framework.find_summ;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.RecursiveTask;

/**
 *
 */
@Slf4j
public class FindSummArrayRecursiveTaskEx extends RecursiveTask<Integer> {
    private int left;
    private int right;
    private int[] arr;
    private int minPart;

    public FindSummArrayRecursiveTaskEx(int left, int right, int[] arr, int quantityThreads, int minPart) {
        this.left = left;
        this.right = right;
        this.arr = arr;
        this.minPart = minPart;
    }

    @Override
    protected Integer compute() {
        // 1. First of all - we need to create quit from recursion
        if (right - left <= minPart) {
            //log.info("left is {}, right is {}", this.minPart, this.left, this.right);
            // Find max in arr
            int currSumm = 0;
            //log.info("Current summ before cycle  is : {}", currSumm);
            for (int i = left; i <= right; i++) {
                /*
                log.info("currSumm {} += {} ;", currSumm,arr[i]);
                */
                currSumm += arr[i];
                /*
                log.info("currSumm is {}",currSumm);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
                */
            }
            //log.info("Current summ is : {}, left is {}, right is {}", currSumm, this.left, this.right);
            return currSumm;
        }   else {
            // Enter in recursion - split array
            int mid = (left + right) / 2;

            FindSummArrayRecursiveTaskEx leftTask = new FindSummArrayRecursiveTaskEx(left, mid, arr, minPart, minPart);
            FindSummArrayRecursiveTaskEx rightTask = new FindSummArrayRecursiveTaskEx(mid + 1, right, arr, minPart, minPart);

            //Put our recursive logic in ExecutorThreadPool
            leftTask.fork();
            rightTask.fork();
            return leftTask.join() + rightTask.join();

            //like Oracle documentation
/*            leftTask.fork();
            return rightTask.compute() + leftTask.join();*/
        }
    }
}
