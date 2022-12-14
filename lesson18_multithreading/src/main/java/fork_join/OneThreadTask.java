package fork_join;

import java.util.Date;

public class OneThreadTask {
    public void run() {
        System.out.println(new Date());
        long count = 8_000_000_000_0L;
        long result = -4_000_000_000_0L;
        for (long i = 0; i < count; i++) {
            result += 1L;
        }
        System.out.println(new Date());
        System.out.println(result);
    }
}
