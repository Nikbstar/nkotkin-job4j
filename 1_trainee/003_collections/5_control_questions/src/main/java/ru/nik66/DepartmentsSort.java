package ru.nik66;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DepartmentsSort {

    private final List<String> values;

    public DepartmentsSort(String[] values) {
        this.values = new ArrayList<>();
        // Пропускаем повторяющиеся депортаменты.
        for (int i = 0; i < values.length; i++) {
            if (!checkValue(values[i], values, i + 1)) {
                this.values.add(values[i]);
            }
        }
    }

    // Ищем строки с подстрокой.
    // Метод работает только с отсортированным массивом (как в задании),
    // если массив неотсортирован, то нужно проходить по всему массиву, пропуская position.
    private boolean checkValue(String subStr, String[] values, int position) {
        boolean result = false;
        for (int i = position; i < values.length; i++) {
            if (values[i].contains(subStr)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public String[] sort() {
        Collections.sort(this.values);
        this.addDeps();
        return this.values.toArray(new String[this.values.size()]);
    }

    public String[] reverse() {
        this.values.sort(Collections.reverseOrder());
        this.addDeps();
        return this.values.toArray(new String[this.values.size()]);
    }

    // Добавляем в наш лист отсутствующие депортаменты
    private void addDeps() {
        String lvlOne = "";
        String lvlTwo = "";
        for (int i = 0; i < this.values.size(); i++) {
            String[] split = this.values.get(i).split("\\\\");
            // Если в листе попался депортамент первого уровня то запоминаем его
            if (split.length == 1) {
                lvlOne = this.values.get(i);
            // Если попался депортамент второго уровня то сравниваем департаменты первого уровня
            // и при необходимост добавляем
            } else if (split.length == 2) {
                lvlTwo = this.values.get(i);
                if (!lvlOne.equals(split[0])) {
                    lvlOne = split[0];
                    this.values.add(i, split[0]);
                    i++;
                }
            // Если попалмя депортамент третьего уровня то сравниваем перыве и вторые уровни
            } else if (split.length == 3) {
                // Можно убрать дублирующий код и поставить i-- в конце, чтобы перепроверить
                // депортамент первого уровня, но тогда будут лишние пробеги и двойная проверка
                // депортаментов второго уровня.
                if (!lvlOne.equals(split[0])) {
                    lvlOne = split[0];
                    this.values.add(i, split[0]);
                    i++;
                }
                String tmpLvlTwo = String.format("%s\\%s", split[0], split[1]);
                if (!lvlTwo.equals(tmpLvlTwo)) {
                    lvlTwo = tmpLvlTwo;
                    this.values.add(i, tmpLvlTwo);
                    i++;
                }
            }
        }
    }

}
