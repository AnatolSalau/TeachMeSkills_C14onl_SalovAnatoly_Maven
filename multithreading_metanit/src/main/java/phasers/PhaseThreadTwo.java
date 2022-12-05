package phasers;

import java.util.concurrent.Phaser;

public class PhaseThreadTwo {
    String name;
    Phaser phaser;

    public PhaseThreadTwo(String name, Phaser phaser) {
        this.name = name;
        this.phaser = phaser;
        //.register() - registers the member that is running the phases
        // and returns the number of the current phase - usually phase 0
        System.out.println("PhaseThreadTwo was register");
        phaser.register();
    }

    public void run() {
        try {
            System.out.println(name + " execute phase : " + phaser.getPhase());
        /*+
        .arriveAndAwaitAdvance() - indicates that the participant has completed the phase and returns
        the number of the current phase. Causes the phaser to wait for
        all other participants to complete the phase
         */
            phaser.arriveAndAwaitAdvance();
            Thread.sleep(200);

            System.out.println(name + " execute phase : " + phaser.getPhase());
            //indicates that second phase is complete
            phaser.arriveAndAwaitAdvance();
            Thread.sleep(200);

            System.out.println(name + " execute phase : " + phaser.getPhase());
            /*
             * informs about the completion of all phases by the participant
             *  and removes him from registration. Returns the current phase number,
             *  or a negative number if the Phaser has completed its work*/
            Thread.sleep(200);
            System.out.println(name + " phaser PhaseThreadTwo deregister");
            phaser.arriveAndDeregister();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
