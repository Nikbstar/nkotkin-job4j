package ru.nik66.dom;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DepthOfMarkerTest {

    @Test
    public void whenHandleAddTypeOrderWithSamePriceThenVolumeIncrease() {
        DepthOfMarket dom = new DepthOfMarket();
        Order order = new Order("GAZ", Type.ADD, Action.ASK, 11d, 10);
        assertThat(dom.orderHandler(order), is(true));
        assertThat(dom.getGlasses().size(), is(1));
        assertThat(dom.getGlasses().get(0).getAsk().size(), is(1));
        assertThat(dom.getGlasses().get(0).getBid().size(), is(0));
        assertThat(dom.getGlasses().get(0).getBook(), is("GAZ"));
        Order tmp = dom.getGlasses().get(0).getAsk().iterator().next();
        assertThat(tmp.getVolume(), is(10));
        Order order1 = new Order("GAZ", Type.ADD, Action.ASK, 11d, 10);
        assertThat(dom.orderHandler(order1), is(true));
        assertThat(dom.getGlasses().size(), is(1));
        assertThat(dom.getGlasses().get(0).getAsk().size(), is(1));
        assertThat(dom.getGlasses().get(0).getBid().size(), is(0));
        assertThat(tmp.getVolume(), is(20));
    }

    @Test
    public void whenHandleAddTypeOrderWithOtherPriceThenIncreaseGlassSize() {
        DepthOfMarket dom = new DepthOfMarket();
        Order order = new Order("GAZ", Type.ADD, Action.ASK, 11d, 10);
        dom.orderHandler(order);
        Order order1 = new Order("GAZ", Type.ADD, Action.ASK, 10d, 10);
        assertThat(dom.orderHandler(order1), is(true));
        assertThat(dom.getGlasses().size(), is(1));
        assertThat(dom.getGlasses().get(0).getAsk().size(), is(2));
        assertThat(dom.getGlasses().get(0).getBid().size(), is(0));
    }

    @Test
    public void whenHandleAddTypeOrdersWithDifferentBooksThenCreateNewGlass() {
        DepthOfMarket dom = new DepthOfMarket();
        Order order = new Order("GAZ", Type.ADD, Action.ASK, 11d, 10);
        dom.orderHandler(order);
        Order order1 = new Order("LUK", Type.ADD, Action.BID, 10d, 10);
        assertThat(dom.orderHandler(order1), is(true));
        assertThat(dom.getGlasses().size(), is(2));
        assertThat(dom.getGlasses().get(0).getAsk().size(), is(1));
        assertThat(dom.getGlasses().get(1).getBid().size(), is(1));
    }
}
