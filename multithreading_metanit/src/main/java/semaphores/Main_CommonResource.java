package semaphores;

import java.util.concurrent.Semaphore;

public class Main_CommonResource {
    public static void main(String[] args) {


        Semaphore sem = new Semaphore(1);
        CommonResource res = new CommonResource();

        CountThread countThreadOne = new CountThread(res, sem,"Thread One");
        CountThread countThreadTwo = new CountThread(res, sem,"Thread Two");
        CountThread countThreadThree = new CountThread(res, sem,"Thread Three");
        CountThread countThreadFour = new CountThread(res, sem,"Thread Four");

        Thread threadOne = new Thread(countThreadOne::increment);
        Thread threadTwo = new Thread(countThreadTwo::increment);
        Thread threadThree = new Thread(countThreadThree::increment);
        Thread threadFour = new Thread(countThreadFour::increment);

        threadOne.start();
        threadTwo.start();
        threadThree.start();
        threadFour.start();
    }
}
