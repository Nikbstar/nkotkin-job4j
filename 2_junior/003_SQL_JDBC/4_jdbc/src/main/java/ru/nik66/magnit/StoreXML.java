package ru.nik66.magnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StoreXML {

    private static final Logger LOG = LoggerFactory.getLogger(StoreXML.class);

    private final File target;

    public StoreXML(File target) {
        this.target = target;
    }

    public void save(List<Entry> list) {
        Entries entries = new Entries();
        entries.setEntries(list);
        try {
            JAXBContext context = JAXBContext.newInstance(Entries.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(entries, this.target);
        } catch (JAXBException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Entries {

        @XmlElement(name = "entry")
        private List<Entry> entries = new ArrayList<>();

        public List<Entry> getEntries() {
            return this.entries;
        }

        public void setEntries(List<Entry> entries) {
            this.entries = entries;
        }

    }

}
