package calculator.operations.integeroperations;

import calculator.operations.Calculator;

public class IntegerArithmeticsOps implements Calculator<Integer>, IntegerOperations {

    @Override
    public Integer add(Integer leftVal, Integer rightVal) {
        return leftVal + rightVal;
    }
}
