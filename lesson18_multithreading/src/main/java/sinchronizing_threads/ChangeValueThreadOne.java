package sinchronizing_threads;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ChangeValueThreadOne implements Runnable {
    private final Value value;
    private long count;
    PrintSynchro printSynchro;

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            synchronized (value) {
                value.setValue(value.getValue()+1);
            }
        }

            try {
                printSynchro.print();
            } catch (InterruptedException e) {
                //call synchronized method
                e.printStackTrace();
            }

    }
}
