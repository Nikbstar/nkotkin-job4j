package ru.nik66.magnit;

import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class StoreXMLTest {

    @Test
    public void whenSaveToXMLFromList() throws Exception {
        File file = new File("my.xml");
        StoreXML storeXML = new StoreXML(file);
        storeXML.save(Arrays.asList(new Entry(1), new Entry(2)));
        String actual = new String(Files.readAllBytes(file.toPath()));
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<entries>\n    <entry>\n        <field>1</field>\n    </entry>\n    <entry>\n        <field>2</field>\n    </entry>\n</entries>\n";
        assertThat(actual, is(expected));
    }

}