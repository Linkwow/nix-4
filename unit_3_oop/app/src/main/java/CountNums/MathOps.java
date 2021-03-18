package CountNums;

import CountNums.services.LogicOfApp;

public class MathOps {

    public static void main(String[] args) {
        LogicOfApp logic = new LogicOfApp();
        try {
            logic.start();
        } catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
