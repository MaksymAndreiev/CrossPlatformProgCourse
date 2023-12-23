package registry;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Registerable extends Remote {
    public int registry(Participant user) throws RemoteException;
    public void getInfo() throws RemoteException;
}

