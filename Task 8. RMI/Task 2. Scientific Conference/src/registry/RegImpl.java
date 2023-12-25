package registry;

import client.Client;
import server.ConferenceServer;


import java.rmi.RemoteException;
import java.util.ArrayList;

import static server.ConferenceServer.textArea;


public class RegImpl implements Registerable {
    private ArrayList<Participant> regParticipant;

    public RegImpl() {
        regParticipant = new ArrayList<>();
    }

    public int registry(Participant participant) throws RemoteException {
        regParticipant.add(participant);
        String userString = "\n"+String.valueOf(regParticipant.indexOf(participant) + 1) + ") " + participant.toString();
        textArea.append(userString);
        ConferenceServer.partField.setText(String.valueOf(regParticipant.size()));
        if (Client.partField !=null || Client.hostField != null) {
            Client.partField.setText(String.valueOf(regParticipant.size()));
            ConferenceServer.hostField.setText(Client.hostField.getText());
        }
        return regParticipant.size();
    }

    @Override
    public void getInfo() throws RemoteException {
        StringBuilder result = new StringBuilder();
        for (Participant user :
                regParticipant) {
            result.append("\n").append(regParticipant.indexOf(user) + 1).append(") ").append(user.toString());
        }
        textArea.append(String.valueOf(result));
    }

    public void setRegParticipant(ArrayList<Participant> regParticipant) {
        if (this.regParticipant.size() == 0) {
            this.regParticipant = regParticipant;
        } else {
            this.regParticipant.addAll(regParticipant);
        }
    }

    public ArrayList<Participant> getRegParticipant() {
        return regParticipant;
    }
}

