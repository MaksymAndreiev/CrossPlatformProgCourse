package server;

import interfaces.Executable;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import static server.ServerFrame.textArea;

public class Server extends Thread{
    private ServerSocket serverSocket;
    public boolean flag;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        flag = true;
        textArea.append("The server is waiting for connections...\n");
    }

    @Override
    public void run() {
        while (flag){
            try {
                Socket clientSocket = serverSocket.accept();
                textArea.append("Connection starting execution...\n");
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                String classFile = (String) in.readObject();
                classFile = classFile.replaceFirst("client", "server");
                byte[] b = (byte[]) in.readObject();
                FileOutputStream fos = new FileOutputStream(classFile);
                fos.write(b);
                Executable ex = (Executable) in.readObject();
                double startTime = System.nanoTime();
                Object output = ex.execute();
                textArea.append("Connection [WORK DONE]\n");
                double endTime = System.nanoTime();
                double completionTime = endTime - startTime;
                ResultImpl r = new ResultImpl(output, completionTime);
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                classFile = classFile.replaceFirst("server", "client");
                out.writeObject(classFile);
                FileInputStream fis = new FileInputStream(classFile);
                byte[] bo = new byte[fis.available()];
                fis.read(bo);
                out.writeObject(bo);
                out.writeObject(r);
                textArea.append("Connection result sent. Finish connection...");
            } catch (SocketException e){
                ;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopServer(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
