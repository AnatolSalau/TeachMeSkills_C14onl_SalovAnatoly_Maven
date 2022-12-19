package fork_join_framework.work_pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.locks.ReentrantLock;

class ManagedLocker implements ForkJoinPool.ManagedBlocker {
    final ReentrantLock lock;
    boolean hasLock = false;
    ManagedLocker(ReentrantLock lock) {
        this.lock = lock;
    }
    public boolean block() {
        if (!hasLock)
            lock.lock();
        return true;
    }
    public boolean isReleasable() {
        return hasLock || (hasLock = lock.tryLock());
    }
}
