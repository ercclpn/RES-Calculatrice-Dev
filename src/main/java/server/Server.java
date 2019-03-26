package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    final static Logger LOG = Logger.getLogger(server.Server.class.getName());

    int port;

    /**
     * Constructor
     *
     * @param port the port to listen on
     */
    public Server(int port) {
        this.port = port;
    }

    /**
     * This method initiates the process. The server creates a socket and binds
     * it to the previously specified port. It then waits for clients in a infinite
     * loop. When a client arrives, the server will read its input line by line
     * and send back the data converted to uppercase. This will continue until
     * the client sends the "BYE" command.
     */
    public void serveClients() {
        ServerSocket serverSocket;
        Socket clientSocket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return;
        }

        while (true) {
            try {

                LOG.log(Level.INFO, "Waiting (blocking) for a new client on port {0}", port);
                clientSocket = serverSocket.accept();
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream());
                String line;

                out.println("Welcome to the calculate server, please give me an operation:");
                out.flush();

                while ((line = in.readLine()) != null) {
                    String [] splitString = line.split(" ");

                    int result = Integer.parseInt(splitString[0]);
                    int b = Integer.parseInt((splitString[2]));
                    switch(splitString[1]){
                        case "+" :
                            result+=b;
                            break;

                        case "-":
                            result-=b;
                            break;

                        case "*":
                            result*=b;
                            break;

                        case "/":
                            result/=b;
                            break;

                        default:
                            result=0;
                            break;
                    }
                    out.println("Answer=" + result);
                    out.flush();

                    break;
                }

                LOG.info("Cleaning up resources...");
                clientSocket.close();
                in.close();
                out.close();

            } catch (IOException ex) {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException ex1) {
                        LOG.log(Level.SEVERE, ex1.getMessage(), ex1);
                    }
                }
                if (out != null) {
                    out.close();
                }
                if (clientSocket != null) {
                    try {
                        clientSocket.close();
                    } catch (IOException ex1) {
                        LOG.log(Level.SEVERE, ex1.getMessage(), ex1);
                    }
                }
                LOG.log(Level.SEVERE, ex.getMessage(), ex);
            }
        }
    }
}
