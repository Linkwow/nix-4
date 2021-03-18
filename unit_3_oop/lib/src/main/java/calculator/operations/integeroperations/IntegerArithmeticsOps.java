package calculator.operations.integeroperations;

import calculator.operations.Calculator;
@Deprecated
public class IntegerArithmeticsOps implements Calculator<Integer>, IntegerOperations {

    @Override
    public Integer add(Integer leftVal, Integer rightVal) {
        return leftVal + rightVal;
    }

    @Override
    public Integer divide(Integer leftVal, Integer rightVal) throws Exception {
        if(rightVal == 0){
            throw new Exception();
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