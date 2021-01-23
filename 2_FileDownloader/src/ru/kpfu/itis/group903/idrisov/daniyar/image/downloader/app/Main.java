package ru.kpfu.itis.group903.idrisov.daniyar.image.downloader.app;

import com.beust.jcommander.JCommander;
import ru.kpfu.itis.group903.idrisov.daniyar.image.downloader.utils.DownloadByThreads;


public class Main {

    public static void main(String[] argv) {
        Args args = new Args();
        DownloadByThreads d = new DownloadByThreads();
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);
        String[] links = args.files.split(";");
        if (args.mode.equals("one-thread")) {
            d.downloadByThreads(1, args.folder, links);
        }
        else {
            if (args.mode.equals("multi-thread")) {
                d.downloadByThreads(args.count, args.folder, links);
            }
            else throw new IllegalArgumentException();
        }
    }

}
