package old_standart_threads.threads_old;

public class ThreadImplementationStandartRunnable implements Runnable{
    private boolean isRun = true;

    public void setRun(boolean run) {
        isRun = run;
    }

    @Override
    public void run() {

        while (isRun) {
            int sleep = 1_000;
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello from MyThreadImplements sleep:" +  sleep);
        }
    }
}
