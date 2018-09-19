package ru.nik66.loop;

import java.util.function.BiPredicate;

/**
 * The class paints the triangles in console by pseudo-graphic.
 */
public class Paint {

    /**
     * The method returns a string with the triangle.
     * @param height the triangle's height.
     * @return string with pseudo-graphic.
     */
    public String pyramid(int height) {
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
        return this.loopBy(
                height,
                (height * 2) - 1,
                (row, column) -> ((column + 1 >= height - row) && (column + 1 <= height + row))
        );
    }

    /**
     * The method returns a string with the right side triangle.
     * @param height the triangle's height.
     * @return string with pseudo-graphic.
     */
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

    /**
     * The method returns a string with the left side triangle.
     * @param height the triangle's height.
     * @return string with pseudo-graphic.
     */
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

    /**
     * The method returns a string makes by predicate.
     * @param height triangle's height.
     * @param width triangle's width.
     * @param predicate condition for making the string.
     * @return the string with triangle.
     */
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
