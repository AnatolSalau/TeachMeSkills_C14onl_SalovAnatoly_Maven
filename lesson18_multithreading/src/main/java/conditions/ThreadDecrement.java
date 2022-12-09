package conditions;

import lombok.AllArgsConstructor;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

@AllArgsConstructor
public class ThreadDecrement {
    private Lock lock;
    private CommonResource commonResource;

    public void decrement() {

    }
}
