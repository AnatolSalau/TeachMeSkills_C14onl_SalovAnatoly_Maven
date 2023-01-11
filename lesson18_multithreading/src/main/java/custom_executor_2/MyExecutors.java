package custom_executor_2;

// Class 1
// Helper class
public class MyExecutors {
    // Member variables of this class
    int capacity;

    // Passing the number of threads that
    // will be in the thread pool
    static MyExecutorService
    myNewFixedThreadPool(int capacity)
    {

        return new MyThreadPool(capacity);
    }
}
