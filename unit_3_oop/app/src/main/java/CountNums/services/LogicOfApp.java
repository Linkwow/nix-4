package CountNums.services;

import consolehelper.ConsoleHelper;
import calculator.FactoryControlService;

public class LogicOfApp {
    Service serviceInputOutputFromConsole = new Service();
    String typeOperations, typeValue, firstNum, secondNum, endCalculate;
    ConsoleHelper consoleHelper = new ConsoleHelper();
    boolean end = true;

    public void start() throws Exception {
        while (end) {
            typeOperations = serviceInputOutputFromConsole.menuOperations();
            typeValue = serviceInputOutputFromConsole.menuType();
            firstNum = serviceInputOutputFromConsole.firstNum();
            secondNum = serviceInputOutputFromConsole.secondNum();
            calculate(typeOperations, typeValue, firstNum, secondNum);
            endCalculate = serviceInputOutputFromConsole.end();
            if (Integer.parseInt(endCalculate) == 2) {
                end = false;
            } else {
                end = true;
            }
        }
    }

    private void calculate(String typeOperations, String typeValue, String firstNum, String secondNum) throws Exception {
        System.out.println("Результат операции : ");
        if (Integer.parseInt(typeValue) == 1) {
            if (Integer.parseInt(typeOperations) == 1) {
                consoleHelper.output(FactoryControlService.integerOperations().add(Integer.parseInt(firstNum), Integer.parseInt(secondNum)));
            } else if ((Integer.parseInt(typeOperations) == 2)) {
                consoleHelper.output(FactoryControlService.integerOperations().subtract(Integer.parseInt(firstNum), Integer.parseInt(secondNum)));
            } else if ((Integer.parseInt(typeOperations) == 3)) {
                consoleHelper.output(FactoryControlService.integerOperations().multiply(Integer.parseInt(firstNum), Integer.parseInt(secondNum)));
            } else if (((Integer.parseInt(typeOperations) == 4))) {
                consoleHelper.output(FactoryControlService.integerOperations().divide(Integer.parseInt(firstNum), Integer.parseInt(secondNum)));
            }
        } else if(Integer.parseInt(typeValue) == 2) {
            if (Integer.parseInt(typeOperations) == 1) {
                consoleHelper.output(FactoryControlService.doubleOperations().add(Double.parseDouble(firstNum), Double.parseDouble(secondNum)));
            } else if ((Integer.parseInt(typeOperations) == 2)) {
                consoleHelper.output(FactoryControlService.doubleOperations().subtract(Double.parseDouble(firstNum), Double.parseDouble(secondNum)));
            } else if ((Integer.parseInt(typeOperations) == 3)) {
                consoleHelper.output(FactoryControlService.doubleOperations().multiply(Double.parseDouble(firstNum), Double.parseDouble(secondNum)));
            } else if (((Integer.parseInt(typeOperations) == 4))) {
                consoleHelper.output(FactoryControlService.doubleOperations().divide(Double.parseDouble(firstNum), Double.parseDouble(secondNum)));
            }
        }
    }
}