package echoServer;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPEchoClient {
    public final static int PORT = 7;

    public static void main(String[] args){
        String hostname = "localhost";
        if (args.length > 0){
            hostname = args[0];
        }
        try {
            InetAddress inetAddress = InetAddress.getByName(hostname);
            DatagramSocket socket = new DatagramSocket();
            Thread sender = new SenderThread(socket, inetAddress, PORT);
            sender.start();
            Thread reciever = new RecieveThread(socket);
            reciever.start();
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
