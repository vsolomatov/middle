package suspend.flag;

public class SuspendResume {

    public static void main(String[] args) {
        NewThread ob1 = new NewThread("Первый");
        NewThread ob2 = new NewThread("Второй");

        try {
            Thread.sleep(1000);

            ob1.mySuspend();
            System.out.println("Приостановка потока: " + ob1.name);
            Thread.sleep(1000);
            ob1.myResume();
            System.out.println("Возобновление потока: " + ob1.name);

            ob2.mySuspend();
            System.out.println("Приостановка потока: " + ob2.name);
            Thread.sleep(1000);
            ob2.myResume();
            System.out.println("Возобновление потока: " + ob1.name);
        } catch (InterruptedException e) {
              System.out.println("Главный поток прерван");
        }

        // Ожидать завершения потоков
        try {
            System.out.println("Ожидание завершения потоков");
            ob1.t.join();
            ob2.t.join();
        } catch (InterruptedException e) {
            System.out.println("Главный поток прерван");
        }
    }

}
