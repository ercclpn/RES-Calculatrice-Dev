package client;

import server.Server;

import java.util.Scanner;

public class ClientApplication {
    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");

        Thread listenThread = new Thread(new Server());
        listenThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final String IP_SERVER = "localhost";
        final int PORT_SERVER = 3636;
        Client c1 = new Client();
        String computing = "";
        Scanner sc = new Scanner(System.in);

        System.out.println("Client> ");
        computing = sc.nextLine();
        c1.connect("localhost", 3636, computing);
    }
}