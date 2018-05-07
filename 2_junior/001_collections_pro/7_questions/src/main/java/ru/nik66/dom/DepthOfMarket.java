package ru.nik66.dom;

import java.util.ArrayList;
import java.util.List;

public class DepthOfMarket {

    private List<Glass> glasses = new ArrayList<>();

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
     * For tests.
     */
    public List<Glass> getGlasses() {
        return this.glasses;
    }
}
