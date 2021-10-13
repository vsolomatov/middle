package barrier;

import org.junit.Test;

public class CountBarrierTest {
    @Test
    public void whenCountBarrierEquals10() throws InterruptedException {
        CountBarrier countBarrier = new CountBarrier(10);

        Thread threadCount = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                countBarrier.count();
            }
        });

        Thread firstAwait = new Thread(countBarrier::await);
        Thread secondAwait = new Thread(countBarrier::await);

        firstAwait.start();
        secondAwait.start();

        threadCount.start();

        firstAwait.join();
        secondAwait.join();

    }
}
