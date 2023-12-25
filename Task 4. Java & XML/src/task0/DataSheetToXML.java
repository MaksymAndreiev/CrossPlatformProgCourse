package task0;

import java.io.File;

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

import org.w3c.dom.*;

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

    public static Document createDataSheetDOM() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.newDocument();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Element rootElement = doc.createElement("dtpCardList");
        doc.appendChild(rootElement);

        return doc;
    }

    public static Document createDOM(Document doc, SAXHandler [] handlers){
        for (SAXHandler ds:
             handlers) {
            Element region = doc.createElement("region");
            Attr regAttr = doc.createAttribute("regName");
            regAttr.setValue(ds.getDataSheet().getName());
            region.setAttributeNode(regAttr);

            NodeList rootElement = doc.getElementsByTagName("dtpCardList");
            rootElement.item(0).appendChild(region);

            for (int i = 0; i < ds.getDataSheet().size(); i++) {
                Element tab = doc.createElement("tab");
                region.appendChild(tab);
                Data data = ds.getDataSheet().getDataItem(i);
                Element district = doc.createElement("district");
                tab.appendChild(district);
                district.appendChild(doc.createTextNode(data.getDistrict() + ""));
                Element COORD_L = doc.createElement("COORD_L");
                tab.appendChild(COORD_L);
                COORD_L.appendChild(doc.createTextNode(data.getCOORD_L() + ""));
                Element COORD_W = doc.createElement("COORD_W");
                tab.appendChild(COORD_W);
                COORD_W.appendChild(doc.createTextNode(data.getCOORD_W() + ""));
            }
        }
        return doc;
    }
}

