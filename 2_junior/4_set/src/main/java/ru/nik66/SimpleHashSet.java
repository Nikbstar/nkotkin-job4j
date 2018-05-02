package ru.nik66;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple set hash table based.
 * @param <E> - element type
 */
public class SimpleHashSet<E> implements Iterable<E> {

    /**
     * Default hashTable array length;
     */
    private static final int DEFAULT_CAPACITY = 16;
    /**
     * Процент заполнения массива hashTable, при котором будет происходить увеличение.
     */
    private static final float LOAD_FACTOR = 0.75f;

    /**
     * Количество элементов в коллекции.
     */
    private int size;
    /**
     * Проверка на изменение коллекции.
     * Если в коллекции происходит какое лиюо изменение (удаление
     * или добавление нового элемента), то modCount увеличивается на 1.
     */
    private int modCount;
    /**
     * Количество элементов, про котором происходит увелиение массива hashTable.
     */
    private float threshold;
    /**
     * Массив для хранения контейнеров с эементами.
     */
    private Node<E>[] hashTable;

    /**
     * Конструктор, в котором устанавивается размер массива hashTable.
     * @param capacity - hash table length.
     */
    public SimpleHashSet(int capacity) {
        this.size = 0;
        this.modCount = 0;
        this.hashTable = new Node[capacity];
        this.threshold = this.hashTable.length * LOAD_FACTOR;
    }

    /**
     * Стандартный конструктор, создающий массив на 16 элементов.
     */
    public SimpleHashSet() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Add element into collection.
     * Если количество элементов больше чем threshold, то массив удваивается.
     * Далее генерируется индекс, указывающий на ячейку в массиве, в которой
     * будет храниться контейнер с элементом.
     * Если эта ячейка пустая то в ней создается нода.
     * Если подобный элемент уже есть в ноде до ничего не делаем, иначе
     * произошла коллизия и мы к ноде добавляем еще один элемент.
     * @param e элемент
     * @return true если добавление элемента прошло успешно.
     */
    public boolean add(E e) {
        if (this.size() + 1 >= this.threshold) {
            increaseArrayLength();
        }
        int index = this.hash(e);
        if (this.hashTable[index] == null) {
            this.hashTable[index] = new Node<>();
        }
        if (!this.hashTable[index].getElements().contains(e)) {
            this.hashTable[index].getElements().add(e);
            this.size++;
            this.modCount++;
        }
        return true;
    }

    /**
     * По хэш коду определяет в какой ячейке хранится элемент
     * и там его ищет.
     * @param e элемент.
     * @return true если элемент найден.
     */
    public boolean contains(E e) {
        int index = this.hash(e);
        boolean result = false;
        if (this.hashTable[index] != null) {
            SimpleLinkedList<E> elements = this.hashTable[index].getElements();
            if (elements.size() == 1 && e.equals(elements.get(0))) {
                result = true;
            } else {
                for (E element : elements) {
                    if (e.equals(element)) {
                        elements.remove(e);
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Удалить элемент из SimpleSet.
     * Определяет ячейку элемента по хэш коду, если в этой ячейке
     * один элемент, то удаляет его, если несколько то ищет нужный
     * и удаляет.
     * @param e элемент.
     * @return true если удалось удалить элемент.
     */
    public boolean remove(E e) {
        int index = this.hash(e);
        boolean result = false;
        if (this.hashTable[index] != null) {
            SimpleLinkedList<E> elements = this.hashTable[index].getElements();
            if (elements.size() == 1 && e.equals(elements.get(0))) {
                elements.removeFirst();
                this.size--;
                this.modCount++;
                result = true;
            } else {
                for (E element : elements) {
                    if (e.equals(element)) {
                        elements.remove(e);
                        this.size--;
                        this.modCount++;
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    /**
     * Возвращает количество элементов в коллекции.
     * @return количество элементов.
     */
    public int size() {
        return this.size;
    }

    /**
     * Увеличивает размер массива hashTable и threshold в 2 раза
     * и копирует в новый массив все элементы.
     */
    private void increaseArrayLength() {
        this.threshold *= 2;
        Node<E>[] oldHashTable = this.hashTable;
        this.hashTable = new Node[oldHashTable.length * 2];
        this.size = 0;
        for (Node<E> node : oldHashTable) {
            if (node != null) {
                for (E e : node.getElements()) {
                    this.add(e);
                }
            }
        }
    }

    /**
     * По хэш коду генерирует номер ячейки в которую необходимо положить
     * элемент.
     * @param e - элемент.
     * @return - индекс ячйки.
     */
    private int hash(E e) {
        int hash = 31 * 17 + e.hashCode();
        return Math.abs(hash) % this.hashTable.length;
    }

    /**
     * Iterator.
     * @return iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int index = findCell(0);
            private int expectedModCount = modCount;
            Iterator<E> subIterator = (index == -1) ? null : hashTable[this.index].getElements().iterator();

            private int findCell(int start) {
                int result = -1;
                for (int i = start; i < hashTable.length; i++) {
                    if (hashTable[i] != null) {
                        result = i;
                        break;
                    }
                }
                return result;
            }

            @Override
            public boolean hasNext() {
                return (this.index != -1)
                        && (this.subIterator.hasNext()
                                || (!this.subIterator.hasNext() && (findCell(this.index + 1) != -1)));
            }

            @Override
            public E next() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (this.index == -1) {
                    throw new NoSuchElementException();
                }
                if (!this.subIterator.hasNext()) {
                    this.index = findCell(this.index + 1);
                    this.subIterator = hashTable[this.index].getElements().iterator();
                }
                return subIterator.next();
            }
        };
    }

    /**
     * При коллизии, в одну ячейку массива hashTable засовывает несколько
     * элементов.
     * @param <E>
     */
    private static class Node<E> {

        private SimpleLinkedList<E> elements;

        Node() {
            this.elements = new SimpleLinkedList<>();
        }

        public SimpleLinkedList<E> getElements() {
            return this.elements;
        }
    }

    /**
     * For tests.
     * @return hashTable Length.
     */
    int getHashTableLength() {
        return this.hashTable.length;
    }
    float getThreshold() {
        return this.threshold;
    }

}
