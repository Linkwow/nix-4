package cellcreator.service;

import cellcreator.entity.Cell;

public class CellCreator {

    public static Cell[][] cellBoardCreate(int m, int n) {
        Cell[][] array = new Cell[m][n];
        for (int column = 0; column < array.length; column++) {
            for (int row = 0; row < array[column].length; row++)
                array[column][row] = new Cell(column, row, array.length, array[column].length);
        }
        return array;
    }

    public static Cell[][] cellBoardCreate(int m, int n, boolean empty) {
        Cell[][] array = new Cell[m][n];
        for (int column = 0; column < array.length; column++) {
            for (int row = 0; row < array[column].length; row++)
                array[column][row] = new Cell(column, row, array.length, array[column].length, empty);
        }
        return array;
    }

    public static void printCellBoard(Cell[][] cellBoard){
        for (int column = 0; column < cellBoard.length; column++) {
            for (int row = 0; row < cellBoard[column].length; row++) {
                System.out.print(cellBoard[column][row]);
                if(row < cellBoard[column].length - 1)
                    System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception E) {
            System.out.println(E);
        }
    }
}
