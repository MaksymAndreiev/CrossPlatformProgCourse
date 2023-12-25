package tcpWork;

import java.io.Serializable;

public class MetroCard implements Serializable {
    private String serialNumber;
    private User user;
    private String university;
    private double balance;

    public MetroCard() {
        //
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "№: " + serialNumber + "\n User: " + user + "\n University: " + university + "\n Balance:" + balance;
    }
}
