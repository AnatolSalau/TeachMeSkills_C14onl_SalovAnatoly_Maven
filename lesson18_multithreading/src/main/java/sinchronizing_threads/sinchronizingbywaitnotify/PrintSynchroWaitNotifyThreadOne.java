package sinchronizing_threads.sinchronizingbywaitnotify;

import lombok.AllArgsConstructor;
import sinchronizing_threads.PrintSynchro;

@AllArgsConstructor
public class PrintSynchroWaitNotifyThreadOne implements Runnable {

    PrintSynchroWaitNotify printSynchroWaitNotify;

    @Override
    public void run() {
        try {
                printSynchroWaitNotify.print();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
