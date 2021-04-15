package ua.nix.orderedlist;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class OrderedList<E> implements List<E> {

    private Object[] elements;

    public OrderedList() {
        elements = new Object[0];
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public boolean isEmpty() {
        return elements.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        boolean result = false;
        for (Object element : elements) {
            if (element.equals(o)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            int count = -1;

            @Override
            public boolean hasNext() {
                return count++ < elements.length - 1;
            }

            @Override
            public E next() {
                return (E) elements[count];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return elements;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) elements;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean add(E e) {
        if (elements.length == 0) {
            elements = new Object[1];
            elements[0] = e;
        } else {
            Object[] arrayOfPreviousElements = elements;
            elements = sort((Comparable<E>) e, arrayOfPreviousElements);
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private Object[] sort(Comparable<E> object, Object[] arrayOfPreviousElements) {
        Object[] arrayOfNewElements = new Object[arrayOfPreviousElements.length + 1];
        for (int i = 0; i < arrayOfPreviousElements.length; i++) {
            if (object.compareTo((E) arrayOfPreviousElements[i]) < 0 && i == 0) {
                arrayOfNewElements[0] = object;
                for (int k = 0, l = 1; k < arrayOfPreviousElements.length; k++, l++) {
                    arrayOfNewElements[l] = arrayOfPreviousElements[k];
                }
                break;
            } else if (object.compareTo((E) arrayOfPreviousElements[i]) < 0) {
                arrayOfNewElements[i] = object;
                for (int k = i + 1, l = i; l < arrayOfPreviousElements.length; k++, l++) {
                    arrayOfNewElements[k] = arrayOfPreviousElements[l];
                }
                break;
            } else if (object.compareTo((E) arrayOfPreviousElements[i]) > 0) {
                arrayOfNewElements[i] = arrayOfPreviousElements[i];
                if (i == arrayOfPreviousElements.length - 1) {
                    arrayOfNewElements[i + 1] = object;
                }
            } else if (object.compareTo((E) arrayOfPreviousElements[i]) == 0) {
                arrayOfNewElements[i] = object;
                for (int k = i + 1, l = i; k < arrayOfNewElements.length; k++, l++) {
                    arrayOfNewElements[k] = arrayOfPreviousElements[l];
                }
                break;
            }
        }
        return arrayOfNewElements;
    }

    @Override
    public boolean remove(Object o) {
        Object[] arrayOfNewValue = new Object[elements.length - 1];
        for (int i = 0; i < elements.length; i++) {
            if (o.equals(elements[i])) {
                for (int current = i, mutable = i + 1; mutable < elements.length; current++, mutable++) {
                    arrayOfNewValue[current] = elements[mutable];
                }
                break;
            } else {
                arrayOfNewValue[i] = elements[i];
            }
        }
        elements = arrayOfNewValue;
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean result = false;
        for(Object o : c){
            if(this.contains(o)){
                result = true;
            } else {
                result = false;
                break;
            }
        }
        return result;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for(E o : c){
            add(o);
        }
        return false;
    }

    @Deprecated
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            remove(o);
        }
        return isEmpty();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        for(Object o : c){
            if(!contains(o)){
                remove(o);
            }
        }
        return false;
    }

    @Override
    public void clear() {
        elements = null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        return (E) elements[index];
    }

    @Deprecated
    @Override
    public E set(int index, E element) {
        add(element);
        return element;
    }

    @Deprecated
    @Override
    public void add(int index, E element) {
        add(element);
    }

    @Override
    public E remove(int index) {
        for (int i = 0; i < elements.length; i++) {
            if(i == index){
                remove(get(i));
                break;
            }
        }
        return null;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < elements.length; i++) {
            if(elements[i] == o){
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        while (contains(o)){
            index = indexOf(o);
        }
        return index;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ListIterator<E> listIterator() {
        return new ListIterator<>() {
            int nextCount = -1;
            Object lastElement = new Object();
            int currentPosition = 0;

            @Override
            public boolean hasNext() {
                return nextCount++ < elements.length - 1;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() {
                currentPosition++;
                lastElement = elements[nextCount];
                return (E) lastElement;
            }

            @Override
            public boolean hasPrevious() {
                return currentPosition-- > 0;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E previous() {
                lastElement = elements[currentPosition];
                return (E) lastElement;
            }

            @Override
            public int nextIndex() {
                return indexOf(next());
            }

            @Override
            public int previousIndex() {
                return indexOf(previous());
            }

            @Override
            public void remove() {
                lastElement = null;
            }

            @Override
            public void set(E e) {
                lastElement = e;
            }

            @Override
            public void add(E e) {
                OrderedList.this.add(e);
            }
        };
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListIterator<>() {
            int currentPosition = index;
            int nextCount = currentPosition - 1;
            Object lastElement = new Object();

            @Override
            public boolean hasNext() {
                return nextCount++ < elements.length - 1;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E next() {
                currentPosition++;
                lastElement = elements[nextCount];
                return (E) lastElement;
            }

            @Override
            public boolean hasPrevious() {
                return currentPosition-- > 0;
            }

            @SuppressWarnings("unchecked")
            @Override
            public E previous() {
                lastElement = elements[currentPosition];
                return (E) lastElement;
            }

            @Override
            public int nextIndex() {
                return indexOf(next());
            }

            @Override
            public int previousIndex() {
                return indexOf(previous());
            }

            @Override
            public void remove() {
                lastElement = null;
            }

            @Override
            public void set(E e) {
                lastElement = e;
            }

            @Override
            public void add(E e) {
                OrderedList.this.add(e);
            }
        };
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        List<E> sublist = new OrderedList<>();
        for (int i = fromIndex; i <= toIndex; i++) {
            sublist.add((E) elements[i]);
        }
        return sublist;
    }
}
