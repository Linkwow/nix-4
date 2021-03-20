package calculator.factory;

import calculator.operations.*;
import calculator.operations.integeroperations.IntegerOperations;
import org.reflections.Reflections;

import java.util.Set;

public class IntegerOperationsFactory implements CalculateFactory<Integer> {
    private Reflections reflections;
    private static IntegerOperationsFactory instance;
    private Set<Class<? extends IntegerOperations>> integerOperationsSet;

    private IntegerOperationsFactory() {
        reflections = new Reflections("calculator.operations");
        integerOperationsSet = reflections.getSubTypesOf(IntegerOperations.class);
    }

    @Override
    public Calculator<Integer> createOperationsClass() throws Exception {
        for (Class<? extends Calculator<Integer>> operationsClass : integerOperationsSet) {
            if (!operationsClass.isAnnotationPresent(Deprecated.class)) {
                return operationsClass.getDeclaredConstructor().newInstance();
            }
        }
        throw new ClassNotFoundException();
    }

    public static IntegerOperationsFactory getInstance() {
        if (instance == null) {
            instance = new IntegerOperationsFactory();
        }
        return instance;
    }
}