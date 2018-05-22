package ru.nik66.wordindex;

import java.util.Set;

public interface WIndexable {

    public void loadFile(String fileName);

    public Set<Integer> getIndexes4Word(String searchWord);

}
