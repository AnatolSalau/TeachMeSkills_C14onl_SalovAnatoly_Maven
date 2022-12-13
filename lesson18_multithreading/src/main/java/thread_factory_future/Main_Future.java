package thread_factory_future;

import java.util.concurrent.*;

public class Main_Future {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("START PROGRAM");
        //Create CallableOne
        Callable<Integer> callableOne = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Start work callableOne");
                Integer result = 0;
                for (int i = 0; i < 10; i++) {
                    //Check that CustomThreadFactory is work
                    System.out.println("Thread Name" + Thread.currentThread().getName() + " : " + result);
                    Thread.sleep(100);
                    result +=1;
                }

                System.out.println("End work callableOne");
                return result;
            }
        };
        //Create CallableTwo
        Callable<Integer> callableTwo = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                System.out.println("Start work callableTwo");
                Integer result = 0;
                for (int i = 0; i < 10; i++) {
                    //Check that CustomThreadFactory is work
                    System.out.println("Thread Name" + Thread.currentThread().getName()+ " : " + result);
                    Thread.sleep(100);
                    result +=1;
                }

                System.out.println("End work callableTwo");
                return result;
            }
        };

        //Create CustomThreadFactory
        class CustomThreadFactory implements ThreadFactory {
            public Thread newThread(Runnable r) {
                return new Thread(r, "CALLABLE_THREAD");
            }
        }

        //Create Executors with one thread
        ExecutorService callableExecutorService = Executors.newSingleThreadExecutor(new CustomThreadFactory());

        //Put in that executors two task
        Future<Integer> completableFutureOne = callableExecutorService.submit(callableOne);
        Future<Integer> completableFutureTwo = callableExecutorService.submit(callableTwo);

        //Close ThreadExecutor
        callableExecutorService.shutdown();

        //Run cycle in main thread -that show that CALLABLE_THREAD is work parallel
        Thread.currentThread().setName("Main thread");
        for (int i = 0; i < 20; i++) {
            System.out.println("Thread : " + Thread.currentThread().getName() + " is running");
            Thread.sleep(100);
        }
        System.out.println("Result of two feature: " + completableFutureOne.get() + completableFutureTwo.get());
        System.out.println("END PROGRAM");
    }
}
