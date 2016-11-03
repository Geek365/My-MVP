package com.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Runner {
    private static final String DOWNLOAD_URL = "https://raw.githubusercontent.com/REBOOTERS/SomeFile/master/App.pdf";

    public static void main(String[] args) {
        System.out.print("hello world");
        new DownloadTask().start();

    }


    static class DownloadTask extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                URL url = new URL(DOWNLOAD_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setConnectTimeout(20000);
                connection.setReadTimeout(20000);

                connection.connect();

                InputStream inputStream = connection.getInputStream();
                FileNameMap map=connection.getFileNameMap();

                System.err.println("the map is "+map.toString());

                File file = new File("myFile.pdf");
                FileOutputStream fileOutputStream = new FileOutputStream(file);

                byte[] buffer = new byte[2048];

                int len = 0;
                int fileLength=connection.getContentLength();
                int temp=0;

                while ((len = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, len);
                    temp=temp+len;
                    System.err.println("the len is " + temp*100/fileLength);
                }

                fileOutputStream.flush();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
