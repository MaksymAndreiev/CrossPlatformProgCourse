package echoServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class RecieveThread extends Thread {
    private DatagramSocket socket;
    private volatile boolean stopped = false;

    public RecieveThread(DatagramSocket socket) {
        this.socket = socket;
    }

    public void halt(){
        this.stopped = true;
    }

    @Override
    public void run() {
        byte [] buffer = new byte[65507];
        while (true){
            if (stopped){
                return;
            }
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length);
            try {
                socket.receive(datagramPacket);
                String s = new String(datagramPacket.getData(), 0, datagramPacket.getLength(), StandardCharsets.UTF_8);
                System.out.println(s);
                Thread.yield();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
