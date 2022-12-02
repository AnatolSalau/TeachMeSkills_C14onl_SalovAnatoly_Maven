package semaphores;

import lombok.AllArgsConstructor;

import java.util.concurrent.Semaphore;

@AllArgsConstructor
public class CountThread {
    private CommonResource res;
    private Semaphore sem;
    private String name;

    public void increment()  {
        try {
            System.out.println(name + " waiting for permission");
            //get permission from semaphore
            sem.acquire();
            for (int i = 0; i < 5; i++) {
                System.out.println(this.name + " : " + res.getCount());
                res.setCount(res.getCount()+1);
            }


            System.out.println(name + " releases permission");
            sem.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
