import java.io.*;
import java.net.URL;

public class FileDownload {

    public void fileDownload(String url, int speed) {
        System.out.println("Started FileDownload.fileDownload");

        try (BufferedInputStream inputStream = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;

            long timeDownLoad;
            long startTimeStamp;
            long endTimeStamp;
            long timeWait;
            do {
                startTimeStamp = System.nanoTime();
                bytesRead = inputStream.read(dataBuffer, 0, 1024);
                endTimeStamp = System.nanoTime();

                if (bytesRead != -1) {
                    System.out.println("startTimeStamp = " + startTimeStamp);
                    System.out.println("endTimeStamp = " + endTimeStamp);
                    // Вычисляем время загрузки очередных байт
                    timeDownLoad = (endTimeStamp - startTimeStamp) / 1000;
                    System.out.println("timeDownLoad = " + timeDownLoad);
                    timeWait = speed - timeDownLoad;
                    System.out.println("timeWait = " + timeWait);

                    // Записываем данные в новый файл
                    fileOutputStream.write(dataBuffer, 0, bytesRead);

                    // Ждем нужное нам время, если нужно
                    if (timeWait > 0) {
                        try {
                            Thread.sleep(timeWait);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    }
                } else {
                    break;
                }
            } while (true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished FileDownload.fileDownload");
    }
}
