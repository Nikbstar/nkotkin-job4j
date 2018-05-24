package ru.nik66.wordindex;

import java.util.Set;

public interface WIndexable {

    void loadFile(String fileName);

    Set<Long> getIndexes4Word(String searchWord);

}
