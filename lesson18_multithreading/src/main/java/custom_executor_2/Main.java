package custom_executor_2;

// Class 5
// Main Class
public class Main {
    // Main driver method
    public static void main(String[] args)
    {
        // Getting the object of MyExcutorService by using
        //  the factory method myNewFixedThreadPool

        // Passing number of threads as 3
        FixedThreadPool service
                = ThreadPoolService.myNewFixedThreadPool(3);

        for (int i = 0; i < 20; i++) {

            // Creating 20 tasks and passing them to execute
            service.execute(new Mytask());
        }

    }
}
