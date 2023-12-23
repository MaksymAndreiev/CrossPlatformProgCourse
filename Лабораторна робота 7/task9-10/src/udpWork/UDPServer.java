package udpWork;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPServer {
    private ActiveUsers users = null;
    private DatagramSocket socket = null;
    private DatagramPacket packet = null;
    private InetAddress address = null;
    private int port = -1;

    public UDPServer(int port) {
        this.port = port;
        try {
            socket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        users = new ActiveUsers();
    }

    public void work(int bufferSize){
        try{
            System.out.println("Server start...");
            while (true){
                getUserData(bufferSize);
                log(address, port);
                sendUserData();
            }
        } catch (IOException e){
            System.out.println("Error: " + e);
        } finally {
            System.out.println("Server end...");
            socket.close();
        }
    }

    private void sendUserData() throws IOException {
        byte[] buffer;
        for (int i = 0; i < users.size(); i++) {
            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bout);
            out.writeObject(users.get(i));
            buffer = bout.toByteArray();
            packet = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(packet);
        }
        buffer = "end".getBytes();
        packet = new DatagramPacket(buffer, 0, address, port);
        socket.send(packet);
    }

    private void log(InetAddress address, int port){
        System.out.println("Request from: " + address.getHostAddress() + " port: " + port);
    }

    private void clear(byte[] arr){
        arr = new byte[arr.length];
    }

    private void getUserData(int bufferSize) throws IOException {
        byte[] buffer = new byte[bufferSize];
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        address = packet.getAddress();
        port = packet.getPort();
        User user = new User(address, port);
        if (users.isEmpty()){
            users.add(user);
        } else if (!users.contains(user)){
            users.add(user);
        }
        clear(buffer);
    }

    public static void main(String[] args){
        (new UDPServer(1501)).work(256);
    }
}
