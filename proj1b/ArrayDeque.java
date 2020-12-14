public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;


    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        int mid = items.length / 2;
        nextFirst = mid;
        nextLast = mid + 1;
    }

    private void resize(int capacity) {
        T[] a = (T []) new Object[capacity];
        int startPos = a.length / 2 - size / 2;
        int oldIndex = (nextFirst + 1) % items.length;
        int newIndex = startPos;
        int count = 0;
        while (count < size) {
            a[newIndex] = items[oldIndex % items.length];
            oldIndex++;
            newIndex++;
            count++;
        }
        items = a;
        nextFirst = startPos - 1;
        nextLast = newIndex;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        size += 1;
        nextLast = (nextLast + 1) % items.length;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        size += 1;
        //must add items.length
        nextFirst = (nextFirst - 1 + items.length) % items.length;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (size == 0) {
            return;
        }
        int oldIndex = nextFirst + 1;
        for (int i = 0; i < size; i++) {
            System.out.print(items[(oldIndex + i) % items.length] + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = (nextFirst + 1) % items.length;
        T res = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        if ((double) size / items.length < 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }
        return  res;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = (nextLast - 1 + items.length) % items.length;
        T res = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        if ((double) size / items.length < 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }
        return  res;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int oldIndex = (nextFirst + 1) % items.length;
        while (index > 0) {
            oldIndex = (oldIndex + 1) % items.length;
            index--;
        }
        return items[oldIndex];
    }

//    public static void main(String[] args) {
//        ArrayDeque test = new ArrayDeque();
//        test.addFirst(6);
//        test.addFirst(5);
//        test.addFirst(4);
//        test.addFirst(3);
//        test.addFirst(2);
//        test.addFirst(1);
//        test.addLast(7);
//        test.addLast(8);
//        test.addLast(9);
//        System.out.println(test.size);
//        test.printDeque();
//        System.out.println(test.get(8));
//        System.out.println(test.get(1));
//        System.out.println(test.get(0));
//    }

}
