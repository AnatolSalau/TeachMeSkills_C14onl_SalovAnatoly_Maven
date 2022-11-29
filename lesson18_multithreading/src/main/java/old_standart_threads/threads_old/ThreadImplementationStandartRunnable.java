package old_standart_threads.threads_old;

public class ThreadImplementationStandartRunnable implements Runnable{
    private boolean isRun = true;

    public void setRun(boolean run) {
        isRun = run;
    }

    @Override
    public void run() {
        //Бесконечный поток
        while (isRun) {
            int sleep = 1_000;
            try {
                //Задержка 2000 мсек
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Hello from MyThreadImplements sleep:" +  sleep);
        }
    }
}
