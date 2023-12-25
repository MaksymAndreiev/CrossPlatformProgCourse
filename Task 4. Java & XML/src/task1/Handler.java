package task1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.text.Document;

public class Handler extends DefaultHandler {

    private DataSheet datasheet = null;
    private Data tmpData = null;
    private boolean isX, isY;
    private double sumX = 0, sumY = 0, sumX2 = 0, sumXY = 0;
    private double k, b;

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
            datasheet.setName("New DataSheet");
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("\tThe Data Structure is Created ...");
        System.out.println("\t" + datasheet);
        System.out.println("End Document Parsing Process ...");
    }

    @Override
    public void startElement(String uri, String name, String qName,
                             Attributes attrs) throws SAXException {
        if (qName.equals("data")) {
            tmpData = new Data();
            if (attrs.getLength() > 0) {
                tmpData.setDate(attrs.getValue(0));
            }
        } else if (qName.equals("x")) {
            isX = true;
        } else if (qName.equals("y")) {
            isY = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (qName.equals("x")) {
            isX = false;
        } else if (qName.equals("y")) {
            isY = false;
        } else if (qName.equals("data")) {
            datasheet.addDataItem(tmpData);
            sumX += tmpData.getX();
            sumY += tmpData.getY();
            sumX2 += tmpData.getX() * tmpData.getX();
            sumXY += tmpData.getX() * tmpData.getY();
            int num = datasheet.size();
            k = (sumXY - sumX * sumY / num) / (sumX2 - sumX * sumX / num);
            b = sumY / num - k * sumX / num;
            System.out.println("[k=" + k + ", b=" + b + "]");
            tmpData = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String str = new String(ch, start, length).trim();
        if (isX) {
            tmpData.setX(Double.parseDouble(str));
        } else if (isY) {
            tmpData.setY(Double.parseDouble(str));
        }

    }

}