package ua.nix.mathtest;

import java.math.BigInteger;

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
        for (Number number : numbers.toArray()) {
            add(number);
        }
    }

    //done
    public MathSet(MathSet... numbers) {
        for (MathSet mathSet : numbers) {
            add(mathSet.toArray());
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

    //done
    public void add(Number... n) {
        for (Number number : n) {
            add(number);
        }
    }

    //done
    public void join(MathSet ms) {
        for (Number number : ms.toArray()) {
            add(number);
        }
    }

    //done
    public void join(MathSet... ms) {
        for (MathSet mathSet : ms) {
            add(mathSet.toArray());
        }
    }

    //done
    public void sortDesc() {
        Number[] tempArray = new Number[arrayOfUniqueNumber.length];
        Number tempNumber;
        for (int outerIndex = 0; outerIndex < arrayOfUniqueNumber.length; outerIndex++) {
            int indexCount = 0;
            tempNumber = arrayOfUniqueNumber[outerIndex];
            for (int innerIndex = 0; innerIndex < arrayOfUniqueNumber.length; innerIndex++) {
                if (tempNumber.doubleValue() < arrayOfUniqueNumber[innerIndex].doubleValue()) {
                    indexCount++;
                }
            }
            tempArray[indexCount] = tempNumber;
        }
        arrayOfUniqueNumber = tempArray;
    }

    //done
    public void sortDesc(int firstIndex, int lastIndex) {
        Number[] tempArray = new Number[lastIndex - firstIndex + 1];
        Number tempNumber;
        for (int outerIndex = firstIndex; outerIndex <= lastIndex; outerIndex++) {
            int indexCount = 0;
            tempNumber = arrayOfUniqueNumber[outerIndex];
            for (int innerIndex = firstIndex; innerIndex <= lastIndex; innerIndex++) {
                if (tempNumber.doubleValue() < arrayOfUniqueNumber[innerIndex].doubleValue()) {
                    indexCount++;
                }
            }
            tempArray[indexCount] = tempNumber;
        }
        for (int innerIndex = 0, outerIndex = firstIndex; innerIndex < tempArray.length; innerIndex++, outerIndex++) {
            arrayOfUniqueNumber[outerIndex] = tempArray[innerIndex];
        }
    }

    //done
    public void sortDesc(Number value) throws ClassNotFoundException {
        int indexOfValue;
        Number[] tempArray;
        try {
            indexOfValue = findIndexOfValue(value);
            tempArray = new Number[arrayOfUniqueNumber.length - indexOfValue - 1];
        } catch (ClassNotFoundException classNotFoundException) {
            throw new ClassNotFoundException(classNotFoundException.getMessage());
        }
        Number tempNumber;
        for (int outerIndex = indexOfValue + 1; outerIndex < arrayOfUniqueNumber.length; outerIndex++) {
            int indexCount = 0;
            tempNumber = arrayOfUniqueNumber[outerIndex];
            for (int innerIndex = indexOfValue + 1; innerIndex < arrayOfUniqueNumber.length; innerIndex++) {
                if (tempNumber.doubleValue() < arrayOfUniqueNumber[innerIndex].doubleValue()) {
                    indexCount++;
                }
            }
            tempArray[indexCount] = tempNumber;
        }
        for (int innerIndex = 0, outerIndex = indexOfValue + 1; innerIndex < tempArray.length; innerIndex++, outerIndex++) {
            arrayOfUniqueNumber[outerIndex] = tempArray[innerIndex];
        }
    }

    //done
    public void sortAsc(int firstIndex, int lastIndex) {
        Number[] tempArray = new Number[lastIndex - firstIndex + 1];
        Number tempNumber;
        for (int outerIndex = firstIndex; outerIndex <= lastIndex; outerIndex++) {
            int indexCount = 0;
            tempNumber = arrayOfUniqueNumber[outerIndex];
            for (int innerIndex = firstIndex; innerIndex <= lastIndex; innerIndex++) {
                if (tempNumber.doubleValue() > arrayOfUniqueNumber[innerIndex].doubleValue()) {
                    indexCount++;
                }
            }
            tempArray[indexCount] = tempNumber;
        }
        for (int innerIndex = 0, outerIndex = firstIndex; innerIndex < tempArray.length; innerIndex++, outerIndex++) {
            arrayOfUniqueNumber[outerIndex] = tempArray[innerIndex];
        }
    }

    //done
    public void sortAsc() {
        Number[] tempArray = new Number[arrayOfUniqueNumber.length];
        Number tempNumber;
        for (int outerIndex = 0; outerIndex < arrayOfUniqueNumber.length; outerIndex++) {
            int indexCount = 0;
            tempNumber = arrayOfUniqueNumber[outerIndex];
            for (int innerIndex = 0; innerIndex < arrayOfUniqueNumber.length; innerIndex++) {
                if (tempNumber.doubleValue() > arrayOfUniqueNumber[innerIndex].doubleValue()) {
                    indexCount++;
                }
            }
            tempArray[indexCount] = tempNumber;
        }
        arrayOfUniqueNumber = tempArray;
    }

    //done
    public void sortAsc(Number value) throws ClassNotFoundException {
        int indexOfValue;
        Number[] tempArray;
        try {
            indexOfValue = findIndexOfValue(value);
            tempArray = new Number[arrayOfUniqueNumber.length - indexOfValue - 1];
        } catch (ClassNotFoundException classNotFoundException) {
            throw new ClassNotFoundException(classNotFoundException.getMessage());
        }
        Number tempNumber;
        for (int outerIndex = indexOfValue + 1; outerIndex < arrayOfUniqueNumber.length; outerIndex++) {
            int indexCount = 0;
            tempNumber = arrayOfUniqueNumber[outerIndex];
            for (int innerIndex = indexOfValue + 1; innerIndex < arrayOfUniqueNumber.length; innerIndex++) {
                if (tempNumber.doubleValue() > arrayOfUniqueNumber[innerIndex].doubleValue()) {
                    indexCount++;
                }
            }
            tempArray[indexCount] = tempNumber;
        }
        for (int innerIndex = 0, outerIndex = indexOfValue + 1; innerIndex < tempArray.length; innerIndex++, outerIndex++) {
            arrayOfUniqueNumber[outerIndex] = tempArray[innerIndex];
        }
    }

    //done
    public Number get(int index) {
        return arrayOfUniqueNumber[index];
    }

    //done
    public Number getMax() {
        Number max = 0, temp;
        for (int outerIndex = 0; outerIndex < arrayOfUniqueNumber.length; outerIndex++) {
            temp = arrayOfUniqueNumber[outerIndex];
            for (int innerIndex = 0; innerIndex < arrayOfUniqueNumber.length; innerIndex++) {
                if (temp.doubleValue() > arrayOfUniqueNumber[innerIndex].doubleValue()) {
                    if (temp.doubleValue() > max.doubleValue()) {
                        max = temp;
                    }
                }
            }

        }
        return max;
    }

    //done
    public Number getMin() {
        Number min = 0, temp;
        for (int outerIndex = 0; outerIndex < arrayOfUniqueNumber.length; outerIndex++) {
            temp = arrayOfUniqueNumber[outerIndex];
            for (int innerIndex = 0; innerIndex < arrayOfUniqueNumber.length; innerIndex++) {
                if (temp.doubleValue() < arrayOfUniqueNumber[innerIndex].doubleValue()) {
                    if (temp.doubleValue() < min.doubleValue()) {
                        min = temp;
                    }
                }
            }

        }
        return min;
    }

    //done
    public Number getAverage() {
        Number average;
        double temp = 0.00;
        for (Number number : arrayOfUniqueNumber) {
            temp += number.doubleValue();
        }
        average = temp / arrayOfUniqueNumber.length;
        return average;
    }

    //done
    public Number getMedian() {
        Number median = 0;
        sortAsc();
        int middle = arrayOfUniqueNumber.length / 2;
        if (arrayOfUniqueNumber.length % 2 == 0) {
            median = arrayOfUniqueNumber[middle - 1].doubleValue() + arrayOfUniqueNumber[middle + 1].doubleValue() / 2;
        } else {
            median = arrayOfUniqueNumber[middle].doubleValue();
        }
        return median;
    }

    //done
    public Number[] toArray() {
        return arrayOfUniqueNumber;
    }

    //done
    public Number[] toArray(int firstIndex, int lastIndex) {
        Number[] numbers = new Number[lastIndex - firstIndex + 1];
        for (int innerIndex = 0, outerIndex = firstIndex; innerIndex < numbers.length; innerIndex++, outerIndex++){
            numbers[innerIndex] = arrayOfUniqueNumber[outerIndex];
        }
        return numbers;
    }

    /*
    public MathSet squash(int firstIndex, int lastIndex) {  //обрезать
    }

    public void clear() {
    }

    public void clear(Number[] numbers) {
    }*/

    //done
    private int findIndexOfValue(Number number) throws ClassNotFoundException {
        for (int index = 0; index < arrayOfUniqueNumber.length; index++) {
            if (number.equals(arrayOfUniqueNumber[index])) {
                return index;
            }
        }
        throw new ClassNotFoundException("Введённый Вами объект в метод сортировки по значению объекта не найден в коллекции. Проверьте Вводимый объект");
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
        Number[] array = new Number[]{28, 17, 4, 150, 15.1, 32, -1, 2};
        Number[] array1 = new Number[]{28, 17, 4, 150, 32, -1, 2};
        MathSet mathSet = new MathSet(array);
        MathSet mathSet1 = new MathSet(array1);
        System.out.println(mathSet);
        System.out.println(mathSet1);
        for (Number number: mathSet1.toArray(3, 6)) {
            System.out.println(number);
        }
    }
}
