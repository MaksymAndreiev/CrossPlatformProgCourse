package tcpWork;

import java.io.Serializable;

public class AddMetroCardOperation extends CardOperation{
    private MetroCard card = null;

    public AddMetroCardOperation() {
        card = new MetroCard();
    }

    public AddMetroCardOperation(String serialNumber, User user, String university) {
        card = new MetroCard();
        card.setSerialNumber(serialNumber);
        card.setUser(user);
        card.setUniversity(university);
        card.setBalance(0);
    }

    public MetroCard getCard() {
        return card;
    }
}
