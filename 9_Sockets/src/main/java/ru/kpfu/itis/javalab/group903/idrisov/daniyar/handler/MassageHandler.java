package ru.kpfu.itis.javalab.group903.idrisov.daniyar.handler;

import ru.kpfu.itis.javalab.group903.idrisov.daniyar.server.EchoServerSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MassageHandler {

    Socket client;
    EchoServerSocket server;
    BufferedReader fromClient;
    PrintWriter toClient;

    public MassageHandler(Socket client, EchoServerSocket server) {
        this.client = client;
        this.server = server;
        try {
            this.fromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.toClient = new PrintWriter(client.getOutputStream(), true);
            new Thread(messageHandler).start();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private Runnable messageHandler = () -> {
        try {
            String name = fromClient.readLine();
            System.out.println("Client *" + name + "* entered the chat");
            server.messageForChat("Welcome to our chat, " + name + "!");
            String message = fromClient.readLine();
            while (true) {
                System.out.println("Client *" + name + "* sent a message with content: '" + message + "'");
                server.messageForChat(name + ": " + message);
                message = fromClient.readLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    };

    public void thisMessageForChat(String message) {
        toClient.println(message);
    }

}
