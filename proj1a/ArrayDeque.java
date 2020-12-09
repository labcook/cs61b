public class ArrayDeque<T> {
    private T[] items;
    private int size;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
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
        items[size] = item;
        size += 1;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        System.arraycopy(items,0,items,1,size);
        items[0] = item;
        size += 1;
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
        for(int i =0 ; i < size; i++){
            System.out.print(items[i] + " ");
        }
    }

    public T removeFirst() {
        T res = items[0];
        size -= 1;
        System.arraycopy(items,1,items,0,size);
        if(size < items.length/4){
            resize(size/2);
        }
        return  res;
    }

    public T removeLast() {
        size -= 1;
        T res = items[size];
        if(size < items.length/4){
            resize(size/2);
        }
        return  res;
    }

    public T get(int index) {
        if(index < 0 || index + 1 > size) return null;
        return items[index];
    }
}
