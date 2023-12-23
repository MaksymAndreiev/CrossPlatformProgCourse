package bulletinBoardService;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class MessengerImpl implements Messenger {
    private UITasks ui = null;
    private MulticastSocket group = null;
    private InetAddress address = null;
    private int port;
    private String name;

    private boolean canceled = false;

    public MessengerImpl(UITasks ui, InetAddress address, int port, String name) {
        this.ui = ui;
        this.address = address;
        this.port = port;
        this.name = name;
        try {
            group = new MulticastSocket(port);
            group.setTimeToLive(2);
            group.joinGroup(address);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start() {
        Thread thread = new Receiver();
        thread.start();
    }

    @Override
    public void stop() {
        cancel();
        try {
            if (!group.isClosed()) {
                group.leaveGroup(address);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            group.close();
        }
    }

    @Override
    public void send() {
        new Sender().start();
    }

    private class Sender extends Thread {
        @Override
        public void run() {
            try {
                String message = name + ": " + ui.getMessage();
                byte[] out = message.getBytes();
                DatagramPacket packet = new DatagramPacket(out, out.length, address, port);
                group.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class Receiver extends Thread {
        @Override
        public void run() {
            try {
                byte[] in = new byte[256];
                DatagramPacket packet = new DatagramPacket(in, in.length);
                while (!isCanceled()) {
                    group.receive(packet);
                    ui.setText(new String(packet.getData(), 0, packet.getLength()));
                }
            } catch (IOException e) {
                if (!isCanceled()) {
                    e.printStackTrace();
                }
            }
        }
    }

    private synchronized boolean isCanceled() {
        return canceled;
    }

    private synchronized void cancel() {
        canceled = true;
    }
}
