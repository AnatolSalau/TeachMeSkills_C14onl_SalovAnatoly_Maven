package old_standart_threads;

import old_standart_threads.threads_old.ThreadExtendsStandardThread;
import old_standart_threads.threads_old.ThreadImplementationStandartRunnable;

public class Main_OldStandartThreads {

    public static void main(String[] args) {
        //Thread extends Thread
        ThreadExtendsStandardThread threadExtendsStandardThread = new ThreadExtendsStandardThread();

        threadExtendsStandardThread.start();

        threadExtendsStandardThread.setRun(false);

        //Thread implements Thread
        Runnable threadImplementationStandartRunnable = new ThreadImplementationStandartRunnable();
        Thread threadImplStandardRunnable = new Thread(threadImplementationStandartRunnable);
    }
}
