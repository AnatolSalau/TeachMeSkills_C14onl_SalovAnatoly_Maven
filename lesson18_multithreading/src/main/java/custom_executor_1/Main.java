package custom_executor_1;

public class Main {
    public static void main(String[] args) {
        //Create customThreadPool
        CustomThreadPool customThreadPool =
                new CustomThreadPool(3,10);
        //Create tasks
        for (int i = 0; i < 10 ; i++) {
            int taskNumber = i;
            //Create logic in task
            Runnable task = () -> {
                    System.out.println(
                            Thread.currentThread().getName() + ", task : " + taskNumber
                    );
            };
            customThreadPool.execute(task);
        }
        customThreadPool.waitUntilAllTasksFinished();
        customThreadPool.stop();
    }
}
