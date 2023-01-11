package custom_executor_2;

import java.util.concurrent.LinkedBlockingQueue;
// Class 2
// ThreadPool implementation
class MyFixedThreadPoolImpl implements ThreadPool {

    // Member variables of this class
    static int capacity;
    static int currentCapacity;
    Execution execution;

    // Creating object of LinkedBlockingQueue class
    // Declaring object of type Runnable
    static LinkedBlockingQueue<Runnable> linkedTaskBlockingQueue;

    // Member variables of this class


    // Method 1
    public MyFixedThreadPoolImpl(int capacity)
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
        execution = new Execution();
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
        execution.executeMyMethod();
    }
}
