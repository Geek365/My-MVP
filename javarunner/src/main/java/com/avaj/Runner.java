package com.avaj;

public class Runner {
    private static final String DOWNLOAD_URL = "https://raw.githubusercontent.com/REBOOTERS/SomeFile/master/App.pdf";

    public static void main(String[] args) {
//        System.err.println("File Download ----->");
//        new HttpDownload(DOWNLOAD_URL).start();

//        FileCopy.copy("README.md", "README1.md");

//        System.err.println("read content of ch_file.txt ------> " + ReadContent.read("HttpDemo\\src\\main\\java\\home\\smart\\fly\\httpurlconnectiondemo\\RxJavaDemoActivity.java"));

        String msg = null;
        for (; ; ) {
            if (msg == null) {
                return;
            }


            System.out.println("come here");

            String appVersion = "4.4.0";
            String uuid = "3.3.5";

            int result = appVersion.compareTo(uuid);

            System.out.println("the result is " + result);


            int[] temp = {1, 13, -2, 1, 65, 12, 0, -10};
        }


    }


}
