package deque;

public class LinkedListDeque<T> {

    //内部类:单个节点
    private class Node {
        public Node prev;
        public T item;
        public Node next;
        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;  //哨兵节点
    private int size;  //队列大小

    //构造函数(构造空队列)
    public LinkedListDeque() {
        //这里不能初始化的时候直接用sentinel，因为sentinel还没被初始化，要先全部赋值为null，在设置其前驱与后继
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node newNode = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;  //记得要把 size + 1
    }

    public void addLast(T item) {
        Node newNode = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;  //记得要把 size + 1
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node first = sentinel.next;
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
        Node last = sentinel.prev;
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
        Node curr = sentinel.next;
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

    public T getRecursiveHelper(Node curr, int n) {
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
        Node curr = sentinel.next;
        for(int i = 0;i < size - 1;++i) {
            System.out.print(curr.item + " ");
            curr = curr.next;
        }
        System.out.print(curr.item);
    }
}
