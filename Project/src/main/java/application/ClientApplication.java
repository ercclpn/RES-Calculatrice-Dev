package application;

import client.Client;
import java.util.Scanner;

public class ClientApplication {
    public static void main(String[] args) {
        System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s %n");

        String IP_SERVER = "";
        final int PORT_SERVER = 3636;
        Scanner sc = new Scanner(System.in);
        Client c1 = new Client();
        String computing = "";
        System.out.print("IP address : ");
        IP_SERVER = sc.nextLine();
        c1.connect(IP_SERVER, PORT_SERVER);

        computing = sc.nextLine();
        c1.sendRequest(computing);
        c1.disconnect();
    }
}