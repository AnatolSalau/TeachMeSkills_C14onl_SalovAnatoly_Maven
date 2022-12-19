package fork_join_framework.managed_blocked_common_pool;

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
        System.out.println("BLOCK");
        return true;
    }
    public boolean isReleasable() {
        System.out.println("IS_RELEASE");
        rtlock.lock();
        return isLocked || (isLocked = rtlock.isLocked());
    }
}
