package calculator;

import calculator.factory.DoubleOperationsFactory;
import calculator.factory.IntegerOperationsFactory;
import calculator.operations.*;

public class FactoryControlService {

    public static Calculator<Double> doubleOperations() throws Exception {
        return DoubleOperationsFactory.getInstance().createOperationsClass();
    }

    public static Calculator<Integer> integerOperations() throws Exception {
        return IntegerOperationsFactory.getInstance().createOperationsClass();
    }
}