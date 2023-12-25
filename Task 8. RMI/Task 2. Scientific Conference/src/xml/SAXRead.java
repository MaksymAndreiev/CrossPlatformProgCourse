package xml;

import org.xml.sax.SAXException;
import registry.Participant;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SAXRead {

    public static ArrayList<Participant> XMLReadData(String fileName) {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        ArrayList<Participant> load = null;

        try {
            SAXParser saxParser = factory.newSAXParser();
            DataHandler handler = new DataHandler();
            InputStream xmlInput = new FileInputStream(fileName);
            saxParser.parse(xmlInput, handler);
            load = handler.getParticipants();
        } catch (SAXException | ParserConfigurationException | IOException e) {
            e.printStackTrace();
        }

        return load;
    }
}
