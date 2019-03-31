package application;

import server.MultiServer;

public class MultiServerApplication {

    public static void main(String... args) {
        MultiServer multiServer = new MultiServer(3636);
        multiServer.serveClients();
    }
}
