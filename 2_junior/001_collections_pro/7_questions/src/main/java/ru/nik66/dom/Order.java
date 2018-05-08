package ru.nik66.dom;

/**
 * Заявка.
 */
public class Order implements Comparable<Order> {

    /**
     * Уникальный ключ заявки.
     */
    private static int id;
    /**
     * Идентификатор ценной бумаги (эммитент).
     */
    private String book;
    /**
     * Тип заявки.
     * ADD - Добавление заявки.
     * DELETE - Удаление заявки.
     */
    private Type type;
    /**
     * Действие заявки.
     * BID - Заявка на продажу.
     * ASK - Заявка на покупку.
     */
    private Action action;
    /**
     * Цена на покупку/продажу ценной бумаги.
     */
    private double price;
    /**
     * Количество ценных бумаг, предложенных для покупки/продажи.
     */
    private int volume;

    /**
     * All arguments constructor.
     * @param book book.
     * @param type type.
     * @param action action.
     * @param price price.
     * @param volume volume.
     */
    public Order(String book, Type type, Action action, double price, int volume) {
        this.book = book;
        this.type = type;
        this.action = action;
        this.price = price;
        this.volume = volume;
        id++;
    }

    public int getId() {
        return id;
    }

    public String getBook() {
        return this.book;
    }

    public Type getType() {
        return this.type;
    }

    public Action getAction() {
        return this.action;
    }

    public double getPrice() {
        return this.price;
    }

    public int getVolume() {
        return this.volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public int compareTo(Order o) {
        return Double.compare(o.getPrice(), this.getPrice());
    }
}
