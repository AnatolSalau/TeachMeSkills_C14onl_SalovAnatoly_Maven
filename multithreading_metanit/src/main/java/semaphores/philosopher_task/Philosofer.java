package semaphores.philosopher_task;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class Philosofer {
    // semaphor
    private Semaphore sem;
    // quantity of meals
    private int num = 0;
    // number of philosofer
    private int id;
    private List<String> meals = new ArrayList<>();

    public Philosofer(Semaphore sem, int id) {
        this.sem = sem;
        this.id = id;
        //add meals
        meals.add("eats");
        meals.add("drink tea");
        meals.add("chat");
    }

    public void eat() {
            try {

                //Philosofer must eat 3 time
                while (num < meals.size()) {
                    sem.acquire();
                    System.out.println("Philosopher : " + id + " " + "sits down at the table");
                    System.out.println("Philosopher : " + id + " " + meals.get(num));
                    num++;
                    System.out.println("Philosopher : " + id + " " + "leaves the table");
                    sem.release();
                    if(num == meals.size()) {
                        System.out.println("Philosopher : " + id + " " + "go out from restaurant");
                        break;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}
