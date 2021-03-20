package calculator.operations.integeroperations;

import calculator.operations.Calculator;

public class DemonstrationIntegerArithmeticsOps implements Calculator<Integer>, IntegerOperations{

    @Override
    public Integer add(Integer leftVal, Integer rightVal) {
        return leftVal + rightVal;
    }

    @Override
    public Integer divide(Integer leftVal, Integer rightVal) throws Exception {
        if(rightVal == 0){
            throw new Exception("You try divide be zero");
        } else {
            return leftVal / rightVal;
        }
    }

    @Override
    public Integer subtract(Integer leftVal, Integer rightVal) {
        return leftVal - rightVal;
    }

    @Override
    public Integer multiply(Integer leftVal, Integer rightVal) {
        return leftVal * rightVal;
    }
}