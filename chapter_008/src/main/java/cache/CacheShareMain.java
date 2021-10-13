package cache;

public class CacheShareMain {
    public static void main(String[] args) throws InterruptedException {
        Cache  cache = new Cache();
        Thread first = new Thread(() -> {
            try {
                System.out.println("Start ... " + Thread.currentThread().getName());
                Thread.sleep(1000);
                cache.createInstance(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread second = new Thread(() -> {
            try {
                System.out.println("Start... " + Thread.currentThread().getName());
                Thread.sleep(1000);
                cache.createInstance(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        first.start();
        second.start();
        first.join();
        second.join();
        System.out.println(cache.get());
    }
}

