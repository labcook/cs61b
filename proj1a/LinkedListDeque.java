public class LinkedListDeque<T> {
    private class IntNode {
        private T item;
        private IntNode next;
        private IntNode prev;
        IntNode(IntNode m, T i, IntNode n) {
            prev = m;
            item = i;
            next = n;
        }
        IntNode(IntNode m, IntNode n) {
            prev = m;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new IntNode(null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;

    }

    public void addFirst(T item) {
        size += 1;
        if (sentinel.next == sentinel && sentinel.prev == sentinel) {
            IntNode temp = new IntNode(sentinel, item, sentinel);
            sentinel.next = temp;
            sentinel.prev = temp;
        } else {
            IntNode temp = new IntNode(sentinel, item, sentinel.next);
            sentinel.next.prev = temp;
            sentinel.next = temp;
        }
    }

    public void addLast(T item) {
        size += 1;
        if (sentinel.next == sentinel && sentinel.prev == sentinel) {
            IntNode temp = new IntNode(sentinel, item, sentinel);
            sentinel.next = temp;
            sentinel.prev = temp;
        } else {
            IntNode temp = new IntNode(sentinel.prev, item, sentinel);
            sentinel.prev.next = temp;
            sentinel.prev = temp;
        }
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
        if (sentinel.next == sentinel && sentinel.prev == sentinel) {
            return;
        }
        IntNode ptr = sentinel.next;
        System.out.print(ptr.item + " ");
        while (ptr.next != sentinel) {
            ptr = ptr.next;
            System.out.print(ptr.item + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T res = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return  res;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        T res = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return  res;
    }

    public T get(int index) {
        if (index < 0 || index + 1 > size) {
            return null;
        }
        IntNode ptr = sentinel.next;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        }
        return ptr.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index + 1 > size) {
            return null;
        }
        return recHelper(index, 0, sentinel.next);
    }

    private T recHelper(int index, int now, IntNode n) {
        if (now == index) {
            return n.item;
        }
        return recHelper(index, now + 1, n.next);
    }

}
