package task0;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXHandler extends DefaultHandler {
    private DataSheet datasheet = null;
    private Data tmpData = null;
    private boolean LFlag, WFlag, isDistrict, isRegName;

    public DataSheet getDataSheet() {
        return datasheet;
    }

    public void setDataSheet(DataSheet dsh) {
        this.datasheet = dsh;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start Document Parsing Process ...");
        if (datasheet == null) {
            datasheet = new DataSheet();
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("\tThe Data Structure is Created ...");
        System.out.println("\t" + datasheet);
        System.out.println("End Document Parsing Process ...");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("regName")) {
            isRegName = true;
        } else if (qName.equals("district")) {
            tmpData = new Data();
            isDistrict = true;
        } else if (qName.equals("COORD_L")) {
            LFlag = true;
        } else if (qName.equals("COORD_W")) {
            WFlag = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("district")) {
            isDistrict = false;
        } else if (qName.equals("COORD_L")) {
            LFlag = false;
        } else if (qName.equals("COORD_W")) {
            WFlag = false;

        } else if (qName.equals("regName")) {
            isRegName = false;
        } else if (qName.equals("tab")){
            datasheet.addDataItem(tmpData);
            tmpData = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String str = new String(ch, start, length).trim();
        if (isRegName) {
            datasheet.setName(str);
        } else if (isDistrict) {
            tmpData.setDistrict(str);
        } else if (LFlag) {
            tmpData.setCOORD_L(Double.parseDouble(str));
        } else if (WFlag) {
            tmpData.setCOORD_W(Double.parseDouble(str));
        }
    }
}
