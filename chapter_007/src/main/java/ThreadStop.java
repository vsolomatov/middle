public class ThreadStop {
    public static void main(String[] args) throws InterruptedException {

        Thread progress = new Thread(new ConsoleProgress());

        progress.start();

        Thread.sleep(3000); /* симулируем выполнение параллельной задачи в течение 3 секунд */

        progress.interrupt(); //

    }
}
