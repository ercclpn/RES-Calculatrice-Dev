package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {
    final static Logger LOG = Logger.getLogger(Client.class.getName());

    Socket clientSocket;
    BufferedReader in;
    PrintWriter out;
    boolean connected = false;
    String toCompute;

    /**
     * This inner class implements the Runnable interface, so that the run()
     * method can execute on its own thread. This method reads data sent from the
     * server, line by line, until the connection is closed or lost.
     */
    class NotificationListener implements Runnable {

        @Override
        public void run() {
            String notification;
            try {
                if(connected && (notification = in.readLine()) != null) {
                    LOG.log(Level.INFO, "Server> {0}", new Object[]{notification});
                }
            } catch (IOException e) {
                LOG.log(Level.SEVERE, "Connection problem in client: {0}", new Object[]{e.getMessage()});
                connected = false;
            } finally {
                cleanup();
            }
        }
    }

    /**
     * This method is used to connect to the server and to inform the server that
     * the user "behind" the client has a name (in other words, the HELLO command
     * is issued after successful connection).
     *
     * @param serverAddress the IP address used by the Presence Server
     * @param serverPort the port used by the Presence Server
     * @param toCompute the calculate to compute with the server
     */
    public void connect(String serverAddress, int serverPort, String toCompute) {
        try {
            clientSocket = new Socket(serverAddress, serverPort);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream());
            connected = true;
            this.toCompute = toCompute;
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "Unable to connect to server: {0}", e.getMessage());
            cleanup();
            return;
        }
        // Let us start a thread, so that we can listen for server notifications
        new Thread(new NotificationListener()).start();

        out.println(toCompute);
        out.flush();
    }

    private void cleanup() {

        try {
            if (in != null) {
                in.close();
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }

        if (out != null) {
            out.close();
        }

        try {
            if (clientSocket != null) {
                clientSocket.close();
            }
        } catch (IOException ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
