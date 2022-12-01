package old_standart_threads;

import old_standart_threads.threads_old.ThreadExtendsStandardThread;
import old_standart_threads.threads_old.ThreadImplementationStandartRunnable;

import java.lang.management.ManagementFactory;

public class Main_OldStandartThreads {

    public static void main(String[] args) {
        ManagementFactory.getPlatformMBeanServer();
        //Thread extends Thread
        ThreadExtendsStandardThread threadExtendsStandardThread = new ThreadExtendsStandardThread();

        threadExtendsStandardThread.start();

        threadExtendsStandardThread.setRun(false);

        //Thread implements Thread
        Runnable threadImplementationStandartRunnable = new ThreadImplementationStandartRunnable();
        Thread threadImplStandardRunnable = new Thread(threadImplementationStandartRunnable);
    }
}
