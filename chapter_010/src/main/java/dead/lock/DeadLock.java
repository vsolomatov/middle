package dead.lock;

public class DeadLock implements Runnable {
    A a = new A();
    B b = new B();

    DeadLock() {
        Thread.currentThread().setName("Главный поток");
        Thread t = new Thread(this, "Соперничающий поток");
        t.start();

        a.foo(b); // Получить блокировку для объекта a в этом потоке исполнения
        System.out.println("Назад в главный поток");
    }

    @Override
    public void run() {
        b.bar(a); // Получить блокировку для объекта b в другом потоке исполнения
        System.out.println("Назад в другой поток");
    }

    public static void main(String[] args) {
        new DeadLock();
    }
}
