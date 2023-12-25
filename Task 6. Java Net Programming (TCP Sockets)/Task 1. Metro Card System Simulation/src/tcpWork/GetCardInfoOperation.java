package tcpWork;

import java.util.ArrayList;

public class GetCardInfoOperation extends  CardOperation {
    private String serialNumber;

    public GetCardInfoOperation(String serialNumber){
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }
}
