package dead.lock;

public class A {
    synchronized void foo(B b) {
        String name = Thread.currentThread().getName();
        System.out.println(name + " вошел в метод A.foo()");
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println("Класс A прерван");
        }
        System.out.println(name + " пытается вызвать метод B.last()");
        b.last();
    }

    synchronized void last() {
        System.out.println("B методе A.last() ");
    }
}
