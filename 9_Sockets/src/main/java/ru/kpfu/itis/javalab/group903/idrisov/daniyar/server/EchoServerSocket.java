package ru.kpfu.itis.javalab.group903.idrisov.daniyar.server;

import ru.kpfu.itis.javalab.group903.idrisov.daniyar.handler.MassageHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class EchoServerSocket {

    private ArrayList<MassageHandler> clients;

    public void start(int port) {

        ServerSocket server;
        Socket client;
        clients = new ArrayList<>();

        try {
            server = new ServerSocket(port);
            System.out.println("The server started on the port: " + port);

            while (true) {
                client = server.accept();
                MassageHandler newClient = new MassageHandler(client, this);
                clients.add(newClient);
            }

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    public void messageForChat(String message) {
        for (MassageHandler client: clients) {
            client.thisMessageForChat(message);
        }
    }

}
