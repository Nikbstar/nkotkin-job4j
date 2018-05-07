package ru.nik66.dom;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DepthOfMarkerTest {

    @Test
    public void whenHandleAddTypeOrder() {
        Order order = new Order("GAZ", Type.ADD, Action.ASK, 11d, 10);
        DepthOfMarket dom = new DepthOfMarket();
        assertThat(dom.orderHandler(order), is(true));
        assertThat(dom.getGlasses().size(), is(1));
        assertThat(dom.getGlasses().get(0).getAsk().size(), is(1));
        assertThat(dom.getGlasses().get(0).getBid().size(), is(0));
        assertThat(dom.getGlasses().get(0).getBook(), is("GAZ"));
        // TODO: если при добвалении с такой ценой уже есть заявки, то плюсонуть volume
        Order order1 = new Order("GAZ", Type.ADD, Action.ASK, 11d, 10);
    }

}
