package custom_executor_1;

// Class 3
// Helper class extending Runnable interface
class Execution implements Runnable {
    private boolean isStopped = false;
    private Thread currentThread;
    // Method 1 of  this class
    void executeMyMethod() {
        // At start the current capacity will be 0
        // The another capacity is the number of threads we
        // want to create so we will increase the current
        // capacity count after creating each thread it
        // means that we will create the threads if the
        // current capacity is less than capacity passed by
        // us i.e number of threads we want to create.

        // In this case 3 threads will get created
        if (MyFixedThreadPoolImpl.currentCapacity
                < MyFixedThreadPoolImpl.capacity) {
            MyFixedThreadPoolImpl.currentCapacity++;

            // Creating object of Thread class
            currentThread = new Thread(new Execution());

            // Starting the thread
            currentThread.start();
        }
    }

    // Method 2 of this class
    // @Override
    public void run()
    {

        // Till it is true
        while (!isStopped) {

            // Here we are fetching the tasks from the
            // linkedblocking queue
            // which we have submitted using execute method
            // and executing them
            if (MyFixedThreadPoolImpl.linkedTaskBlockingQueue.size()
                    != 0) {
                MyFixedThreadPoolImpl.linkedTaskBlockingQueue.poll()
                        .run();
            }
        }
    }

    public  void stop() {
        this.isStopped = true;
    }
}