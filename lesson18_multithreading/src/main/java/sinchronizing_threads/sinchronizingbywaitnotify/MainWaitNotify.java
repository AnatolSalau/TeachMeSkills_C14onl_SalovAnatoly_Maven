package sinchronizing_threads.sinchronizingbywaitnotify;

public class MainWaitNotify {
    public static void main(String[] args) {
        PrintSynchroWaitNotify printSynchroWaitNotify = new PrintSynchroWaitNotify();

        PrintSynchroWaitNotifyThreadOne printSynchroWaitNotifyThreadOne =
                new PrintSynchroWaitNotifyThreadOne(printSynchroWaitNotify);
        PrintSynchroWaitNotifyThreadTwo printSynchroWaitNotifyThreadTwo =
                new PrintSynchroWaitNotifyThreadTwo(printSynchroWaitNotify);

        Thread threadOne = new Thread(printSynchroWaitNotifyThreadOne);
        Thread threadTwo = new Thread(printSynchroWaitNotifyThreadTwo);

        threadOne.start();
        threadTwo.start();

    }
}
