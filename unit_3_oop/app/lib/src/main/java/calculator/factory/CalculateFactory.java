package calculator.factory;

import calculator.operations.Calculator;

public interface CalculateFactory<T> {

    Calculator<T> createOperationsClass() throws Exception;
    //void addOperationsClass(Class<? extends Calculator<T>> obj);

}
