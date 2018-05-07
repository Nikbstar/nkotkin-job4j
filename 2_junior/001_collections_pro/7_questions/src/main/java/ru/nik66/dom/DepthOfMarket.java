package ru.nik66.dom;

import java.util.ArrayList;
import java.util.List;

/**
 * Биржевой стакан.
 */
public class DepthOfMarket {

    /**
     * Лист со стаканами.
     */
    private List<Glass> glasses = new ArrayList<>();

    /**
     * Добавить заявку.
     * Ищет в листе стаканов стакан с нужным эмитунтом, если не находит то
     * создает новый. Если находит то добавляет к нему.
     * @param order заявка.
     * @return true если заявка добавлена.
     */
    private boolean addOrder(Order order) {
        boolean result = false;
        for (Glass glass : this.glasses) {
            if (order.getBook().equals(glass.getBook())) {
                glass.add(order);
                result = true;
                break;
            }
        }
        if (!result) {
            this.glasses.add(new Glass(order.getBook(), order));
            result = true;
        }
        return result;
    }

    /**
     * Удалить заявку.
     * Ищет в листе стаканов стакан с нужным эмитунтом, если находит то
     * пытается удалить нужную заявку.
     * @param order заявка.
     * @return true если заявка удалена успешно.
     */
    private boolean removeOrder(Order order) {
        boolean result = false;
        for (Glass glass : this.glasses) {
            if (order.getBook().equals(glass.getBook())) {
                result = glass.remove(order);
                break;
            }
        }
        return result;
    }

    /**
     * Обработчик заявки.
     * Проверяет что нужно сделать с заявкой, добавить или удалить.
     * @param order заявка.
     * @return true если тип заявки выполнен.
     */
    public boolean orderHandler(Order order) {
        boolean result = false;
        if (order.getType().equals(Type.ADD)) {
            result = this.addOrder(order);
        } else if (order.getType().equals(Type.DELETE)) {
            result = this.removeOrder(order);
        }

        return result;
    }

    /**
     * Печатает стакан эммитента.
     * @param book идентификатор ценной бумаги.
     */
    public void printGlass(String book) {
        for (Glass glass : glasses) {
            if (glass.getBook().equals(book)) {
                for (String s : glass.toStringList()) {
                    System.out.println(s);
                }
            }
        }
    }

    /**
     * For tests.
     */
    public List<Glass> getGlasses() {
        return this.glasses;
    }
}
