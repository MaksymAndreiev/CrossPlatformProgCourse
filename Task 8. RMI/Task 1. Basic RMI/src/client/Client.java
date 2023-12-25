package client;

import compute.Compute;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        int port = 1099;
        String host = "localhost";
        Registry registry = null;
        try {
            registry = LocateRegistry.getRegistry(host, port);
            String[] names = registry.list();
            Compute stub = (Compute) registry.lookup(names[0]);
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            BigDecimal res = stub.execute(Integer.parseInt(inFromUser.readLine()));
            System.out.println(res);
        } catch (NotBoundException | IOException e) {
            e.printStackTrace();
        }
    }
}

