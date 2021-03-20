package calculator.operations;

public interface Calculator <T>{
    T add(T leftVal, T rightVal);
    T subtract(T leftVal, T rightVal);
    T multiply(T leftVal, T rightVal);
    T divide(T leftVal, T rightVal) throws Exception;
}
