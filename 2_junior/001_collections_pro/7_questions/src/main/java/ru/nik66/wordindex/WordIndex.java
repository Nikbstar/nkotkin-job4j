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
            while (fileReader.ready()) {
                String[] words = fileReader.readLine().split("\\W+");
                for (String word : words) {
                    this.trie.insert(word.toLowerCase());
                }
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
    public Set<Integer> getIndexes4Word(String searchWord) {
        return this.trie.query(searchWord.toLowerCase());
    }

}
