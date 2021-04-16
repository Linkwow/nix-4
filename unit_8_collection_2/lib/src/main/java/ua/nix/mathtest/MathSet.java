package ua.nix.mathtest;

public class MathSet {
    private static final int BASE_CAPACITY = 0;
    private Number[] arrayOfUniqueNumber;

    //done
    public MathSet() {
        arrayOfUniqueNumber = new Number[BASE_CAPACITY];
    }

    //done
    public MathSet(int capacity) {
        arrayOfUniqueNumber = new Number[BASE_CAPACITY];
    }

    //done
    public MathSet(Number[] numbers) {
        arrayOfUniqueNumber = new Number[BASE_CAPACITY];
        parseArrayByElements(numbers);
    }

    //done
    public MathSet(Number[]... numbers) {
        arrayOfUniqueNumber = new Number[BASE_CAPACITY];
        for (Number[] array : numbers) {
            parseArrayByElements(array);
        }
    }

    //done
    public MathSet(MathSet numbers) {
        arrayOfUniqueNumber = new Number[BASE_CAPACITY];
        for (Number number : numbers.getAll()) {
            add(number);
        }
    }

    //done
    public MathSet(MathSet... numbers) {
        for (MathSet mathSet : numbers) {
            for (Number number : mathSet.getAll()) {
                add(number);
            }
        }
    }

    //done
    public void add(Number number) {
        if (number != null) {
            if (isUnique(number)) {
                Number[] tempArray = new Number[arrayOfUniqueNumber.length + 1];
                for (int index = 0; index < arrayOfUniqueNumber.length; index++) {
                    tempArray[index] = arrayOfUniqueNumber[index];
                }
                tempArray[tempArray.length - 1] = number;
                arrayOfUniqueNumber = tempArray;
            }
        }
    }

    public void add(Number... n) {  //добавить элементы
    }

    /*
     public void join(MathSet ms) {  //присоединить
     }

     public void join(MathSet... ms) { //присоединить массив
     }

     public void sortDesc() {    //сортировать по убыванию
     }

     public void sortDesc(int firstIndex, int lastIndex) {   //сортировать по цбыванию в диапазоне
     }

     public void sortDesc(Number value) {    //всё что меньше значения по убыванию
     }

     public void sortAsc() { //по возрастанию
     }

     public void sortAsc(int firstIndex, int lastIndex) {    //по возрастанию в диапазоне
     }

     public void sortAsc(Number value) { //всё что выше элемента
     }

     public Number get(int index) {  //получить индекс
     }

     public Number getMax() {    //максимально езначение
     }

     public Number getMin() {    //минимальное значение
     }

     public Number getAverage() {    //среднее значение
     }

     public Number getMedian() { // медиана — это такое число, что половина из элементов набора больше него, а другая половина меньше
     }

     public Number[] toArray() { //превратить в массив
     }

     public Number[] toArray(int firstIndex, int lastIndex) { //превратит в массив в диапазоне
     }

     public MathSet squash(int firstIndex, int lastIndex) {  //обрезать
     }

     public void clear() {
     }

     public void clear(Number[] numbers) {
     }*/
    //done
    public Number[] getAll() {
        return arrayOfUniqueNumber;
    }

    //done
    private boolean isUnique(Number n) {
        boolean unique = false;
        if (arrayOfUniqueNumber.length == 0) {
            unique = true;
        } else {
            for (Number inner : arrayOfUniqueNumber) {
                if (!n.equals(inner)) {
                    unique = true;
                } else {
                    unique = false;
                    break;
                }
            }
        }
        return unique;
    }

    //done
    private void parseArrayByElements(Number[] numbers) {
        for (Number n : numbers) {
            add(n);
        }
    }

    //done
    public String toString() {
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < arrayOfUniqueNumber.length; i++) {
            value.append(arrayOfUniqueNumber[i]);
            value.append(" ");
        }
        value.deleteCharAt(value.length() - 1);
        return value.toString();
    }

    public static void main(String[] args) {
        Number[] array = new Number[]{1, 24, 45};
        Number[] array1 = new Number[]{1, 24, 45, 18};
        MathSet mathSet = new MathSet(array, array1);
        mathSet.add(10);
        mathSet.add((short) 10);
        mathSet.add(24);
        MathSet mathSet1 = new MathSet(mathSet);
     /* mathSet.add(1);
        mathSet.add(21);
        mathSet.add(13);
        mathSet.add(14);
        mathSet.add(51);
        mathSet.add(1);*/

        System.out.println(mathSet1);

    }

}
