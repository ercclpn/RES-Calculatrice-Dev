import server.MultiServer;
import server.Server;

public class Main {

    public static void main(String ... args){
        //Server server = new Server(3636);

        //server.serveClients();
        MultiServer multiServer = new MultiServer(3636);
        multiServer.serveClients();
    }
}
