package producer.consumer;

public class Consumer implements Runnable {
    Q q;

    public Consumer(Q q) {
        this.q = q;
        new Thread(this, "Потребитель").start();
    }

    @Override
    public void run() {
        int j = 1;
        while (true) {
            j++;
            if (j > 10) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("Исключение типа InterruptedException перехвачено.");
                }
                j = 1;
            }
            q.get();
        }
    }
}
