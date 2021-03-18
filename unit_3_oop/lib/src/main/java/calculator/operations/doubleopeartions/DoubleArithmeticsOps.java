package calculator.operations.doubleopeartions;

import calculator.operations.Calculator;

public class DoubleArithmeticsOps implements Calculator<Double>, DoubleOperations {
    private static DoubleArithmeticsOps instance;

    @Override
    public Double add(Double leftVal, Double rightVal) {
        System.out.println("!");
        return leftVal + rightVal;
    }

    public static DoubleArithmeticsOps getInstance(){
        if(instance == null){
            instance = new DoubleArithmeticsOps();
        }
        return instance;
    }
}
