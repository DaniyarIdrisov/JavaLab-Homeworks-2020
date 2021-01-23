package ru.kpfu.itis.group903.idrisov.daniyar.image.downloader.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

public class FileDownloader {

    public void download(String link, String folder) {

        File newImage = new File(String.valueOf(UUID.randomUUID()));
        BufferedImage image = null;
        URL url = null;
        try {
            url = new URL(link);
        } catch (MalformedURLException exception) {
            throw new IllegalArgumentException(exception);
        }
        try {
            image = ImageIO.read(url);
        } catch (IOException exception) {
            throw new IllegalArgumentException(exception);
        }
        if (image != null) {
            try {
                ImageIO.write(image, "png",new File(folder + "/" + newImage.getName()  + ".png"));
            } catch (IOException exception) {
                throw new IllegalArgumentException(exception);
            }
        }
    }

}
