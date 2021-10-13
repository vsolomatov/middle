package pc;

import org.junit.Test;

public class PCTest {
    int qSize = 50;

    /*@Test
    public void PCTest1() {
        SimpleBlockingQueue<Integer> q = new SimpleBlockingQueue<>(qSize);

        System.out.println("Для остановки нажмите Ctrl+C.");
        ProducerInteger producerInteger = new ProducerInteger(q);
        ConsumerInteger consumerInteger = new ConsumerInteger(q);

        try {
            producerInteger.getThread().join();
            consumerInteger.getThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }*/
}
