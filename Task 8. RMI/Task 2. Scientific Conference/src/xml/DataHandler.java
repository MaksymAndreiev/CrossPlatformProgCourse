package xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import registry.Participant;

import java.util.ArrayList;

public class DataHandler extends DefaultHandler {

    private ArrayList<Participant> participants = null;
    private Participant tmp = null;
    private boolean isN, isS, isO, isR, isE;

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start Document Parsing Process ...");
        if (participants == null) {
            participants = new ArrayList<>();
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("\t" + participants);
        System.out.println("End Document Parsing Process ...");
    }

    @Override
    public void startElement(String uri, String name, String qName,
                             Attributes attrs) throws SAXException {
        switch (qName) {
            case "participant" -> tmp = new Participant();
            case "name" -> isN = true;
            case "surname" -> isS = true;
            case "organization" -> isO = true;
            case "report" -> isR = true;
            case "email" -> isE = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        switch (qName) {
            case "name" -> isN = false;
            case "surname" -> isS = false;
            case "organization" -> isO = false;
            case "report" -> isR = false;
            case "email" -> isE = false;
            case "participant" -> {
                participants.add(tmp);
                tmp=null;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String str = new String(ch, start, length).trim();
        if (isN) {
            tmp.setName(str);
        } else if (isS) {
            tmp.setSurname(str);
        } else if (isO){
            tmp.setOrganization(str);
        } else if (isR){
            tmp.setReport(str);
        } else if (isE){
            tmp.setEmail(str);
        }
    }

    @Override
    public void warning(SAXParseException e) throws SAXException {
        System.err.println("Warning " + e);
        System.err.println("line = " + e.getLineNumber() + ", col = " + e.getColumnNumber());
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.err.println("Error " + e);
        System.err.println("line = " + e.getLineNumber() + ", col = " + e.getColumnNumber());
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.err.println("Fatal Error " + e);
        System.err.println("line = " + e.getLineNumber() + ", col = " + e.getColumnNumber());
    }
}
