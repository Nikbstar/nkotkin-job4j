package ru.nik66.dom;

import java.util.*;

/**
 * Стакан.
 */
public class Glass {

    /**
     * Эммитент.
     */
    private String book;
    /**
     * Предложения на покупку.
     */
    private Set<Order> bid = new TreeSet<>();
    /**
     * Предложения на продажу.
     */
    private Set<Order> ask = new TreeSet<>();

    /**
     * Конструктор инициализирует эммитент.
     * @param book эммитент.
     */
    public Glass(String book) {
        this.book = book;
    }

    /**
     * Конструктор инициализирует эммитент и добавляет заявку.
     * @param book эммитент.
     * @param order заявка.
     */
    public Glass(String book, Order order) {
        this(book);
        this.add(order);

    }

    /**
     * Вернуть эммитент.
     * @return String эммитент.
     */
    public String getBook() {
        return this.book;
    }

    /**
     * Добавить заявку.
     * Сначала проверяет, есть ли уже заявка с такой же ценой,
     * если да то увеличивает там volume.
     * Если нет то проверяет, есть ли в стакане заявка, противоположного
     * действия, удовлетворяющая по цене, если есть то объединяет их.
     * Если нет то добавляет новую заявку в стакан.
     * @param order Заявка.
     * @return true если заявка добавлени, объеденина или добавлена в другую.
     */
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

    /**
     * Удаляет заявку из стакана.
     * Проветяет на покупку или продажу заявка, и удаляет из
     * соответствующего списка.
     * @param order заявка.
     * @return true если заявка удалена.
     */
    public boolean remove(Order order) {
        boolean result = false;
        if (order.getAction().equals(Action.ASK)) {
            result = this.ask.remove(order);
        } else if (order.getAction().equals(Action.BID)) {
            result = this.bid.remove(order);
        }
        return result;
    }

    /**
     * Если уже есть заявка с такой же ценой, то увеличивает количество
     * акций на число данной заявки.
     * @param order заявка.
     * @return true, если такая заявка уже есть.
     */
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

    /**
     * Объеденяет заявки.
     * Объеденяет противоположные, подходящие по цене заявки
     * и проверяет, не уменьшилось ли количество акций до 0,
     * тогда удаляет заявку.
     * @param order заявка.
     * @return true если заявка объеденина.
     */
    private boolean merge(Order order) {
        boolean result = false;
        if (order.getAction().equals(Action.BID)) {
            Set<Order> forRemove = new HashSet<>();
            for (Order a : this.ask) {
                if (order.getPrice() >= a.getPrice()) {
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
                if (order.getPrice() <= b.getPrice()) {
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

    /**
     * уменьшает количество акций у объединяемых заявок.
     * @param newOrder первая заявка.
     * @param oldOrder вторая заявка.
     */
    private void mergeVolume(Order newOrder, Order oldOrder) {
        int count = Math.min(oldOrder.getVolume(), newOrder.getVolume());
        oldOrder.setVolume(oldOrder.getVolume() - count);
        newOrder.setVolume(newOrder.getVolume() - count);
    }

    /**
     * Возвращает лист строк, со всеми заявками в стакане.
     * @return String list.
     */
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