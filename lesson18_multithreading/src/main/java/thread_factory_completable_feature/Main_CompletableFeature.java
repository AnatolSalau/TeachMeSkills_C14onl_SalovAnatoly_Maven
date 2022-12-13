package thread_factory_completable_feature;

import java.util.concurrent.*;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

public class Main_CompletableFeature {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("START PROGRAM");
        //Create CallableOne
        IntSupplier intSupplierOne = new IntSupplier() {
            @Override
            public int getAsInt() {
                System.out.println("Start work intSupplierOne");
                Integer result = 0;
                for (int i = 0; i < 10; i++) {
                    //Check that CustomThreadFactory is work
                    System.out.println("Thread Name" + Thread.currentThread().getName()+ " : " + result);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    result +=1;
                }

                System.out.println("End work intSupplierOne");
                return result;
            }
        };
        //Create CallableTwo
        IntSupplier intSupplierTwo = new IntSupplier() {
            @Override
            public int getAsInt() {
                System.out.println("Start work intSupplierTwo");
                Integer result = 0;
                for (int i = 0; i < 10; i++) {
                    //Check that CustomThreadFactory is work
                    System.out.println("Thread Name" + Thread.currentThread().getName()+ " : " + result);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    result +=1;
                }

                System.out.println("End work intSupplierTwo");
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
        CompletableFuture<Integer> completableFutureOne = CompletableFuture.supplyAsync(intSupplierOne::getAsInt, callableExecutorService);
        CompletableFuture<Integer> completableFutureTwo = CompletableFuture.supplyAsync(intSupplierTwo::getAsInt, callableExecutorService);

        //Close ThreadExecutor
        callableExecutorService.shutdown();

        //Run cycle in main thread -that show that CALLABLE_THREAD is work parallel
        Thread.currentThread().setName("Main thread");
        for (int i = 0; i < 20; i++) {
            System.out.println("Thread : " + Thread.currentThread().getName() + " is running");
            Thread.sleep(100);
        }
        System.out.println("Result of two feature: " + ( completableFutureOne.get() + completableFutureTwo.get() ) );
        System.out.println("END PROGRAM");
    }
}
