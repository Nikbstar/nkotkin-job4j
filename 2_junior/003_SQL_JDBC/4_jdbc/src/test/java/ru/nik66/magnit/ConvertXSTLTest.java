package ru.nik66.magnit;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ConvertXSTLTest {

    @Test
    public void whenConvert() throws IOException {
        File source = new File("my.xml");
        File schema = new File("magnit.xsl");
        File result = new File("transformed.xml");
        StoreXML storeXML = new StoreXML(source);
        storeXML.save(Arrays.asList(new Entry(1), new Entry(2)));
        ConvertXSTL convert = new ConvertXSTL();
        convert.convert(source, result, schema);
        String actual = new String(Files.readAllBytes(result.toPath()));
        String expected = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><entries><entry field=\"1\"/><entry field=\"2\"/></entries>";
        assertThat(actual, is(expected));
    }
}