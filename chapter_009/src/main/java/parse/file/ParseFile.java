package parse.file;

import java.io.*;

public class ParseFile {
    private File file;

    public synchronized void setFile(File f) {
        file = f;
    }

    public synchronized File getFile() {
        return file;
    }

    public synchronized String getContent() throws IOException {
        InputStream inputStream = new FileInputStream(file);
        String output = "";
        int data;
        while ((data = inputStream.read()) > 0) {
            output += (char) data;
        }
        return output;
    }

    public synchronized String getContentWithoutUnicode() throws IOException {
        InputStream inputStream = new FileInputStream(file);
        String output = "";
        int data;
        while ((data = inputStream.read()) > 0) {
            if (data < 0x80) {
                output += (char) data;
            }
        }
        return output;
    }

    public synchronized void saveContent(String content) throws IOException {
        OutputStream outputStream = new FileOutputStream(file);
        for (int i = 0; i < content.length(); i += 1) {
            outputStream.write(content.charAt(i));
        }
    }
}
