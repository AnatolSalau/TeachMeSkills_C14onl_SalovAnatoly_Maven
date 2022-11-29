package sinchronizing_threads;

public class Main_SinchronizingThreads {
    public static void main(String[] args) throws InterruptedException {


        Value value = new Value(0);
        long count = 1;
        PrintSynchro printSynchro = new PrintSynchro();

        //Synchronized by Value
        ChangeValueThreadOne runnableThreadOne = new ChangeValueThreadOne(value,count,printSynchro);
        ChangeValueThreadTwo runnableThreadTwo = new ChangeValueThreadTwo(value,count,printSynchro);

        Thread threadOne = new Thread(runnableThreadOne);
        Thread threadTwo = new Thread(runnableThreadTwo);

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();
        System.out.println("Value is : ");
        System.out.println(value.getValue());
    }
}
