package cellcreator;

import static cellcreator.service.Controller.*;

public class CellBoard {

    public static void main(String[] args) {
        try {
            run();
        } catch (Exception e){
            throw new RuntimeException("Не работает");
        }
    }
}