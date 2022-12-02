package semaphores.philosopher_task;

import java.util.concurrent.Semaphore;

public class Main_Philosofers {
    public static void main(String[] args) throws InterruptedException {
        Semaphore sem = new Semaphore(2);

        Philosofer philosofer1 = new Philosofer(sem,1);
        Philosofer philosofer2 = new Philosofer(sem,2);
        Philosofer philosofer3 = new Philosofer(sem,3);
        Philosofer philosofer4 = new Philosofer(sem,4);
        Philosofer philosofer5 = new Philosofer(sem,5);
        Philosofer philosofer6 = new Philosofer(sem,6);

        Thread philosoferThread1 = new Thread(philosofer1::eat);
        Thread philosoferThread2 = new Thread(philosofer2::eat);
        Thread philosoferThread3 = new Thread(philosofer3::eat);
        Thread philosoferThread4 = new Thread(philosofer4::eat);
        Thread philosoferThread5 = new Thread(philosofer5::eat);
        Thread philosoferThread6 = new Thread(philosofer6::eat);

        philosoferThread1.start();
        philosoferThread2.start();
        philosoferThread3.start();
        philosoferThread4.start();
        philosoferThread5.start();
        philosoferThread6.start();

        philosoferThread1.join();
        philosoferThread2.join();
        philosoferThread3.join();
        philosoferThread4.join();
        philosoferThread5.join();
        philosoferThread6.join();

        System.out.println("The end of philosopher task");

    }
}
