package parallel.search;

import pc.SimpleBlockingQueue;

public class ParallelSearch {

    public static void main(String[] args) {

        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(15);

        final Thread consumer = new Thread(
                () -> {
                    System.out.println("Started Thread = " + Thread.currentThread().getName());
                    while (!Thread.currentThread().isInterrupted()) {
                        System.out.println(queue.poll());
                    }
                    System.out.println("Finished Thread = " + Thread.currentThread().getName());
                }
        );
        consumer.start();

        final Thread producer = new Thread(
                () -> {
                    System.out.println("Started Thread = " + Thread.currentThread().getName());
                    for (int index = 0; index != 3; index++) {
                        queue.offer(index);
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            System.out.println("Исключение типа InterruptedException перехвачено - " + Thread.currentThread().getName());
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println("Finished Thread = " + Thread.currentThread().getName());
                }
        );
        producer.start();

        try {
            producer.join();
            consumer.interrupt();
            consumer.join();
        } catch (InterruptedException e) {
            System.out.println("Исключение типа InterruptedException перехвачено - " + Thread.currentThread().getName());
            Thread.currentThread().interrupt();
        }
        System.out.println("Finished Thread = " + Thread.currentThread().getName());
    }

}
