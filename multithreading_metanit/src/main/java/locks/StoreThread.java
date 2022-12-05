package locks;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//Class Store - store manufactured products
@Getter
@Setter
public class StoreThread extends Thread {
    private volatile int productQuantity = 0;
    private final ReentrantLock lock;
    private final Condition condition;
    private volatile boolean isOpen = true;
    private Thread currentThread;

    public StoreThread() {
        //initialize lock
        this.lock = new ReentrantLock();
        //get condition from lock
        this.condition = lock.newCondition();
        new Thread(this::run,"STORE_THREAD").start();
        currentThread = Thread.currentThread();
    }

    @Override
    public void run() {
        while (isOpen) {
            if(!isOpen) {
                break;
            }
        }
    }

    public void getProductAutomatic() {
        lock.lock();
        try {
            //await while
            while (productQuantity <1) {
                condition.await();
            }
            setProductQuantity(this.getProductQuantity() -1);

            System.out.println("Customer buy 1 product");
            System.out.println("Product quantity: " + productQuantity);

            condition.signalAll();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void putProductAutomatic() {
        lock.lock();
        try {
            //while store has more than 3 products we await
            while (productQuantity >=3) {
                condition.await();
            }
            setProductQuantity(getProductQuantity()+1);
            System.out.println("Concumer add product");
            System.out.println("Product quantity: " + productQuantity);
            // start
            condition.signalAll();

        } catch (Exception ex) {
            ex.printStackTrace();

        } finally {
            lock.unlock();
        }
    }
}
