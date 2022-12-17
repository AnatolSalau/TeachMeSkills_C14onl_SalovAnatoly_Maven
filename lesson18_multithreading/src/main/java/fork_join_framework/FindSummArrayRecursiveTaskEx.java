package fork_join_framework;

import lombok.AllArgsConstructor;
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

    private int task;

    public FindSummArrayRecursiveTaskEx(int left, int right, int[] arr, int quantityThreads, int task, int minPart) {
        this.left = left;
        this.right = right;
        this.arr = arr;
        this.minPart = minPart;
        this.task = task;
    }

    @Override
    protected Integer compute() {
        // 1. First of all - we need to create quit from recursion

        if (right - left <= minPart) {
            // Find max in arr
            int currSumm = 0;
            for (int i = 0; i < right; i++) {
                currSumm += arr[i];
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {}
            }
            log.info("Quit from recursion, task is {}, min part is {}, left is {}, right is {}", this.task, this.minPart, this.left, this.right);
            return currSumm;
        }   else {
            log.info("Enter in recursion, task is {}, min part is {}, left is {}, right is {}", this.task, this.minPart, this.left, this.right);
            // Enter in recursion - split array
            int mid = (left + right) / 2;

            FindSummArrayRecursiveTaskEx leftTask = new FindSummArrayRecursiveTaskEx(left, mid-1, arr, minPart, ++ task, minPart);
            FindSummArrayRecursiveTaskEx rightTask = new FindSummArrayRecursiveTaskEx(mid, right, arr, minPart, ++ task, minPart);

            //Put our recursive logic in ExecutorThreadPool
            leftTask.fork();
            rightTask.fork();

            return leftTask.join() + rightTask.join();
        }
            //like Oracle documentation
            /*
            leftTask.fork();
            return rightTask.compute() + leftTask.join();
            */
    }
}
