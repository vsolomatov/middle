package parse.file;

import java.io.File;
import java.io.IOException;

public class ParseFileMain {
    public static void main(String[] args) throws InterruptedException {
        File file = new File("temp.java");

        ParseFile parseFile = new ParseFile();
        parseFile.setFile(file);

        if (!file.exists()) {
            try {
                parseFile.saveContent("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Thread first = new Thread(() -> {
            try {
                System.out.println("parseFile.getContent() = " + parseFile.getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
            String newContent = "package ru.job4j.io;\n"
                    + "\n"
                    + "import java.io.*;\n"
                    + "\n"
                    + "public class ParseFile {\n"
                    + "    private File file;\n"
                    + "\n"
                    + "    public synchronized void setFile(File f) {\n"
                    + "        file = f;\n"
                    + "    }\n"
                    + "    \n"
                    + "    public synchronized File getFile() {\n"
                    + "        return file;\n"
                    + "    }\n"
                    + "\n"
                    + "    public String getContent() throws IOException {\n"
                    + "        InputStream i = new FileInputStream(file);\n"
                    + "        String output = \"\";\n"
                    + "        int data;\n"
                    + "        while ((data = i.read()) > 0) {\n"
                    + "            output += (char) data;\n"
                    + "        }\n"
                    + "        return output;\n"
                    + "    }\n"
                    + "\n"
                    + "    public String getContentWithoutUnicode() throws IOException {\n"
                    + "        InputStream i = new FileInputStream(file);\n"
                    + "        String output = \"\";\n"
                    + "        int data;\n"
                    + "        while ((data = i.read()) > 0) {\n"
                    + "            if (data < 0x80) {\n"
                    + "                output += (char) data;\n"
                    + "            }\n"
                    + "        }\n"
                    + "        return output;\n"
                    + "    }\n"
                    + "\n"
                    + "    public void saveContent(String content) throws IOException {\n"
                    + "        OutputStream o = new FileOutputStream(file);\n"
                    + "        for (int i = 0; i < content.length(); i += 1) {\n"
                    + "            o.write(content.charAt(i));\n"
                    + "        }\n"
                    + "    }\n"
                    + "}";
            try {
                parseFile.saveContent(newContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        Thread second = new Thread(() -> {
            try {
                System.out.println("1. Second... parseFile.getContent()  = " + parseFile.getContent());
                Thread.sleep(3000);
                System.out.println("2. Second... parseFile.getContent()  = " + parseFile.getContent());
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        });

        first.start();
        second.start();
        second.join();
    }
}
