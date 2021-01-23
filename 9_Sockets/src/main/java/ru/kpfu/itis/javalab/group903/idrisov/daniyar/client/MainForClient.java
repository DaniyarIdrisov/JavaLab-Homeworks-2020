package ru.kpfu.itis.javalab.group903.idrisov.daniyar.client;

import com.beust.jcommander.JCommander;
import ru.kpfu.itis.javalab.group903.idrisov.daniyar.args.Args;

import java.util.Scanner;

public class MainForClient {

    public static void main(String[] argv) {

        Args args = new Args();

        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);

        SocketClient client = new SocketClient(args.serverIp, args.serverPort);

        Scanner scanner = new Scanner(System.in);

        System.out.println("*Enter your name:*");
        String name = scanner.nextLine();
        client.sendMessage(name);
        System.out.println("*Enter your message:*");
        while (true) {
            String message = scanner.nextLine();
            client.sendMessage(message);
            System.out.println("*Enter your message:*");
        }

    }

}
