package ru.nik66.dom;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Glass {

    private String book;
    private Set<Order> bid = new TreeSet<>();
    private Set<Order> ask = new TreeSet<>();

    public Glass(String book) {
        this.book = book;
    }

    public Glass(String book, Order order) {
        this(book);
        this.add(order);

    }

    public String getBook() {
        return this.book;
    }

    public boolean add(Order order) {
        boolean result = addVolume(order);
        result = result || merge(order);
        if (!result && order.getAction().equals(Action.ASK)) {
            result = this.ask.add(order);
        }
        if (!result && order.getAction().equals(Action.BID)) {
            result = this.bid.add(order);
        }
        return result;
    }

    public boolean remove(Order order) {
        boolean result = false;
        if (order.getAction().equals(Action.ASK)) {
            result = this.ask.remove(order);
        } else if (order.getAction().equals(Action.BID)) {
            result = this.bid.remove(order);
        }
        return result;
    }

    private boolean addVolume(Order order) {
        boolean result = false;
        Set<Order> tmp;
        if (order.getAction().equals(Action.ASK)) {
            tmp = this.ask;
        } else {
            tmp = this.bid;
        }
        for (Order o : tmp) {
            if (o.getPrice() == order.getPrice()) {
                o.setVolume(o.getVolume() + order.getVolume());
                result = true;
                break;
            }
        }
        return result;
    }

    private boolean merge(Order order) {
        boolean result = false;
        if (order.getAction().equals(Action.BID)) {
            Set<Order> forRemove = new HashSet<>();
            for (Order a : this.ask) {
                if (order.getPrice() <= a.getPrice()) {
                    this.mergeVolume(order, a);
                    if (a.getVolume() == 0) {
                        forRemove.add(a);
                    }
                    if (order.getVolume() == 0) {
                        result = true;
                        break;
                    }
                }
            }
            if (forRemove.size() > 0) {
                this.bid.removeAll(forRemove);
            }
        } else {
            Set<Order> forRemove = new HashSet<>();
            for (Order b : this.bid) {
                if (order.getPrice() >= b.getPrice()) {
                    this.mergeVolume(order, b);
                    if (b.getVolume() == 0) {
                        forRemove.add(b);
                    }
                    if (order.getVolume() == 0) {
                        result = true;
                        break;
                    }
                }
            }
            if (forRemove.size() > 0) {
                this.ask.removeAll(forRemove);
            }
        }
        return result;
    }

    private void mergeVolume(Order newOrder, Order oldOrder) {
        int count = Math.min(oldOrder.getVolume(), newOrder.getVolume());
        oldOrder.setVolume(oldOrder.getVolume() - count);
        newOrder.setVolume(newOrder.getVolume() - count);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // TODO: вернуть строку со списком всех предложений стакана.
        return sb.toString();
    }

    /**
     * For tests
     */
    public Set<Order> getBid() {
        return this.bid;
    }

    public Set<Order> getAsk() {
        return this.ask;
    }
}