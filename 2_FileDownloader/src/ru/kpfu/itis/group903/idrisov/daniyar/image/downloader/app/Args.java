package ru.kpfu.itis.group903.idrisov.daniyar.image.downloader.app;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")

public class Args {

    @Parameter(names = "--mode")
    public String mode;

    @Parameter(names = "--files")
    public String files;

    @Parameter(names = "--folder")
    public String folder;

    @Parameter(names = "--count")
    public int count;

}
