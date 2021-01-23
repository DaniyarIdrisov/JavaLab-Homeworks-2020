package ru.kpfu.itis.javalab.group903.idrisov.daniyar.server;

import com.beust.jcommander.JCommander;
import ru.kpfu.itis.javalab.group903.idrisov.daniyar.args.Args;

public class MainForServer {

    public static void main(String[] argv) {

        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        EchoServerSocket serverSocket = new EchoServerSocket();

        serverSocket.start(args.port);


    }

}


