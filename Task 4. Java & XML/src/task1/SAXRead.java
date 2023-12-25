package task1;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SAXRead {

    public static DataSheet XMLReadData() {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        DataSheet dsh = null;

        try {
            SAXParser saxParser = factory.newSAXParser();
            Handler handler = new Handler();
            InputStream xmlInput = new FileInputStream("task1/data.xml");
            saxParser.parse(xmlInput, handler);
            dsh = handler.getDataSheet();
        } catch (SAXException | ParserConfigurationException | IOException e) { e.printStackTrace(); }

        return dsh;
    }

    public static void main(String[] args){
        DataSheet dsh = SAXRead.XMLReadData();
        System.out.println(dsh);
    }
}
