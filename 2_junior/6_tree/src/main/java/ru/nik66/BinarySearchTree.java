package ru.nik66;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * BST (Simple Tree Set).
 * @param <E> element extends Comparable.
 */
public class BinarySearchTree<E extends Comparable<E>> implements Iterable<E> {

    /**
     * Корневой лист.
     */
    private Leaf<E> root;
    /**
     * количество элементов в коллекции.
     */
    private int size = 0;
    /**
     * Счетчик изменений.
     */
    private int modCount = 0;

    /**
     * Добавление элементов в дерево.
     * Если дерево пустое, то создаем корень, иначе добавляем листья.
     * @param e element.
     */
    public void add(E e) {
        if (this.size() == 0) {
            this.root = new Leaf<>(e);
            this.size++;
            this.modCount++;
        } else {
            if (this.addLeaf(this.root, e)) {
                this.size++;
                this.modCount++;
            }
        }
    }

    /**
     * Добавление листьев к дереву.
     * Рекурсвно ищет нужное место для всавки листа и вставляет его туда.
     * Если новый элемент меньше существующего, то проверяется левая ветка,
     * ели больше, то правая.
     * И если она свободна (null) то туда записывается новый элемент и
     * возвращается true, если занята, то рекурсивно вызывается этот же
     * метод и проверяется следующий элемент.
     * Если в дереве уже есть элемент равный новому, то ничего не происходит
     * и возвращается false.
     * @param lastLeaf предыдущий лист.
     * @param e новый элемент.
     * @return true, если элемент добавлен успешно.
     */
    private boolean addLeaf(Leaf<E> lastLeaf, E e) {
        boolean result = false;
        if (e.compareTo(lastLeaf.element) < 0) {
            if (lastLeaf.left == null) {
                lastLeaf.left = new Leaf<>(e);
                lastLeaf.left.parent = lastLeaf;
                result = true;
            } else {
                result = this.addLeaf(lastLeaf.left, e);
            }
        } else if (e.compareTo(lastLeaf.element) > 0) {
            if (lastLeaf.right == null) {
                lastLeaf.right = new Leaf<>(e);
                lastLeaf.right.parent = lastLeaf;
                result = true;
            } else {
                result = this.addLeaf(lastLeaf.right, e);
            }
        }
        return result;
    }

    /**
     * Вщоврачает количество элементов в коллекции.
     * @return size.
     */
    public int size() {
        return this.size;
    }

    /**
     * Iterator.
     * @return iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            /**
             * Идем к самому левому элементу от корня.
             */
            Leaf<E> next = this.goToLeftMost(root);
            /**
             * Проверка на модификацию дерева во время итерации.
             */
            int expectedModCount = modCount;

            /**
             * Передвигает курсов до самого левого элемента ветки.
             * @param leaf Лист, с которого начинать движение.
             * @return самый левый лист.
             */
            private Leaf<E> goToLeftMost(Leaf<E> leaf) {
                while (leaf.left != null) {
                    leaf = leaf.left;
                }
                return leaf;
            }

            /**
             * Двигает курсор на один лист вправо и до конца влево.
             * @return Лист.
             */
            private Leaf<E> goToRight() {
                return goToLeftMost(this.next.right);
            }

            /**
             * Поднимает курсор.
             * Если поднялись по правой ветке, то поднимаемся дальше,
             * если по левой, то останавливаемся. Если по правым веткам
             * упёрлись в корень, то элементов больше нету.
             * @return лист или null, если листов больше нету.
             */
            private Leaf<E> goToUp() {
                Leaf<E> leaf = this.next;
                while (true) {
                    if (leaf.parent == null) {
                        leaf = null;
                        break;
                    }
                    if (leaf.parent.left == leaf) {
                        leaf = leaf.parent;
                        break;
                    }
                    leaf = leaf.parent;
                }
                return leaf;
            }

            @Override
            public boolean hasNext() {
                return this.next != null && this.next.element != null;
            }

            /**
             * Сохраняет текущее положение курсора и ищит следующий лист.
             * Если есть куда идти вправо, то иидет на одну позицию вправо
             * и в самый левый элемент. Если правого нету, то поднимается.
             * @return элемент.
             */
            @Override
            public E next() {
                if (this.expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                Leaf<E> result = this.next;
                if (result == null) {
                    throw new NoSuchElementException();
                }
                if (result.right != null) {
                    this.next = goToRight();
                } else {
                    this.next = goToUp();
                }
                return result.element;
            }
        };
    }

    /**
     * Лист дерева.
     * @param <E> элемент.
     */
    private static class Leaf<E> {

        /**
         * Элемент.
         */
        E element;
        /**
         * Левая ветка. Ссылка на элементы, меньше данного.
         */
        Leaf<E> left;
        /**
         * Правая ветка. Ссылка на элементы, больше данного.
         */
        Leaf<E> right;
        /**
         * Родительский элемент.
         */
        Leaf<E> parent;

        Leaf(E element) {
            this.element = element;
            this.left = null;
            this.right = null;
            this.parent = null;
        }

    }

}
