package custom_executor_1;

import java.util.concurrent.LinkedBlockingQueue;

// Interface
// Custom interface for which contains execute method
interface MyExecutorService {

    // Method
    void execute(Runnable r);
}

// Class 1
// Helper class
class MyExecutors {

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

// Class 2
// Helper class extending to MyExecutorService interface
class MyThreadPool implements MyExecutorService {

    // Member variables of this class
    static int capacity;
    static int currentCapacity;

    // Creating object of LinkedBlockingQueue class
    // Declaring object of type Runnable
    static LinkedBlockingQueue<Runnable>
            linkedTaskBlockingQueue;

    // Member variables of this class
    Execution e;

    // Method 1
    public MyThreadPool(int capacity)
    {

        // Member variables of this class

        // this keyword refers to current instance itself
        this.capacity = capacity;
        currentCapacity = 0;

        // Creating a linked blocking queue which will block
        // if its empty
        // and it will perform thread safe operation.
        linkedTaskBlockingQueue
                = new LinkedBlockingQueue<Runnable>();

        // Creating the object of execution class
        e = new Execution();
    }

    // Method 2
    // @Override
    public void execute(Runnable r)
    {

        // Declaring and adding tasks to
        // blocking queue using add() method
        linkedTaskBlockingQueue.add(r);

        // executeMyMethod() method of Execution class
        // which will execute the tasks
        e.executeMyMethod();
    }
}

// Class 3
// Helper class extending Runnable interface
class Execution implements Runnable {

    // Method 1 of  this class
    void executeMyMethod()
    {

        // At start the current capacity will be 0
        // The another capacity is the number of threads we
        // want to create so we will increase the current
        // capacity count after creating each thread it
        // means that we will create the threads if the
        // current capacity is less than capacity passed by
        // us i.e number of threads we want to create.

        // In this case 3 threads will get created
        if (MyThreadPool.currentCapacity
                < MyThreadPool.capacity) {
            MyThreadPool.currentCapacity++;

            // Creating object of Thread class
            Thread t = new Thread(new Execution());

            // Starting the thread
            t.start();
        }
    }

    // Method 2 of this class
    // @Override
    public void run()
    {

        // Till it is true
        while (true) {

            // Here we are fetching the tasks from the
            // linkedblocking queue
            // which we have submitted using execute method
            // and executing them
            if (MyThreadPool.linkedTaskBlockingQueue.size()
                    != 0) {
                MyThreadPool.linkedTaskBlockingQueue.poll()
                        .run();
            }
        }
    }
}

// Class 4
// Helper class
// Here we are creating a simple task
// which is printing current thread name
class Mytask implements Runnable {

    // Method 1 of this class
    // @Override
    public void run()
    {

        // Try block to check for exceptions
        try {

            // Making thread to pause fo a second
            // using sleep() method
            Thread.sleep(1000);
        }

        // Catch block to check for exceptions
        catch (InterruptedException e) {

            // Print the exception scaling ith line number
            // using printStackTrace() method
            e.printStackTrace();
        }

        // Print and display the current thread using
        // currentThread() method by getting thread name
        // using getName() method
        System.out.println(
                "Current Thread :-> "
                        + Thread.currentThread().getName());
    }
}

// Class 5
// Main Class
public class ExecutorServiceCustom {
    // Main driver method
    public static void main(String[] args)
    {
        // Getting the object of MyExcutorService by using
        //  the factory method myNewFixedThreadPool

        // Passing number of threads as 3
        MyExecutorService service
                = MyExecutors.myNewFixedThreadPool(3);

        for (int i = 0; i < 20; i++) {

            // Creating 20 tasks and passing them to execute
            service.execute(new Mytask());
        }

        Runnable runnableTask = null;
    }
}