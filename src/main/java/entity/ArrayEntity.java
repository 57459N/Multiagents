package entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayEntity implements Collection<Integer> {
    private int[] array;

    public ArrayEntity(int[] array) {
        this.array = array;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (!(o instanceof Integer)) {
            return false;
        }
        int value = (Integer) o;
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[index++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return Arrays.stream(array).boxed().toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        Integer[] integerArray = Arrays.stream(array).boxed().toArray(Integer[]::new); // Convert int[] to Integer[]

        if (a.length < integerArray.length) {
            // Use System.arraycopy if a is too small
            return (T[]) Arrays.copyOf(integerArray, integerArray.length, a.getClass());
        }
        System.arraycopy(integerArray, 0, a, 0, integerArray.length);
        if (a.length > integerArray.length) {
            a[integerArray.length] = null; // Null-terminate the array if it's larger
        }
        return a;
    }

    @Override
    public boolean add(Integer integer) {
        throw new UnsupportedOperationException("Cannot add elements to ArrayEntity");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Cannot remove elements from ArrayEntity");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        throw new UnsupportedOperationException("Cannot add elements to ArrayEntity");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Cannot remove elements from ArrayEntity");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Cannot retain elements in ArrayEntity");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Cannot clear elements in ArrayEntity");
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArrayEntity that = (ArrayEntity) o;
        return Arrays.equals(array, that.array);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }
}
