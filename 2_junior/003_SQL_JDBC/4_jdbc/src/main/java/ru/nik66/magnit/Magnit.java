package ru.nik66.magnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class Magnit {

    private static final Logger LOG = LoggerFactory.getLogger(Magnit.class);

    private File schema;

    public Magnit(File schema) {
        this.schema = schema;
    }

    public long sum(int n) {
        long result = 0;
        Properties properties = new Properties();
        properties.setProperty("driver-class-name", "org.sqlite.JDBC");
        properties.setProperty("url", "jdbc:sqlite:xml_xslt_optimization.db");
        try (StoreSQL sql = new StoreSQL(properties)) {
            if (sql.init()) {
                sql.generate(n);
                File source = new File("my.xml");
                StoreXML xml = new StoreXML(source);
                xml.save(sql.getEntryList());
                ConvertXSTL convert = new ConvertXSTL();
                File transformed = new File("transformed.xml");
                convert.convert(source, transformed, this.schema);
                SAXParserFactory parserFactory = SAXParserFactory.newInstance();
                SAXParser parser = parserFactory.newSAXParser();
                Handler handler = new Handler();
                parser.parse(transformed, handler);
                result = handler.getSum();
            }
        } catch (SAXException | ParserConfigurationException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    public static class Handler extends DefaultHandler {

        private long sum = 0;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("entry")) {
                this.sum += Long.parseLong(attributes.getValue("field"));
            }
        }
        public long getSum() {
            return this.sum;
        }
    }

}
