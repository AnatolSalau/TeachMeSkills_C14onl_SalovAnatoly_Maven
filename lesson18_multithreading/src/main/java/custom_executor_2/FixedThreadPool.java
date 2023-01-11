package custom_executor_2;

// Interface for our varieties of performers
// Custom interface for which contains execute method
public interface FixedThreadPool {
    // Method
    void execute(Runnable r);
}
