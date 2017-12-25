package ru.nik66;

public class Paint {

    public String pyramid(int h) {
        StringBuilder sb = new StringBuilder();
        String eol = System.lineSeparator();
        int w = (h * 2) - 1;

        for (int i = 0; i < h; i++) {
            for (int j = 1; j <= w; j++) {
                if ((j >= h - i) && (j <= h + i)) {
                    sb.append("^");
                } else {
                    sb.append(" ");
                }
            }
            sb.append(eol);
        }
        
        
        // TODO
        return sb.toString();
    }

}
