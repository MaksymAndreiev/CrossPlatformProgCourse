package task0;

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

public class DOMParserTask {
    public static void main(String[] args) {
        Document doc = DOMParserTask.readXMLDoc("xmlOut/DTP.xml");
        DOMParserTask.showDOMTreeStructure(doc);
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

    private static Document readXMLDoc(String fileName) {
        Document doc = null;
        DocumentBuilder builder = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true);

        Schema schema = null;
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory schemaFactory = SchemaFactory.newInstance(language);
            schema = schemaFactory.newSchema(new File("DTP.xsd"));
        } catch (SAXException e) {
            e.printStackTrace();
        }
        factory.setSchema(schema);
        factory.setValidating(false);
        factory.setNamespaceAware(true);

        try {
            builder = factory.newDocumentBuilder();
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
}
