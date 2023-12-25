package task1;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class DOMReadVal {
    static Boolean flag;

    public static void main(String[] args) {
        flag = false;
        Document doc = DOMReadVal.readXMLDoc("task1/data.xml", flag);
        flag = true;
        doc = DOMReadVal.readXMLDoc("task1/data.xml", flag);
        for (int i = 6; i > 0; i--) {
            DOMReadVal.removeElement(doc, i);
        }
        DOMReadVal.replaceElementAt(doc, 0, DOMReadVal.newElement(doc, "15.03.2021", 1, 1));
        DOMReadVal.insertElementAt(doc, 1, DOMReadVal.newElement(doc, "15.03.2021", 2, 2));
        DOMReadVal.addElement(doc, DOMReadVal.newElement(doc, "15.03.2021", 3, 3));
        DOMSave.saveXMLDoc(doc, "task1/DOMOut.xml", flag);
    }

    private static void showDOMTreeStructure(Document doc) {
        Element root = doc.getDocumentElement();
        diveIn(root);
    }

    private static void diveIn(Node node) {
        System.out.println("Node name: " + node.getNodeName() + "   type: " + node.getNodeType() + "   value: " + node.getNodeValue() + "  has attr: " + node.hasAttributes());
        if (node.hasAttributes()) {
            NamedNodeMap attr = node.getAttributes();
            System.out.print("\t");
            for (int i = 0; i < attr.getLength(); i++) {
                System.out.print("  " + attr.item(i).getNodeName() + "=" + attr.item(i).getNodeValue());
            }
            System.out.println();
        }
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            diveIn(nodeList.item(i));
        }
    }

    private static Document readXMLDoc(String fileName, Boolean flag) {
        Document doc = null;
        DocumentBuilder builder = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);

        Schema schema = null;
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory schemaFactory = SchemaFactory.newInstance(language);
            schema = schemaFactory.newSchema(new File("task1/data.xsd"));
        } catch (SAXException e) {
            e.printStackTrace();
        }
        if (flag) {
            factory.setSchema(schema);
        }
        factory.setValidating(flag);
        factory.setNamespaceAware(flag);

        try {
            builder = factory.newDocumentBuilder();
            System.out.println("DOMParser is validating: " + builder.isValidating());
            System.out.println("DOMParser is namespace aware: " + builder.isNamespaceAware());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        try {
            doc = builder.parse(new FileInputStream(fileName));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }

        return doc;
    }

    private static Element newElement(Document doc, String date, double x, double y) {
        Element data = doc.createElement("data");
        Attr attr = doc.createAttribute("date");
        attr.setValue(date);
        data.setAttributeNode(attr);

        Element X = doc.createElement("x");
        X.appendChild(doc.createTextNode(String.valueOf(x)));
        data.appendChild(X);

        Element Y = doc.createElement("y");
        Y.appendChild(doc.createTextNode(String.valueOf(y)));
        data.appendChild(Y);
        return data;
    }

    private static void addElement(Document doc, Element data) {
        doc.getDocumentElement().appendChild(data);
    }

    private static void removeElement(Document doc, int pos) {
        Node el = doc.getDocumentElement().getElementsByTagName("data").item(pos);
        doc.getDocumentElement().removeChild(el);
    }

    private static void insertElementAt(Document doc, int pos, Node nd) {
        Node el = doc.getDocumentElement().getElementsByTagName("data").item(pos);
        doc.getDocumentElement().insertBefore(nd, el);
    }

    private static void replaceElementAt(Document doc, int pos, Node nd) {
        Node el = doc.getDocumentElement().getElementsByTagName("data").item(pos);
        doc.getDocumentElement().replaceChild(nd, el);
    }
}
