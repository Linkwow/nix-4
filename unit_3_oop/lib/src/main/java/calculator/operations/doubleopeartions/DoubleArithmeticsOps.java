package calculator.operations.doubleopeartions;

import calculator.operations.Calculator;

public class DoubleArithmeticsOps implements Calculator<Double>, DoubleOperations {
    private static DoubleArithmeticsOps instance;

    @Override
    public Double add(Double leftVal, Double rightVal) {
        System.out.println("!");
        return leftVal + rightVal;
    }

    @Override
    public Double subtract(Double leftVal, Double rightVal) {
        return leftVal - rightVal;
    }

    @Override
    public Double multiply(Double leftVal, Double rightVal) {
        return leftVal * rightVal;
    }

    @Override
    public Double divide(Double leftVal, Double rightVal) throws Exception {
        if(rightVal == 0){
            throw new Exception("You Try divide bu zero!");
        } else {
            return leftVal / rightVal;
        }
    }

    public static DoubleArithmeticsOps getInstance(){
        if(instance == null){
            instance = new DoubleArithmeticsOps();
        }
        return instance;
    }
}
