package tcpWork;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread{
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;
    private boolean work = true;
    private MetroCardBank bank = null;
    private Socket socket = null;

    public ClientHandler(MetroCardBank bank, Socket socket) {
        this.bank = bank;
        this.socket = socket;
        this.work = true;
        try {
            this.is = new ObjectInputStream(socket.getInputStream());
            this.os = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e){
            System.out.println("Error: " + e);
        }
    }

    private void processOperation(Object obj) throws IOException, ClassNotFoundException{
        if (obj instanceof StopOperation){
            finish();
        } else if (obj instanceof AddMetroCardOperation){
            addCard(obj);
        } else if (obj instanceof AddMoneyOperation){
            addMoney(obj);
        } else if (obj instanceof PayMoneyOperation){
            payMoney(obj);
        } else if (obj instanceof RemoveCardOperation){
            removeCard(obj);
        } else if (obj instanceof ShowBalanceOperation){
            showBalance(obj);
        } else if (obj instanceof GetCardInfoOperation){
            getInfo(obj);
        } else {
            error();
        }
    }

    private void error() throws IOException {
        os.writeObject("Bad Operation");
        os.flush();
    }

    private void showBalance(Object obj) throws IOException, ClassNotFoundException{
        ShowBalanceOperation op = (ShowBalanceOperation) obj;
        int ind = bank.findMetroCard(op.getSerNum());
        if (ind >= 0){
            os.writeObject("Card: " + op.getSerNum() + " balance: " + bank.getStore().get(ind).getBalance() +
                    " user " + bank.getStore().get(ind).getUser() + " university " + bank.getStore().get(ind).getUniversity());
            os.flush();
        } else {
            os.writeObject("Cannot Show Balance for Card: " + op.getSerNum());
        }
    }

    private void removeCard(Object obj) throws IOException, ClassNotFoundException{
        RemoveCardOperation op = (RemoveCardOperation) obj;
        boolean res = bank.removeCard(op.getSerNum());
        if (res){
            os.writeObject("Metro Card Successfully Remove: " + op.getSerNum());
        } else {
            os.writeObject("Cannot Pay Money");
        }
        os.flush();
    }

    private void payMoney(Object obj) throws IOException, ClassNotFoundException{
        PayMoneyOperation op = (PayMoneyOperation) obj;
        boolean res = bank.getMoney(op.getSerNum(), op.getMoney());
        if (res){
            os.writeObject("Money Payed");
        } else {
            os.writeObject("Cannot Pay Money");
        }
        os.flush();
    }

    private void addMoney(Object obj) throws IOException, ClassNotFoundException{
        AddMoneyOperation op = (AddMoneyOperation) obj;
        boolean res = bank.addMoney(op.getSerNum(), op.getMoney());
        if (res) {
            os.writeObject("Balance Added");
        } else {
            os.writeObject("Cannot Balance Added");
        }
        os.flush();
    }

    private void addCard(Object obj) throws IOException, ClassNotFoundException{
        bank.addCard(((AddMetroCardOperation)obj).getCard());
        os.writeObject("Card Added");
        os.flush();
    }

    private void getInfo(Object obj) throws IOException {
        System.out.println("aaa");
        int index = bank.findMetroCard(((GetCardInfoOperation)obj).getSerialNumber());
        MetroCard metroCard = bank.getStore().get(index);
        os.writeObject(metroCard);
        os.flush();
    }

    private void finish() throws IOException {
        work = false;
        os.writeObject("Finish Work " + socket);
        os.flush();
    }

    @Override
    public void run() {
        synchronized (bank){
            System.out.println("Client Handler Started for: " + socket);
            while (work){
                Object obj;
                try {
                    obj = is.readObject();
                    processOperation(obj);
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Error: " + e);
                }
            }
            try {
                System.out.println("Client Handler Stopped for: " + socket);
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
