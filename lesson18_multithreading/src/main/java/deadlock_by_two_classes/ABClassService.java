package deadlock_by_two_classes;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ABClassService {
    private AClass aClass;
    private BClass bClass;

    public void inc() throws InterruptedException {
        synchronized (aClass) {
            System.out.println("inc aClass");
            Thread.sleep(500);
            System.out.println("inc bClass");
            synchronized (bClass) {

            }
        }

    }

    public void dec() throws InterruptedException {
        synchronized (bClass) {
            System.out.println("dec bClass");
            Thread.sleep(500);
            System.out.println("dec aClass");
            synchronized (aClass) {

            }
        }
    }
}
