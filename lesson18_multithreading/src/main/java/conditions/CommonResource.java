package conditions;

import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;


@Getter
public class CommonResource {
    private  AtomicInteger count;
    Condition condition;

    public CommonResource(Condition condition) {
        this.condition = condition;
    }

    public CommonResource() {
        this.count = new AtomicInteger(0);
    }

    public void start() {
        while (true) {
            if(count.intValue() == 5) {
                System.out.println("FIVE");
            }
        }
    }
}
