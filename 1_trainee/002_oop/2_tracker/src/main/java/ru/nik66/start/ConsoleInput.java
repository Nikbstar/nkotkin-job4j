package ru.nik66.start;

import java.util.List;
import java.util.Scanner;

public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    @Override
    public int ask(String question, List<Integer> range) {
        int key = Integer.valueOf(this.ask(question));
        if (range.contains(key)) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range.");
        }
    }

}
