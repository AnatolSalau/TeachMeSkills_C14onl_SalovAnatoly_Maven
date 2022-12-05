package phasers;

import java.util.concurrent.Phaser;

public class Main_Phasers {
    public static void main(String[] args)  {

        Phaser phaser = new Phaser(1);

        PhaseThreadOne phaseThreadOne = new PhaseThreadOne(
                "phaseThreadOne", phaser);
        PhaseThreadTwo phaseThreadTwo = new PhaseThreadTwo(
                "phaseThreadTwo", phaser);

        Thread threadOne = new Thread(phaseThreadOne::run);
        Thread threadTwo = new Thread(phaseThreadTwo::run);

        threadOne.start();
        threadTwo.start();


        // end the phase 0 for all participants
        int phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("MAIN: phase : " + phase + " complete");

        // end the phase 1 for all participants
        phase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("MAIN: phase : " + phase + " complete");
    }
}
