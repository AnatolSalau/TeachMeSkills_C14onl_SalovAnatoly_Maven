package deadlock_by_two_classes;

import java.lang.management.ManagementFactory;

public class MainTwoClassesCynchro {

    public static void main(String[] args) {

        ManagementFactory.getPlatformMBeanServer();



        ABClassService abClassService =
                new ABClassService(new AClass(), new BClass());

        Thread threadOne = new Thread(
                () -> {
                    try {
                        abClassService.inc();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
        );

        Thread threadTwo = new Thread(
                () -> {
                    try {
                        abClassService.dec();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
        );

        threadOne.start();
        threadTwo.start();

    }

}
