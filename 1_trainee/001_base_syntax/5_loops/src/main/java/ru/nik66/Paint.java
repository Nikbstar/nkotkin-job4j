package ru.nik66;

import java.util.function.BiPredicate;

public class Paint {

    public String pyramid(int h) {
//        StringBuilder sb = new StringBuilder();
//        String eol = System.lineSeparator();
//        int w = (h * 2) - 1;
//
//        for (int i = 0; i < h; i++) {
//            for (int j = 1; j <= w; j++) {
//                if ((j >= h - i) && (j <= h + i)) {
//                    sb.append("^");
//                } else {
//                    sb.append(" ");
//                }
//            }
//            sb.append(eol);
//        }
//        return sb.toString();
        return this.loopBy(h, (h * 2) - 1, (i, j) -> ((j + 1 >= h - i) && (j + 1 <= h + i)));
    }

    public String rightTrl(int height) {
//        StringBuilder sb = new StringBuilder();
//        int width = height;
//        for (int row = 0; row != height; row++) {
//            for (int column = 0; column != width; column++) {
//                if (row >= column) {
//                    sb.append("^");
//                } else {
//                    sb.append(" ");
//                }
//            }
//            sb.append(System.lineSeparator());
//        }
//        return sb.toString();
        return this.loopBy(height, height, (row, col) -> row >= col);
    }

    public String leftTrl(int height) {
//        StringBuilder sb = new StringBuilder();
//        int width = height;
//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
//                if (i >= width - j - 1) {
//                    sb.append("^");
//                } else {
//                    sb.append(" ");
//                }
//            }
//            sb.append(System.lineSeparator());
//        }
//        return sb.toString();
        return this.loopBy(height, height, (row, col) -> row >= height - col - 1);
    }

    private String loopBy(int height, int width, BiPredicate<Integer, Integer> predicate) {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int col = 0; col != width; col++) {
                if (predicate.test(row, col)) {
                    sb.append("^");
                } else {
                    sb.append(" ");
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

}
