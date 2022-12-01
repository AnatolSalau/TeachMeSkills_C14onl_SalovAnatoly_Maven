package sinchronizing_threads.sinchronizingbywaitnotify;

import lombok.AllArgsConstructor;
import sinchronizing_threads.PrintSynchro;
import sinchronizing_threads.Value;

@AllArgsConstructor
public class PrintSynchroWaitNotifyThreadTwo implements Runnable {

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
