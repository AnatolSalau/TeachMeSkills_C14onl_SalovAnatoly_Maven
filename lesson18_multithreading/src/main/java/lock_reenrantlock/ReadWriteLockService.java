package lock_reenrantlock;

public class ReadWriteLockService {

    private ReadWriteLockCollection readWriteLockCollection= new ReadWriteLockCollection();

    public void write() {
            readWriteLockCollection.write();
    }
    public void read() {
            readWriteLockCollection.read();
    }
}
