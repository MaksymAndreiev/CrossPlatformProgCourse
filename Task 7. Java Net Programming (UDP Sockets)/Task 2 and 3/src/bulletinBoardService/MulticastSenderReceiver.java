package bulletinBoardService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastSenderReceiver {
    private String name;
    private InetAddress address;
    private int port = 3456;
    private MulticastSocket group;

    public MulticastSenderReceiver(String name) {
        this.name = name;
        try {
            address = InetAddress.getByName("224.0.0.1");
            group = new MulticastSocket(port);
            new Receiver().start();
            new Sender().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new MulticastSenderReceiver("test");
    }

    private class Receiver extends Thread{
        @Override
        public void run() {
            try {
                byte[] in = new byte[256];
                DatagramPacket packet = new DatagramPacket(in, in.length);
                group.joinGroup(address);
                while (true){
                    group.receive(packet);
                    System.out.println(new String(packet.getData(), 0, packet.getLength()));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class Sender extends Thread{
        @Override
        public void run() {
            try {
                BufferedReader fromUSer = new BufferedReader(
                        new InputStreamReader(System.in)
                );
                while (true){
                    String message = name + ":" + fromUSer.readLine();
                    byte[] out = message.getBytes();
                    DatagramPacket packet = new DatagramPacket(out, out.length, address, port);
                    group.send(packet);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
