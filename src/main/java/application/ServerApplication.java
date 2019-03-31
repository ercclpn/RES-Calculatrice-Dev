package application;

import server.Server;

public class ServerApplication {
    public static void main(String ... args){
        Server server = new Server(3636);
        server.serveClients();
    }
}
