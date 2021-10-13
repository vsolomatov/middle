package switcher;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class MasterSlaveBarrier {

    @GuardedBy("monitor")
    private boolean flag = false;

    private final Object monitor = this;

    public void tryMaster() {
        synchronized (monitor) {
            while (flag) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void trySlave() {
        synchronized (monitor) {
            while (!flag) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void doneMaster() {
        synchronized (monitor) {
            flag = true;
            monitor.notifyAll();
        }
    }

    public void doneSlave() {
        synchronized (monitor) {
            flag = false;
            monitor.notifyAll();
        }
    }
}
