package pc;

public class ProducerInteger implements Runnable {
    SimpleBlockingQueue<Integer> q;


    public ProducerInteger(SimpleBlockingQueue<Integer> q) {
        this.q = q;
        new Thread(this, "Поставщик").start();
    }

    @Override
    public void run() {
        while (true) {
            q.offer(getRandomNumber(0, 1000));
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public Thread getThread() {
        return Thread.currentThread();
    }

}
