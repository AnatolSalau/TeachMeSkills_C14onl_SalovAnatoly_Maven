package fork_join;

import java.util.Date;
import java.util.concurrent.RecursiveTask;

public class RecursiveTaskService extends RecursiveTask<Long> {
    private final long from, to;
    private final long NUM_OF_OPERATIONS = 8_000_000_000_0L;

    public RecursiveTaskService(long from, long to) {
        this.from = from;
        this.to = to;
    }

    final int quantityThread = Runtime.getRuntime().availableProcessors();
    @Override
    protected Long compute() {
        if( (to - from) <=NUM_OF_OPERATIONS/quantityThread) {
            System.out.println(new Date());
            long result = from;
            for (long i = from; i < to; i++) {
                result += 1L;
            }
            System.out.println(new Date());
            System.out.println(result);
            return result;
        } else {
            long middle = (to + from)/2;
            //FirstHalf
            RecursiveTaskService firstHalf = new RecursiveTaskService(from, middle);
            firstHalf.fork();
            //SecondHalf
            RecursiveTaskService secondHalf = new RecursiveTaskService(middle +1, to);
            secondHalf.fork();
            long secondValue = secondHalf.compute();
            return firstHalf.join() + secondValue;
        }
    }
}
