package ru.nik66.magnit;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class StoreSQLTest {

    @Test
    public void whenGenerateAndGetEntryListThenItReturns() {
        Properties properties = new Properties();
        properties.setProperty("driver-class-name", "org.sqlite.JDBC");
        properties.setProperty("url", "jdbc:sqlite:xml_xslt_optimization.db");
        try (StoreSQL storeSQL = new StoreSQL(properties)) {
            assertThat(storeSQL.init(), is(true));
            storeSQL.generate(2);
            List<Entry> actual = storeSQL.getEntryList();
            List<Entry> expected = Arrays.asList(new Entry(1), new Entry(2));
            assertThat(actual, is(expected));
        }
    }

}