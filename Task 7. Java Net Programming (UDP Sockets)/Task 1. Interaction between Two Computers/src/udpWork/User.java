package udpWork;

import java.io.Serializable;
import java.net.InetAddress;

public class User implements Serializable {
    private final long serialVersionUID = -1L;
    private InetAddress address;
    private int port;

    public User() {
    }

    public User(InetAddress address, int port) {
        this.address = address;
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Host name: " + address.getHostName() +
                "\tHost address: " + address.getHostAddress() +
                " Port: " + port;
    }
}
