package deque;

import afu.org.checkerframework.checker.oigj.qual.O;

public class ArrayDeque<T> {

    private static final int INIT_CAPACITY = 8;
    private T[] items;
    int front;
    int back;
    private int size;

    public ArrayDeque() {
        //初始化泛型数组需要强制转化(泛型数组只能通过先声明Object数组, 然后再强转成泛型)
        items = (T[]) new Object[INIT_CAPACITY];
        front = 0;
        back = 0;
        size = 0;
    }

    private int nextIndex(int index) {
        return (index + 1) % items.length;
    }

    private int prevIndex(int index) {
        return (index - 1 + items.length) % items.length;
    }

    public void resize(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];
        int curr = front;
        for(int i = 0;i < size;i++) {
            newItems[i] = items[curr];
            curr = nextIndex(curr);
        }
        //更新数组引用 和 队列首尾指针
        items = newItems;
        front = 0;
        back = size;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        front = prevIndex(front);
        items[front] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[back] = item;
        back = nextIndex(back);
        size += 1;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T removeItem = items[front];
        items[front] = null;
        front = nextIndex(front);
        size -= 1;

        if (items.length > INIT_CAPACITY && (double) size / items.length < 0.25) {
            resize(items.length / 2);
        }

        return removeItem;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        back = prevIndex(back);
        T removeItem = items[back];
        items[back] = null;
        size -= 1;

        if (items.length > INIT_CAPACITY && (double) size / items.length < 0.25) {
            resize(items.length / 2);
        }

        return removeItem;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int actualIndex = (front + index) % items.length;
        return items[actualIndex];
    }

    public int size() {return this.size;}

    public boolean isEmpty() {return size == 0;}

    public void printDeque() {
        int curr = front;
        for (int i = 0;i < size;i++) {
            System.out.print(items[curr] + " ");
            curr = nextIndex(curr);
        }
        System.out.println();
    }
}
