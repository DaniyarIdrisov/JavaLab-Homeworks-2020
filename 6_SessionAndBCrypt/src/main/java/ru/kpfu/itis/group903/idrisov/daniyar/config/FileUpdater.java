package ru.kpfu.itis.group903.idrisov.daniyar.config;

import java.io.*;

public class FileUpdater {

    private static void appendUsingOutputStream(String fileName, String data) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(fileName), true);
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
