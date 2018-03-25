package ru.nik66;

import java.util.ArrayList;
import java.util.List;

// Вам необходимо создать класс ConvertList. Внутри методов использовать foreach.
// В нём написать 2 метода:
// public List<Integer> toList (int[][] array) {} - в метод приходит двумерный массив целых чисел, необходимо
// пройтись по всем элементам массива и добавить их в List<Integer>.
// public int[][] toArray (List<Integer> list, int rows) {} - метод toArray должен равномерно разбить лист на
// количество строк двумерного массива. В методе toArray должна быть проверка - если количество элементов не
// кратно количеству строк - оставшиеся значения в массиве заполнять нулями.
// Например в результате конвертации листа со значениями (1, 2, 3, 4, 5, 6, 7) с разбиением на 3 строки должен
// получиться двумерный массив {{1, 2, 3}, {4, 5, 6}, {7, 0, 0}}

public class ConvertList {

    public List<Integer> toList(int[][] array) {
        List<Integer> result = new ArrayList<>();
        for (int[] ints : array) {
            for (int i : ints) {
                result.add(i);
            }
        }
        return result;
    }

    public int[][] toArray(List<Integer> list, int rows) {
        int cols = (list.size() % rows == 0) ? list.size() / rows : list.size() / rows + 1;
        int[][] result = new int[rows][cols];
        int col = 0;
        int row = 0;
        for (Integer i : list) {
            result[row][col++] = i;
            if (col == cols) {
                col = 0;
                row++;
            }
        }
        return result;
    }

}
