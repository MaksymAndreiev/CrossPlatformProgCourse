package task0;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class SAXTask2 {

    public static DataSheet XMLReadData(File file) {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        DataSheet dsh = null;

        try {
            SAXParser saxParser = factory.newSAXParser();
            SAXHandler handler = new SAXHandler();
            InputStream xmlInput = new FileInputStream(file);
            saxParser.parse(xmlInput, handler);
            dsh = handler.getDataSheet();
        } catch (SAXException | ParserConfigurationException | IOException e) { e.printStackTrace(); }

        return dsh;
    }

    public static void main(String[] args) {
        ArrayList<DataSheet> sheets = new ArrayList<>();
        File dir = new File(".");
        for (File file :
                dir.listFiles()) {
            if (file.isFile()){
                if (file.getName().matches(".*\\.xml")){
                    sheets.add(SAXTask2.XMLReadData(file));
                }
            }
        }
        SAXHandler [] saxHandlers = new SAXHandler[sheets.size()];
        for (int i = 0; i < saxHandlers.length; i++) {
            saxHandlers[i] = new SAXHandler();
            saxHandlers[i].setDataSheet(sheets.get(i));
        }
        DataSheetToXML.saveXMLDoc(DataSheetToXML.createDOM(DataSheetToXML.createDataSheetDOM(),saxHandlers), "xmlOut/DTP.xml");
    }

}
