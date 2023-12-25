package tcpWork;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class MetroServer extends Thread{
    MetroCardBank bank = null;
    private ServerSocket serverSocket = null;
    private int serverPort = -1;
    private boolean work = true;

    public MetroServer(int serverPort) {
        this.serverPort = serverPort;
        this.bank = new MetroCardBank();
    }

    public MetroCardBank getBank() {
        return bank;
    }

    public void setBank(MetroCardBank bank) {
        this.bank = bank;
    }

    private void stopServer(){
        this.work = false;
    }

    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(serverPort);
            System.out.println("Metro Server started");
            while (work){
                System.out.println("New Client Waiting...");
                Socket socket = serverSocket.accept();
                System.out.println("New client: " + socket);
                ClientHandler ch = new ClientHandler(this.getBank(), socket);
                ch.start();
            }
        } catch (IOException e){
            System.out.println("Error: " + e);
        } finally {
            try {
                serverSocket.close();
                System.out.println("Metro Server stopped");
            } catch (IOException ex){
                System.out.println("Error: " + ex);
            }
        }
    }

    public static void main(String[] args){
        MetroServer server = new MetroServer(7891);
        server.start();
    }
}
