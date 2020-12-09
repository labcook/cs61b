public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;


    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize(int capacity) {
        T[] a = (T []) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        size += 1;
        nextLast = (nextLast+1) % items.length;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst = (nextFirst-1) % items.length;
    }

    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque(){
        if(size == 0) return;
        for(int i =1 ; i <= size; i++){
            System.out.print(items[(nextFirst+i)% items.length] + " ");
        }
    }

    public T removeFirst() {
        if (size == 0) return null;
        T res = items[(nextFirst+1)%items.length];
        size -= 1;
        nextFirst = (nextFirst+1)%items.length;
        if(size < items.length/4){
            resize(size/2);
        }
        return  res;
    }

    public T removeLast() {
        if (size == 0) return null;
        T res = items[(nextLast-1)%items.length];
        size -= 1;
        nextLast = (nextLast-1)%items.length;
        if(size < items.length/4){
            resize(size/2);
        }
        return  res;
    }

    public T get(int index) {
        if(index < 0 || index + 1 > size) return null;
        return items[nextFirst+1+index];
    }
}
