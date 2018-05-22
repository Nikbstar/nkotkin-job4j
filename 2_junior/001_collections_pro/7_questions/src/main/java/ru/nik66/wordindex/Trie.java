package ru.nik66.wordindex;

import java.util.HashSet;
import java.util.Set;

/**
 * Префиксное дерево для индексации слов в тексте.
 */
public class Trie {

    /**
     * Корневой узел.
     */
    private final TrieNode root;
    /**
     * Статическое поле для подсчета индексов.
     */
    private static int index;

    /**
     * Стандартный конструктор.
     * Инициилизирует корень.
     */
    public Trie() {
        this.root = new TrieNode();
        Trie.index = 0;
    }

    /**
     * Поиск слова в дереве.
     * Перебераем все символы слова и сравниваем их с узлами дерева.
     * Если удалось дойти до конца, то смотрим в последнем
     * узлу Set с позициями и если он не пустой, то возвращаем его.
     * Если Set пустой или не удалось дойти до конца, то такого
     * слова нет в тексте и возвращаем null.
     * @param s слово.
     * @return Set позиций слова или null, если слова нет в тексте.
     */
    public Set<Integer> query(final String s) {
        TrieNode current = this.root;
        for (int i = 0; i < s.length(); i++) {
            if (current == null) {
                return null;
            }
            current = current.next(s.charAt(i));
        }
        return current.positions.size() > 0 ? current.positions : null;
    }

    /**
     * Вставить слово в дерево.
     * Пробегаем по символам слова и сравниваем их с ячейками в массиве,
     * если ячейки по коду символа равна null, то создаем в ней новую
     * ноду.
     * В конце слова в Set записываем позицию слова в файле.
     * @param s слово.
     */
    public void insert(final String s) {
        TrieNode current = this.root;
        for (int i = 0; i < s.length(); i++) {
            if (current.trieNodes[s.charAt(i) - 'a'] == null) {
                current.trieNodes[s.charAt(i) - 'a'] = new TrieNode();
            }
            current = current.next(s.charAt(i));
        }
        current.positions.add(Trie.index++);
    }

}

/**
 * Узел дерева.
 */
class TrieNode {

    /**
     * Массив на 26 английских символа.
     */
    public final TrieNode[] trieNodes = new TrieNode[26];
    /**
     * Сет для записи позиции слова в тексте.
     */
    public final Set<Integer> positions = new HashSet<>();

    /**
     * Поиск следующей ноды.
     * @param c символ.
     * @return следующую ноду из ячейки по коду символа.
     */
    public TrieNode next(final char c) {
        return this.trieNodes[c - 'a'];
    }

}