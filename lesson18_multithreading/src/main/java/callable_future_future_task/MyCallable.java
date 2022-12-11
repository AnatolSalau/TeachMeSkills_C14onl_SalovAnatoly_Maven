package callable_future_future_task;

import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        int j = 0;
        System.out.println("MyCallable start");
        for (int i = 0; i < 10; i++) {
            System.out.println(i + "from myCallable");
            Thread.sleep(200);
            j += 1;
        }
        System.out.println("MyCallable finish");
        return j;
    }
}
