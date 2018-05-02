package ru.nik66;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Simple map hash table based
 * @param <K> key
 * @param <V> value
 */
public class SimpleHashMap<K, V> implements Iterable<V> {

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
     * Количество элементов, по котором происходит увелиение массива hashTable.
     */
    private float threshold;
    /**
     * Массив для хранения контейнеров с эементами.
     */
    private Node<K, V>[] hashTable;

    /**
     * Конструктор с параметром, указывающим на размер массива hashTable.
     * @param capacity - hashTable.length
     */
    public SimpleHashMap(int capacity) {
        this.size = 0;
        this.modCount = 0;
        this.hashTable = new Node[capacity];
        this.threshold = this.hashTable.length * LOAD_FACTOR;
    }

    /**
     * Конструктор устанавливает размер массива hashTable = 16.
     */
    public SimpleHashMap() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Вставка элементов в мапу.
     * Если количество элементов больше чем threshold, то массив удваивается.
     * Далее по хэшкоду ключа находится нужная ячейка в массиве и если она пустая,
     * то в ней создается новая нода с ключем и значением.
     * Если нет, то проверяется есть ли в этой ячейке такой же ключ.
     * Если ключ найден, то заменяется значение этого ключа на новое, если нет
     * то произошла коллизия и в ячейку дополнительно добавляется новая нода
     * (в поле next последней добавленной ноды записывается ссылка на новую ноду.
     * @param k ключ.
     * @param v значение.
     * @return true если добавление прошло успешно.
     */
    public boolean insert(K k, V v) {
        boolean result;
        if (this.size() + 1 >= this.threshold) {
            increaseArrayLength();
        }
        int index = this.hash(k);
        if (this.hashTable[index] == null) {
            this.hashTable[index] = new Node<>(k, v);
            this.size++;
            this.modCount++;
            result = true;
        } else if (this.containsKey(k)) {
            result = updateNode(k, v);
        } else {
            result = addNode(new Node<>(k, v));
        }
        return result;
    }

    /**
     * Получает значение по ключу.
     * По хешКоду ключа находит нужную ячейку, и если она не пуста
     * то ищет в ней ноду с нужным ключем. При нахждении возвращает
     * значение (value) этой ноды. Если ничего не найдено, то
     * возвращает null.
     * @param k ключ.
     * @return значение.
     */
    public V get(K k) {
        V result = null;
        int index = this.hash(k);
        Node<K, V> node = this.hashTable[index];
        while (node != null) {
            if (k.equals(node.getKey())) {
                result = node.getValue();
                break;
            }
            node = node.getNext();
        }
        return result;
    }

    /**
     * Удаляет элемент (ключ + значение) по ключу.
     * По хэшкоду ключа находит необходимую ячейку и если она не пуста,
     * и в ней один элемент, то ячейка обнуляется, если в ячеке
     * несколько элементов, то ищет нужный среди них обнуляет ссылки на
     * него.
     * @param k ключ
     * @return true если элемент удален.
     */
    public boolean delete(K k) {
        boolean result = false;
        int index = this.hash(k);
        Node<K, V> node = this.hashTable[index];
        if (node != null) {
            if (node.getNext() == null && node.getKey().equals(k)) {
                this.hashTable[index] = null;
                result = true;
                this.size--;
                this.modCount++;
            } else {
                result = unlinkNode(k);
            }
        }
        return result;
    }

    /**
     * Удаление ноды, если была коллизия.
     * Если удаляемая нода первая, то следующая нода записывается
     * как первая в чейку hashTable, иначе у предыдущей ноды
     * перезаписываетя поле next на следующую за удаляемой ноду
     * или на null.
     * @param k ключ.
     * @return true если нода удалена.
     */
    private boolean unlinkNode(K k) {
        boolean result = false;
        int index = this.hash(k);
        Node<K, V> node = this.hashTable[index];
        if (node.getKey().equals(k)) {
            this.hashTable[index] = node.getNext();
            result = true;
            this.size--;
            this.modCount++;
        } else {
            while (node != null) {
                if (node.getNext() != null && node.getNext().getKey().equals(k)) {
                    node.setNext(node.getNext().getNext());
                    result = true;
                    this.size--;
                    this.modCount++;
                    break;
                }
                node = node.getNext();
            }
        }
        return result;
    }

    /**
     * Добавление ноды при коллизии.
     * Ищет последнюю ноду и в ее поле next записывает ссылку на новую ноду.
     * @param newNode новая нода.
     * @return true если добавление прошло удачно.
     */
    private boolean addNode(Node<K, V> newNode) {
        boolean result = false;
        int index = this.hash(newNode.getKey());
        Node<K, V> node = this.hashTable[index];
        if (node != null) {
            while (node.getNext() != null) {
                node = node.getNext();
            }
            node.setNext(newNode);
            this.size++;
            this.modCount++;
            result = true;
        }
        return result;
    }

    /**
     * Если ключ уже есть в мапе, то обновляется поле value.
     * @param k ключ.
     * @param v значение.
     * @return true если значение обновлено.
     */
    private boolean updateNode(K k, V v) {
        boolean result = false;
        int index = this.hash(k);
        Node<K, V> node = this.hashTable[index];
        while (node != null) {
            if (node.getKey().equals(k)) {
                node.setValue(v);
                result = true;
                this.modCount++;
                break;
            }
        }
        return result;
    }

    /**
     * Проверяет наличие ключа в мапе.
     * Определяет индекс ячейки по хэшкоду ключа, и если
     * она не пуская, то проверяется наличие ноды с данным
     * ключем.
     * @param k ключ.
     * @return true если ключ найден.
     */
    public boolean containsKey(K k) {
        boolean result = false;
        int index = this.hash(k);
        if (this.hashTable[index] != null) {
            Node<K, V> node = this.hashTable[index];
            while (node != null) {
                if (node.getKey().equals(k)) {
                    result = true;
                    break;
                }
                node = node.getNext();
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
        Node<K, V>[] oldHashTable = this.hashTable;
        this.hashTable = new Node[oldHashTable.length * 2];
        this.size = 0;
        for (Node<K, V> node : oldHashTable) {
            while (node != null) {
                this.insert(node.getKey(), node.getValue());
                node = node.getNext();
            }
        }
    }

    /**
     * По хэш коду генерирует номер ячейки в которую необходимо положить
     * элемент.
     * @param k - элемент.
     * @return - индекс ячйки.
     */
    private int hash(K k) {
        int hash = 31 * 17 + k.hashCode();
        return Math.abs(hash) % this.hashTable.length;
    }

    /**
     * Узел для хранения элемента (ключ + значение).
     * Так же, на случай коллизии, хранит ссылку на следующую ноду в одной
     * ячейке.
     * @param <K> ключ.
     * @param <V> значение.
     */
    private static class Node<K, V> {

        private K key;
        private V value;
        private Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {

            private int index = findCell(0);
            private int expectedModCount = modCount;
            private Node<K, V> node = (this.index == -1) ? null : hashTable[this.index];

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
                return this.index != -1 && (this.node != null || findCell(this.index + 1) != -1);
            }

            @Override
            public V next() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (this.index == -1) {
                    throw new NoSuchElementException();
                }
                V result = this.node.getValue();
                if (this.node.getNext() == null) {
                    this.index = findCell(this.index + 1);
                    this.node = (this.index == -1) ? null : hashTable[this.index];
                } else {
                    this.node = this.node.getNext();
                }
                return result;
            }
        };
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
