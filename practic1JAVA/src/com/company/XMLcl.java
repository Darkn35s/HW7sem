package com.company;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLcl {
    private String absolute_path;
    private final static ArrayList<Data> dataset = new ArrayList<>();

    public void WriteToXML(String absolute_path) throws ParserConfigurationException {
        DocumentBuilderFactory dbf;
        DocumentBuilder db ;
        Document doc;
        this.absolute_path=absolute_path;
        dbf = DocumentBuilderFactory.newInstance();
        db  = dbf.newDocumentBuilder();
        doc = db.newDocument();
        Element root = doc.createElement("stack");

        Element user = doc.createElement("data");
        user.setAttribute ("data1", "some_data");
        user.setAttribute ("data2", "some_data2");
        root.appendChild(user);
        doc.appendChild(root);
        writeDocument(doc, absolute_path);
    }
    private void writeDocument(Document document, String path) throws TransformerFactoryConfigurationError
    {
        Transformer trf = null;
        DOMSource src = null;
        FileOutputStream fos = null;
        try {
            trf = TransformerFactory.newInstance().newTransformer();
            src = new DOMSource(document);
            fos = new FileOutputStream(path);

            StreamResult result = new StreamResult(fos);
            trf.transform(src, result);
        } catch (TransformerException e) {
            e.printStackTrace(System.out);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    public void ReadFromXML()throws ParserConfigurationException, SAXException, IOException{
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        XMLHandler handler = new XMLHandler();
        parser.parse(new File(absolute_path), handler);

        for (Data data : dataset)
            System.out.println(String.format("data1: %s, data2: %s", data.getData1(), data.getData2()));

    }

    private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes){
            if (qName.equals("data")) {
                String name = attributes.getValue("data1");
                String job = attributes.getValue("data2");
                dataset.add(new Data(name, job));
            }
        }
    }
    public void DeleteXML(){
        File xml_file = new File(absolute_path);
        xml_file.delete();
    }

}

class Data {
    private String data1, data2;

    public Data(String data1, String data2) {
        this.data1 = data1;
        this.data2 = data2;
    }

    public String getData1() {
        return data1;
    }

    public String getData2() {
        return data2;
    }
}
