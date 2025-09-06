package deque;

public class LinkedListDeque<T> {

    //内部类:单个节点
    public static class Node<T> {
        public Node<T> prev;
        public T item;
        public Node<T> next;
        public Node(Node<T> p, T i, Node<T> n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node<T> sentinel;  //哨兵节点
    private int size;  //队列大小

    //构造函数(构造空队列)
    public LinkedListDeque() {
        sentinel = new Node<>(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node<T> newNode = new Node<>(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;  //记得要把 size + 1
    }

    public void addLast(T item) {
        Node<T> newNode = new Node<>(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;  //记得要把 size + 1
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node<T> first = sentinel.next;
        T removeItem = first.item;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        //垃圾回收
        first.next = null;
        first.prev = null;
        size -= 1;  //记得要把 size - 1
        return removeItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node<T> last = sentinel.prev;
        T removeItem = last.item;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        //垃圾回收
        last.next = null;
        last.prev = null;
        size -= 1;  //记得要把 size - 1
        return removeItem;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node<T> curr = sentinel.next;
        for(int i = 0;i < index;i++) {
            curr = curr.next;
        }
        return curr.item;
    }

    public T getRecursive(int index) {
        if(index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    public T getRecursiveHelper(Node<T> curr, int n) {
        if(n == 0) {
            return curr.item;
        }
        return getRecursiveHelper(curr.next, n - 1);
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {return size == 0;}

    public void printDeque() {
        Node<T> curr = sentinel.next;
        while(curr != sentinel) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.println();
    }
}
