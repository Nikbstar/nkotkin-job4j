package ru.nik66;

import java.util.Comparator;

// Очень часто возникает ситуация, когда нужно сравнить два слова.
// У нас есть готовый метод String.compareTo.
// В это задании нужно создать подобный метод самому.
// Вам нужно реализовать компаратор для сравнения двух массивов символов.
// Необходимо реализовать поэлементное сравнение двух списков, т.е. сравниваем элементы
// двух списков, находящихся на одних и тех же позициях (по одним и тем же индексом).
// Сравнение в лексикографическом порядке.
// В этом задании нельзя использовать метод String.compateTo.
public class ListCompare implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        int leftLen = left.length();
        int rightLen = right.length();
        int result = leftLen - rightLen;
        int minLength = Math.min(leftLen, rightLen);
        for (int i = 0; i < minLength; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                result = left.charAt(i) - right.charAt(i);
                break;
            }
        }
        return result;
    }
}
