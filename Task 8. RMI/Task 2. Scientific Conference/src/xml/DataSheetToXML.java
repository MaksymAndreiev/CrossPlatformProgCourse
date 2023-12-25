package xml;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import registry.Participant;

public class DataSheetToXML {

    public static void saveXMLDoc(Document doc, String fileName) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "Windows-1251");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(fileName.trim()));

        try {
            transformer.transform(source, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        System.out.println("File " + fileName.trim() + " saved!");

    }

    public static Document createDataSheetDOM(ArrayList<Participant> participants) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Element rootElement = doc.createElement("conference");
        doc.appendChild(rootElement);
        Element root = doc.createElement("participants");
        rootElement.appendChild(root);

        for (int i = 0; i < participants.size(); i++) {
            Element participant = doc.createElement("participant");
            root.appendChild(participant);
            Participant p = participants.get(i);
            Element name = doc.createElement("name");
            name.appendChild(doc.createTextNode(p.getName()+""));
            participant.appendChild(name);
            Element surname = doc.createElement("surname");
            surname.appendChild(doc.createTextNode(p.getSurname()+""));
            participant.appendChild(surname);
            Element organization = doc.createElement("organization");
            organization.appendChild(doc.createTextNode(p.getOrganization()+""));
            participant.appendChild(organization);
            Element report = doc.createElement("report");
            report.appendChild(doc.createTextNode(p.getReport()+""));
            participant.appendChild(report);
            Element email = doc.createElement("email");
            email.appendChild(doc.createTextNode(p.getEmail()+""));
            participant.appendChild(email);
        }
        return doc;
    }

}
