package ru.nik66;

public class Board {

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
