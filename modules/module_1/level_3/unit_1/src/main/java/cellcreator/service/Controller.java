package cellcreator.service;

import cellcreator.entity.Cell;
import static cellcreator.service.CellCreator.*;
import static cellcreator.console.ConsoleInteraction.*;
import static cellcreator.service.Logic.calculate;

public class Controller {
    public static void run() throws Exception{
        String[] size = inputSize().split(" ");
        int generation = generationCount();
        Cell[][] currentGeneration = cellBoardCreate(Integer.parseInt(size[0]), Integer.parseInt(size[1]));
        Cell[][] nextGen =cellBoardCreate(Integer.parseInt(size[0]), Integer.parseInt(size[1]), true);
        for (int i = 0; i < generation; i++) {
            if (i % 2 == 0) {
                calculate(currentGeneration, nextGen);
                System.out.println("Поколение номер " + i);
                printCellBoard(nextGen);
            } else {
                calculate(nextGen, currentGeneration);
                System.out.println("Поколение номер " + i);
                printCellBoard(currentGeneration);
            }
            Thread.sleep(500);
            cls();
        }
    }
}