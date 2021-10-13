import java.util.Arrays;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        FileDownload fileDownload = new FileDownload();
        try {
            fileDownload.fileDownload(this.url, this.speed);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("Started Wget.main");
        System.out.println("args = " + Arrays.deepToString(args));

        String url;
        if (args.length > 0) {
            url = args[0];
            if (url.isEmpty()) {
                url = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
            }
        } else {
            url = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        }

        int speed = 5000;
        if (args.length > 1) {
            if (!args[1].isEmpty()) {
                speed = Integer.parseInt(args[1]);
            }
        }

        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        try {
            wget.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished Wget.main");
    }
}
