package old_standart_threads.threads_old;

public class ThreadExtendsStandardThread extends Thread{
    //We must set isRun field by volatile because
    //compiler can put ihis variable in CPU cache
    private volatile boolean isRun = true;
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(true) {
            System.out.println("Hello from my ThreadExtend");

            if (!isRun) {
                System.out.println("Break");
                break;
            }
        }
        System.out.println("After cycle");
    }

    public void setRun(boolean run) {
        isRun = run;
    }
}
