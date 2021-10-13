package pc;

public class ConsumerInteger implements Runnable {
    SimpleBlockingQueue<Integer> q;

    public ConsumerInteger(SimpleBlockingQueue<Integer> q) {
        this.q = q;
        new Thread(this, "Потребитель").start();
    }

    @Override
    public void run() {
        while (true) {
            q.poll();
        }
    }

    public Thread getThread() {
        return Thread.currentThread();
    }

}
