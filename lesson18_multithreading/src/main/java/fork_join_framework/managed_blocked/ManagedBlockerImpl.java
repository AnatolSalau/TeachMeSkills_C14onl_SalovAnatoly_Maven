package fork_join_framework.managed_blocked;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ManagedBlockerImpl implements ForkJoinPool.ManagedBlocker {

    final ReentrantLock rtlock;
    boolean isLocked = false;

    public ManagedBlockerImpl(ReentrantLock rtlock) {
        this.rtlock = rtlock;
    }

    public boolean block() {
        if (!isLocked){
            rtlock.lock();
        }
        return true;
    }
    public boolean isReleasable() {
        return isLocked || (isLocked = rtlock.tryLock());
    }
}
