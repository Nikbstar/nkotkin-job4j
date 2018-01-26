package ru.nik66;

import java.util.ArrayList;

public class CoffeeMachine {

    private static final int[] COINS = {10, 5, 2, 1};

    public int[] changes(int value, int price) {
        ArrayList<Integer> changes = new ArrayList<>();
        int change = value - price;

        while (change != 0) {
            for (int coin : COINS) {
                if (change >= coin) {
                    changes.add(coin);
                    change -= coin;
                    break;
                }
            }
        }
        // List to array
        int[] result = new int[changes.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = changes.get(i).intValue();
        }

        return result;
    }

}
