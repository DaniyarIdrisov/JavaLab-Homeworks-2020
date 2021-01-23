package ru.kpfu.itis.javalab.group903.idrisov.daniyar.args;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Args {

        @Parameter(names = "--port")
        public int port;

        @Parameter(names = "--server-ip")
        public String serverIp;

        @Parameter(names = "--server-port")
        public int serverPort;

}
