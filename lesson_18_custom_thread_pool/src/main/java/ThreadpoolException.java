/**
 * Thrown when there's a RuntimeException or InterruptedException when executing a runnable from the queue, or awaiting termination
 */
public class ThreadpoolException extends RuntimeException {
    public ThreadpoolException(Throwable cause) {
        super(cause);
    }
}