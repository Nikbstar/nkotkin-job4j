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
     * BID - Заявка на покупку.
     * ASK - Заявка на продажу.
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

    /**
     * уникальный ключ заявки.
     * @return id.
     */
    public int getId() {
        return id;
    }

    /**
     * идентификатор ценной бумаги.
     * @return book.
     */
    public String getBook() {
        return this.book;
    }

    /**
     * add/delete - выставить заявку на торги или снять.
     * @return Type.
     */
    public Type getType() {
        return this.type;
    }

    /**
     * bid/ask - заявка имеет два действия. Заявка на покупка
     * ценной бумаги или на продажу.
     * @return Action.
     */
    public Action getAction() {
        return this.action;
    }

    /**
     * цена, по которой мы ходим сделать действия покупки или
     * продажи.
     * @return price.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * количество акций, которые мы хотим продать или купить.
     * @return volume.
     */
    public int getVolume() {
        return this.volume;
    }

    /**
     * изменить количество акций у заявки.
     * @param volume новое значение.
     */
    public void setVolume(int volume) {
        this.volume = volume;
    }

    /**
     * изменить тип заявки.
     * @param type новый тип.
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Сравнивает заявки по убыванию цены для сортировки.
     * @param o заявка.
     * @return больше нуля если цена данной заявки меньше предложенной.
     */
    @Override
    public int compareTo(Order o) {
        return Double.compare(o.getPrice(), this.getPrice());
    }

}
