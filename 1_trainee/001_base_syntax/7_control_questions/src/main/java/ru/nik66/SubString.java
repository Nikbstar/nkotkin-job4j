package ru.nik66;

public class SubString {

    public boolean contains(String origin, String sub) {
        char[] originArray = origin.toCharArray();
        char[] subArray = sub.toCharArray();
        boolean result = false;
        int subIterator = 0;

        for (int originIterator = 0; originIterator <= originArray.length - subArray.length; originIterator++) {
            while (subIterator < subArray.length) {
                if (originArray[originIterator + subIterator] == subArray[subIterator]) {
                    subIterator++;
                } else {
                    subIterator = 0;
                    break;
                }
            }
            if (subIterator == subArray.length) {
                result = true;
                break;
            }
        }

        return result;
    }

}
