package lock_reenrantlock;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

@AllArgsConstructor
@NoArgsConstructor
public class PrintSynchroReadWriteLockThread {

    private ReadWriteLockCollection readWriteLockCollection = new ReadWriteLockCollection();
    
    public void print() {

    }

}
