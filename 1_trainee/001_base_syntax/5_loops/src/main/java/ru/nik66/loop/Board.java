package ru.nik66.loop;

/**
 * The class draws the chessboard in console by pseudo-graphic.
 */
public class Board {

    /**
     * The method create the string with chessboard by pseudo-graphic.
     * @param width board's width.
     * @param height board's height.
     * @return string with chessboard.
     */
    public String paint(int width, int height) {
        StringBuilder sb = new StringBuilder();
        String eol = System.lineSeparator();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if ((i + j) % 2 == 0) {
                    sb.append("X");
                } else {
                    sb.append(" ");
                }
            }
            sb.append(eol);
        }
        return sb.toString();
    }
    

}
