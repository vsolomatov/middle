package thread.pool;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ThreadPoolTest {
    final ThreadPool threadPool = new ThreadPool();

    @Test
    public void whenThreadPoolShutDown() {
        Runnable task0 = () -> System.out.println(Thread.currentThread().getName() + " Run task0");
        Runnable task1 = () -> System.out.println(Thread.currentThread().getName() + " Run task1");
        Runnable task2 = () -> System.out.println(Thread.currentThread().getName() + " Run task2");
        Runnable task3 = () -> System.out.println(Thread.currentThread().getName() + " Run task3");
        Runnable task4 = () -> System.out.println(Thread.currentThread().getName() + " Run task4");
        Runnable task5 = () -> System.out.println(Thread.currentThread().getName() + " Run task5");
        Runnable task6 = () -> System.out.println(Thread.currentThread().getName() + " Run task6");
        Runnable task7 = () -> System.out.println(Thread.currentThread().getName() + " Run task7");
        Runnable task8 = () -> System.out.println(Thread.currentThread().getName() + " Run task8");
        Runnable task9 = () -> System.out.println(Thread.currentThread().getName() + " Run task9");
        Runnable task10 = () -> System.out.println(Thread.currentThread().getName() + " Run task10");
        Runnable task11 = () -> System.out.println(Thread.currentThread().getName() + " Run task11");

        threadPool.work(task0);
        threadPool.work(task1);
        threadPool.work(task2);
        threadPool.work(task3);
        threadPool.work(task4);
        threadPool.work(task5);
        threadPool.work(task6);
        threadPool.work(task7);
        threadPool.work(task8);
        threadPool.work(task9);
        threadPool.work(task10);
        threadPool.work(task11);

        // Ждем пока все задания будут выполнены
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPool.shutdown();

        // Ждем пока все потоки пула завершат свою работу
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Прверим состояния потоков
        for (MyThread myThread : threadPool.getThreads()) {
            //System.out.println(myThread.getState());
            assertThat(myThread.getState(), is(Thread.State.TERMINATED));
        }
    }
}
