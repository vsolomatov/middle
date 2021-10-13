package non.bloking.cache;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CacheExceptionTest {

    /*@Test
    public void whenThrowException() throws InterruptedException {
        AtomicReference<Exception> exceptionAtomicReference = new AtomicReference<>();
        Thread thread = new Thread(
                () -> {
                    try {
                        throw new RuntimeException("Throw Exception in Thread");
                    } catch (Exception e) {
                        exceptionAtomicReference.set(e);
                    }
                }
        );
        thread.start();
        thread.join();
        Assert.assertThat(exceptionAtomicReference.get().getMessage(), is("Throw Exception in Thread"));
    }*/

    @Test
    public void whenTestUpdate() throws InterruptedException {
        Cache cache = new Cache();

        Base base0 = new Base(0, 0);
        cache.add(base0);

        Base base1 = new Base(1, 0);
        cache.add(base1);

        Thread thread2 = new Thread(
                () -> {
                    //while (!Thread.currentThread().isInterrupted()) {
                    try {
                        cache.update(base1);
                    } catch (OptimisticException e) {
                        System.out.println("Исключение типа OptimisticException перехвачено - " + Thread.currentThread().getName());
                    }
                    //}
                }
        );

        Thread thread1 = new Thread(
                () -> {
                    //while (!Thread.currentThread().isInterrupted()) {
                        try {
                            cache.update(base0);
                        } catch (OptimisticException e) {
                            System.out.println("Исключение типа OptimisticException перехвачено - "  + Thread.currentThread().getName());
                        }
                    //}
                }
        );

        Thread thread3 = new Thread(
                () -> {
                    //while (!Thread.currentThread().isInterrupted()) {
                        try {
                            cache.update(base0);
                        } catch (OptimisticException e) {
                            System.out.println("Исключение типа OptimisticException перехвачено - " + Thread.currentThread().getName());
                        }
                    //}
                }
        );

        Thread thread4 = new Thread(
                () -> {
                    //while (!Thread.currentThread().isInterrupted()) {
                    try {
                        cache.update(base0);
                    } catch (OptimisticException e) {
                        System.out.println("Исключение типа OptimisticException перехвачено - " + Thread.currentThread().getName());
                    }
                    //}
                }
        );


        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        thread1.join();
        thread2.join();
        thread3.join();
        thread4.join();

        System.out.println("cache = " + cache);
    }

    @Test
    public void whenTestDelete() throws InterruptedException {
        Cache cache = new Cache();

        Base base0 = new Base(0, 0);
        cache.add(base0);

        Base base1 = new Base(1, 0);
        cache.add(base1);

        Base base3 = new Base(1, 0);

        Thread thread3 = new Thread(
                () -> {
                    //while (!Thread.currentThread().isInterrupted()) {
                    try {
                        cache.update(base0);
                    } catch (OptimisticException e) {
                        System.out.println("Исключение типа OptimisticException перехвачено - " + Thread.currentThread().getName());
                    }
                    //}
                }
        );

        Thread thread2 = new Thread(
                () -> {
                    //while (!Thread.currentThread().isInterrupted()) {
                    try {
                        cache.delete(base1);
                    } catch (OptimisticException e) {
                        System.out.println("Исключение типа OptimisticException перехвачено - " + Thread.currentThread().getName());
                    }
                    //}
                }
        );

        Thread thread1 = new Thread(
                () -> {
                    //while (!Thread.currentThread().isInterrupted()) {
                    try {
                        cache.delete(base3);
                    } catch (OptimisticException e) {
                        System.out.println("Исключение типа OptimisticException перехвачено - "  + Thread.currentThread().getName());
                    }
                    //}
                }
        );

        System.out.println("cache = " + cache);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        assertThat(cache.size(), is(1));

        System.out.println("cache = " + cache);
    }
}
