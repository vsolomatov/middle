package producer.consumer;

public class Producer implements Runnable {
    Q q;

    public Producer(Q q) {
        this.q = q;
        new Thread(this, "Поставщик").start();
    }

    @Override
    public void run() {
        int i = 1;
        while (true) {
            q.put(i++);
        }
    }
}
