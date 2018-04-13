package ru.nik66;

import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Deportaments sort.
 * Отсортировать департаменты [#34352]
 */
public class DepartmentsSort {

    private final String[] values;

    public DepartmentsSort(String[] values) {
        Set<String> tmp = new HashSet<>(Arrays.asList(values));
        for (String value : values) {
            StringBuilder sb = new StringBuilder();
            String[] separate = value.split("\\\\");
            sb.append(separate[0]);
            tmp.add(sb.toString());
            for (int i = 1; i < separate.length - 1; i++) {
                sb.append('\\').append(separate[i]);
                tmp.add(sb.toString());
            }
        }
        this.values = tmp.toArray(new String[tmp.size()]);
    }

    /**
     * Sort deportaments.
     * @return sorted deportaments.
     */
    public String[] sort() {
        Arrays.sort(this.values);
        return this.values;
    }

    /**
     * Reverse sort deportaments.
     * @return reverse sorted deportaments.
     */
    public String[] reverse() {
        Arrays.sort(this.values, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int minLen = Math.min(o1.length(), o2.length());
                int result = o1.length() - o2.length();
                for (int index = 0; index < minLen; index++) {
                    char o1Char = o1.charAt(index);
                    char o2Char = o2.charAt(index);
                    if (o2Char - o1Char != 0) {
                        result = o2Char - o1Char;
                        break;
                    }
                }
                return result;
            }
        });
        return this.values;
    }

}
