package thread.pool;

import pc.SimpleBlockingQueue;

public class MyThread extends Thread {
    private final SimpleBlockingQueue<Runnable> tasks;

    public MyThread(SimpleBlockingQueue<Runnable> tasks) {
        super();
        this.tasks = tasks;
    }

    @Override
    public void run() {
        //System.out.println("Started " + Thread.currentThread().getName());
        Runnable task;
        do {
            task = tasks.poll();
            //System.out.println("    task = " + task + ", " + Thread.currentThread().getName());
            if (task != null) {
                task.run();
            }
        } while (!Thread.currentThread().isInterrupted());
        System.out.println("Finished - " + Thread.currentThread().getName());
    }

    @Override
    public String toString() {
        return "MyThread{"
                + ", name='" + Thread.currentThread().getName()
                + '}';
    }
}
