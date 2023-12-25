package task1;

import com.sun.org.apache.xerces.internal.impl.Constants;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;

public class SAXReadVal {

    public static DataSheet XMLReadData() {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        Schema schema = null;
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory schemaFactory = SchemaFactory.newInstance(language);
            schema = schemaFactory.newSchema(new File("task1/data.xsd"));
        } catch (SAXException e) {
            e.printStackTrace();
        }
        factory.setSchema(schema);
        factory.setValidating(true);
        factory.setNamespaceAware(true);

        DataSheet dsh = null;

        try {
            SAXParser saxParser = factory.newSAXParser();
            System.out.println("SAXParser is validating: " + saxParser.isValidating());
            System.out.println("SAXParser is namespace aware: " + saxParser.isNamespaceAware());
            DataHandler handler = new DataHandler();
            InputStream xmlInput = new FileInputStream("task1/data.xml");
            saxParser.parse(xmlInput, handler);
            dsh = handler.getDataSheet();
        } catch (SAXException | ParserConfigurationException | IOException e) { e.printStackTrace(); }

        return dsh;
    }

    public static void main(String[] args){
        DataSheet dsh = SAXReadVal.XMLReadData();
        System.out.println(dsh);
        DataAnalyzer da = new DataAnalyzer();
        da.setDatasheet(dsh);
        da.Analyze();
        System.out.println(da);
    }
}
