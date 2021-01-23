package ru.kpfu.itis.group903.idrisov.daniyar.image.downloader.utils;

public class DownloadByThreads {

    public void downloadByThreads(int threadsCount, String folder, String[] links) {
        FileDownloader fileDownloader = new FileDownloader();
        ThreadPool threadPool = new ThreadPool(threadsCount);
        for (String link: links) {
            Runnable runnable = () -> fileDownloader.download(link, folder);
            threadPool.submit(runnable);
        }
    }


}
