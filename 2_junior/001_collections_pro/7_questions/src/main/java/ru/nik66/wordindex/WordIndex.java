package ru.nik66.wordindex;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

/**
 * Задачи ПИТЕР-СЕРВИС [#50316]
 */
public class WordIndex implements WIndexable {

    /**
     * Префиксное дерево для хранения словаря.
     */
    private final Trie trie = new Trie();

    /**
     * Загрузить файл с текстом.
     * Загружает файл и записывает все слова в префиксное дерефо.
     * @param fileName имя файла.
     */
    @Override
    public void loadFile(final String fileName) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(fileName))) {
            long index = 0;
            while (fileReader.ready()) {
                String line = fileReader.readLine();
                String[] words = line.split("\\W+");
                String[] delimiters = line.split("\\w+");
                for (int i = 0; i < words.length; i++) {
                    this.trie.insert(words[i].toLowerCase(), index);
                    index += words[i].length()
                            + ((i + 1 < delimiters.length) ? delimiters[i + 1].length() : 0);
                }
                index += System.lineSeparator().length();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Получить позиции слова в тексте.
     * Ищет слово в префиксном дереве и возвращает позиции его в тексте.
     * @param searchWord слово.
     * @return set позиций слова в тексте.
     */
    @Override
    public Set<Long> getIndexes4Word(final String searchWord) {
        return this.trie.query(searchWord.toLowerCase());
    }

}
