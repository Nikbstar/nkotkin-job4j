package ru.nik66.pseudo;

public class Square implements Shape {

    @Override
    public String draw() {
        StringBuilder sb = new StringBuilder();
        String eol = System.lineSeparator();
        sb.append("++++").append(eol);
        sb.append("+  +").append(eol);
        sb.append("+  +").append(eol);
        sb.append("++++").append(eol);
        return sb.toString();
    }

}
