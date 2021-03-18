package calculator.factory;

import calculator.operations.*;
import calculator.operations.doubleopeartions.DoubleOperations;
import org.reflections.Reflections;
import java.util.Set;

public class DoubleOperationsFactory implements CalculateFactory<Double> {
    private Reflections reflections;
    private static DoubleOperationsFactory instance;
    private Set<Class<? extends DoubleOperations>> doubleOperationsSet;


    private DoubleOperationsFactory() {
        reflections = new Reflections("calculator.operations");
        doubleOperationsSet = reflections.getSubTypesOf(DoubleOperations.class);
    }

    @Override
    public Calculator<Double> createOperationsClass() throws Exception {
        for (Class<? extends Calculator<Double>> operationsClass : doubleOperationsSet) {
            if (!operationsClass.isAnnotationPresent(Deprecated.class)) {
                return operationsClass.getDeclaredConstructor().newInstance();
            }
        }
        throw new ClassNotFoundException();
    }

    public static DoubleOperationsFactory getInstance() {
        if (instance == null) {
            instance = new DoubleOperationsFactory();
        }
        return instance;
    }
}