package thread.pool;

import pc.SimpleBlockingQueue;
import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<MyThread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(15);

    public ThreadPool() {
        System.out.println("Started ThreadPool.ThreadPool");
        int size = Runtime.getRuntime().availableProcessors();
        //size = 3;
        System.out.println("    size = " + size);
        for (int i = 0; i < size; i++) {
            MyThread myThread = new MyThread(tasks);
            threads.add(myThread);
            myThread.start();
        }
        System.out.println("Finished ThreadPool.ThreadPool");
    }

    public List<MyThread> getThreads() {
        return threads;
    }

    /**
     * Метод добавляет задачи в блокирующую очередь tasks
     * @param job
     */
    public void work(Runnable job) {
            tasks.offer(job);
    }

    public void shutdown() {
        for (MyThread myThread : threads) {
            myThread.interrupt();
        }
    }

    @Override
    public String toString() {
        return "ThreadPool{"
                + "threads=" + threads
                + ", tasks=" + tasks
                + '}';
    }
}
