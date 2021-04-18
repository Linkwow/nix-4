package ua.nix.libs;

import java.util.Optional;

public class MathSet {
    private static final int BASE_CAPACITY = 0;
    private Number[] arrayOfUniqueNumber;

    public MathSet() {
        arrayOfUniqueNumber = new Number[BASE_CAPACITY];
    }

    public MathSet(int capacity) {
        arrayOfUniqueNumber = new Number[BASE_CAPACITY];
    }

    public MathSet(Number[] numbers) {
        Optional<Number[]> arrayCheck = Optional.of(numbers);
        try {
            if (arrayCheck.isPresent()) {
                arrayOfUniqueNumber = new Number[BASE_CAPACITY];
                parseArrayByElements(numbers);
            }
        } catch (NullPointerException nullPointerException) {
            System.err.println("Массив или элемент массива null, проверьте пожалуйста значения");
        }
    }

    public MathSet(Number[]... numbers) {
        Optional<Number[][]> arrayOfArrayCheck = Optional.of(numbers);
        try {
            if (arrayOfArrayCheck.isPresent()) {
                arrayOfUniqueNumber = new Number[BASE_CAPACITY];
                Optional<Number[]> arrayCheck;
                for (Number[] array : numbers) {
                    arrayCheck = Optional.of(array);
                    try {
                        if (arrayCheck.isPresent()) {
                            parseArrayByElements(array);
                        }
                    } catch (NullPointerException nullPointerException){
                        throw new NullPointerException();
                    }
                }
            }
        } catch (NullPointerException nullPointerException) {
            System.err.println("Массив или элемент массива null, проверьте пожалуйста значения");
        }
    }

    public MathSet(MathSet numbers) {
        arrayOfUniqueNumber = new Number[BASE_CAPACITY];
        for (Number number : numbers.toArray()) {
            add(number);
        }
    }

    public MathSet(MathSet... numbers) {
        for (MathSet mathSet : numbers) {
            add(mathSet.toArray());
        }
    }

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

    public void add(Number... n) {
        for (Number number : n) {
            add(number);
        }
    }

    public void join(MathSet ms) {
        for (Number number : ms.toArray()) {
            add(number);
        }
    }

    public void join(MathSet... ms) {
        for (MathSet mathSet : ms) {
            add(mathSet.toArray());
        }
    }

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

    public Number get(int index) {
        return arrayOfUniqueNumber[index];
    }

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

    public Number getAverage() {
        Number average;
        double temp = 0.00;
        for (Number number : arrayOfUniqueNumber) {
            temp += number.doubleValue();
        }
        average = temp / arrayOfUniqueNumber.length;
        return average;
    }

    public Number getMedian() {
        Number median;
        sortAsc();
        int middle = arrayOfUniqueNumber.length / 2;
        if (arrayOfUniqueNumber.length % 2 == 0) {
            median = arrayOfUniqueNumber[middle - 1].doubleValue() + arrayOfUniqueNumber[middle + 1].doubleValue() / 2;
        } else {
            median = arrayOfUniqueNumber[middle].doubleValue();
        }
        return median;
    }

    public Number[] toArray() {
        return arrayOfUniqueNumber;
    }

    public Number[] toArray(int firstIndex, int lastIndex) {
        Number[] numbers = new Number[lastIndex - firstIndex + 1];
        for (int innerIndex = 0, outerIndex = firstIndex; innerIndex < numbers.length; innerIndex++, outerIndex++) {
            numbers[innerIndex] = arrayOfUniqueNumber[outerIndex];
        }
        return numbers;
    }

    public MathSet squash(int firstIndex, int lastIndex) {
        Number[] tempArray = new Number[arrayOfUniqueNumber.length - (lastIndex - firstIndex + 1)];
        for (int outerIndex = firstIndex, innerIndex = 0; outerIndex <= lastIndex; outerIndex++, innerIndex++) {
            tempArray[innerIndex] = arrayOfUniqueNumber[outerIndex];
        }
        MathSet mathSet = new MathSet(tempArray);
        clear(tempArray);
        return mathSet;
    }

    public void clear() {
        arrayOfUniqueNumber = new Number[BASE_CAPACITY];
    }

    public void clear(Number[] numbers) {
        Number[] tempArray = arrayOfUniqueNumber;
        for (int outerIndex = 0; outerIndex < tempArray.length; outerIndex++) {
            for (int innerIndex = 0; innerIndex < numbers.length; innerIndex++) {
                if (tempArray[outerIndex].equals(numbers[innerIndex])) {
                    tempArray[outerIndex] = null;
                    break;
                }
            }
        }
        arrayOfUniqueNumber = new Number[BASE_CAPACITY];
        for (Number number : tempArray) {
            add(number);
        }
    }

    private int findIndexOfValue(Number number) throws ClassNotFoundException {
        for (int index = 0; index < arrayOfUniqueNumber.length; index++) {
            if (number.equals(arrayOfUniqueNumber[index])) {
                return index;
            }
        }
        throw new ClassNotFoundException("Введённый Вами объект в метод сортировки по значению объекта не найден в коллекции. Проверьте Вводимый объект");
    }

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

    private void parseArrayByElements(Number[] numbers) {
        Optional<Number> optional;
        for (Number number : numbers) {
            try {
                optional = Optional.of(number);
                if (optional.isPresent())
                    add(number);
            } catch (NullPointerException nullPointerException){
                throw new NullPointerException();
            }
        }
    }

    public String toString() {
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < arrayOfUniqueNumber.length; i++) {
            value.append(arrayOfUniqueNumber[i]);
            value.append(" ");
        }
        value.deleteCharAt(value.length() - 1);
        return value.toString();
    }
}