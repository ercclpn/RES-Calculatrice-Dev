import server.Server;

public class Main {

    public static void main(String ... args){
        Server server = new Server(3636);

        server.serveClients();
    }
}
