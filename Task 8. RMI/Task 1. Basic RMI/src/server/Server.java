package server;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Server {
    public static void main(String[] args) {
        int sec = 40;
        try {
            RMIServer server = new RMIServer();
            System.out.println("\nThe server will work about " +
                    sec + " seconds\n");
            try {
                Thread.sleep(sec*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            server.stop();
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("The server is stopped...");
        }
    }
}
