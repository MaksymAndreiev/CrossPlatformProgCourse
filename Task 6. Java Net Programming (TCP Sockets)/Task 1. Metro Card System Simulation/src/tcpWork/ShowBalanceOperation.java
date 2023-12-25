package tcpWork;

public class ShowBalanceOperation extends CardOperation{
    private String serNum = null;
    private double balance = 0.0;

    public ShowBalanceOperation(String serNum) {
        this.serNum = serNum;
    }

    public String getSerNum() {
        return serNum;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return  "serial number " + serNum + '\'' +
                ", balance: " + balance;
    }
}
