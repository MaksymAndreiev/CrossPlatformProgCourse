package tcpWork;

import java.util.ArrayList;

public class MetroCardBank {
    private ArrayList<MetroCard> store;

    public MetroCardBank() {
        store = new ArrayList<>();
    }

    public ArrayList<MetroCard> getStore() {
        return store;
    }

    public void setStore(ArrayList<MetroCard> store) {
        this.store = store;
    }

    public int findMetroCard(String serialNumber) {
        for (MetroCard card :
                store) {
            if (serialNumber.equals(card.getSerialNumber())) {
                return store.indexOf(card);
            }
        }
        return -1;
    }

    public int numCards() {
        return store.size();
    }

    public void addCard(MetroCard card) {
        store.add(card);
    }

    public boolean removeCard(String serialNumber) {
        for (MetroCard card :
                store) {
            if (serialNumber.equals(card.getSerialNumber())) {
                store.remove(card);
                return true;
            }
        }
        return false;
    }

    public boolean addMoney(String serialNumber, double money) {
        for (MetroCard card :
                store) {
            if (serialNumber.equals(card.getSerialNumber())) {
                card.setBalance(card.getBalance() + money);
                return true;
            }
        }
        return false;
    }

    public boolean getMoney(String serialNumber, double money) {
        for (MetroCard card :
                store) {
            if (serialNumber.equals(card.getSerialNumber())) {
                card.setBalance(card.getBalance() - money);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("List of metro cards:");
        for (MetroCard card :
                store) {
            stringBuilder.append("\n\n" + card);
        }
        return stringBuilder.toString();
    }

    public double getBalance(String serialNumber){
        for (MetroCard card :
                store) {
            if (serialNumber.equals(card.getSerialNumber())) {
                return card.getBalance();
            }
        }
        return -1;
    }
}
