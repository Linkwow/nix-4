package consolehelper;

public class Service {
    private ConsoleHelper consoleHelper = new ConsoleHelper();

    public String menuType(){
        System.out.println("Выберите с каким типом данных будут производится операции : ");
        System.out.println("1. Integer.");
        System.out.println("2. Double.");
        return consoleHelper.input();
    }

    public String menuOperations(){
        System.out.println("Выберите, какую операцию вы желаете осуществить : ");
        System.out.println("1. Сложение.");
        return consoleHelper.input();
    }

    public String firstNum(){
        System.out.println("Введите перевое число : ");
        return consoleHelper.input();
    }

    public String secondNum(){
        System.out.println("Введите второе число : ");
        return consoleHelper.input();
    }

    public String end(){
        System.out.println("Вы желаете продолжить?");
        System.out.println("1. Да.");
        System.out.println("2. Нет.");
        return consoleHelper.input();
    }
}