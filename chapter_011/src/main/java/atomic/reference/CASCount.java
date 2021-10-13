package atomic.reference;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        int existingValue;
        int newValue;
        do {
            existingValue = get();
            newValue = existingValue + 1;
        } while (!count.compareAndSet(existingValue, newValue));
    }

    public int get() {
        return count.get();
    }
}
