package ru.nik66.dom;

import java.util.*;

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
                this.ask.removeAll(forRemove);
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
                this.bid.removeAll(forRemove);
            }
        }
        return result;
    }

    private void mergeVolume(Order newOrder, Order oldOrder) {
        int count = Math.min(oldOrder.getVolume(), newOrder.getVolume());
        oldOrder.setVolume(oldOrder.getVolume() - count);
        newOrder.setVolume(newOrder.getVolume() - count);
    }

    public List<String> toStringList() {
        List<String> result = new ArrayList<>();
        Set<Order> tmp = new TreeSet<>();
        tmp.addAll(this.ask);
        tmp.addAll(this.bid);
        for (Order order : tmp) {
            String bid = order.getAction().equals(Action.BID) ? String.valueOf(order.getVolume()) : "";
            String ask = order.getAction().equals(Action.ASK) ? String.valueOf(order.getVolume()) : "";
            String price = String.valueOf(order.getPrice());
            result.add(String.format("%5s%7s%5s", bid, price, ask));
        }
        return result;
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