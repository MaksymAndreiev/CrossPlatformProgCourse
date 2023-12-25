package server;

import client.E;
import client.Pi;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIServer {
    private E obj = null;
    private Registry registry = null;

    public RMIServer(int port) throws RemoteException {
        this.obj = new E();
        registry = LocateRegistry.createRegistry(port);
        System.out.println("The RMI registry is created...");
        Remote stub = UnicastRemoteObject.exportObject(obj,0);
        System.out.println("The object is exported...");
        registry.rebind(obj.getClass().getSimpleName(), stub);
        System.out.println("The stub is registered in RMI registry...");
        System.out.println("The Greeter server is ready to work...");
    }

    public RMIServer() throws RemoteException {
        this(1099);
    }

    public void stop() throws RemoteException, NotBoundException {
        registry.unbind(obj.getClass().getSimpleName());
        System.out.println("The stub is unregistered from RMI registry...");
        if (UnicastRemoteObject.unexportObject(obj,true)) {
            System.out.println("The object is unexported...");
        } else {
            System.out.println("The object is Not unexported...");
        }
        if (UnicastRemoteObject.unexportObject(registry,true)) {
            System.out.println("The registry is unexported...");
        } else {
            System.out.println("The registry is Not unexported...");
        }
    }
}

