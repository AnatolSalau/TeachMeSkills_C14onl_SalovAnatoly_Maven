package custom_executor_3.app;

import custom_executor_3.pool.ThreadPool;

public class Main {
    public static void main(String[] args) throws InterruptedException {
    	//create queue size - 3
    	//Number of threads - 4
        ThreadPool threadPool = new ThreadPool(3,4);
        //Created 15 Tasks and submit to pool
        for(int taskNumber = 1 ; taskNumber <= 7; taskNumber++) {
            TestTask task = new TestTask(taskNumber);
            threadPool.submitTask(task);
        }
    }
}
