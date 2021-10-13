package barrier;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class CountBarrier {
    private final int total;

    @GuardedBy("this")
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    synchronized public void count() {
        System.out.println("Started CountBarrier.count - " + Thread.currentThread().getName());
        System.out.println("    count = " + count + " - " + Thread.currentThread().getName());
        if (count < total) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                notifyAll();
            }
            count++;
        } else {
            notifyAll();
            Thread.currentThread().interrupt();
        }
        System.out.println("Finished CountBarrier.count - " + Thread.currentThread().getName());
    }

    synchronized public void await() {
        System.out.println("Started CountBarrier.await - " + Thread.currentThread().getName());
        while (count != total) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Finished CountBarrier.await - " + Thread.currentThread().getName());
    }

}
