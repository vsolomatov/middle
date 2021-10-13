public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        String[] process = {"/", "|", "\\"};
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\r Loading... " + process[i]);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                break;
            }
            i = i == 2 ? 0 : (i + 1);
        }
    }
}
