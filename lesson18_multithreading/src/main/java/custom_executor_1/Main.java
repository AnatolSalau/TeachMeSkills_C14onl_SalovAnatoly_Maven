package custom_executor_1;

import java.util.concurrent.TimeUnit;

// Class 5
// Main Class
public class Main {
    // Main driver method
    public static void main(String[] args) throws InterruptedException {
        // Getting the object of MyExcutorService by using
        //  the factory method myNewFixedThreadPool

        // Passing number of threads as 3
        ThreadPool service
                = ThreadPoolService.myNewFixedThreadPool(3);

        for (int i = 0; i < 5; i++) {

            // Creating 20 tasks and passing them to execute
            service.execute(new Mytask());
        }
        TimeUnit.SECONDS.sleep(5);
        service.stop();
    }
}
