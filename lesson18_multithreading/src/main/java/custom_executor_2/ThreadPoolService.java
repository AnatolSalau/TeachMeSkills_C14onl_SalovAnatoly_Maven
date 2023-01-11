package custom_executor_2;

// Class 1
// Helper class
//Create thread pols like Executors class in Java
public class ThreadPoolService {

    // Passing the number of threads that
    // will be in the thread pool
    static FixedThreadPool myNewFixedThreadPool(int capacity)
    {
        return new MyFixedThreadPoolImpl(capacity);
    }
}
