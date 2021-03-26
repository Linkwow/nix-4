package cellcreator.service;

import cellcreator.entity.Cell;

public class Logic {

    public static void calculate(Cell[][] cellBoard, Cell[][] nextGeneration) {
        for (int column = 0; column < cellBoard.length; column++) {
            for (int row = 0; row < cellBoard[column].length; row++) {
                nextGeneration[column][row].setState(rulesOfLife(cellBoard[column][row].getState(), getSum(cellBoard, column, row)));
            }
        }
    }

    private static int getSum(Cell[][] cellBoard, int column, int row) {
        int sum = 0;
        sum += takeStateRightCell(cellBoard, column, row);
        sum += takeStateLeftCell(cellBoard, column, row);
        sum += takeStateUpCell(cellBoard, column, row);
        sum += takeStateDownCell(cellBoard, column, row);
        sum += takeStateUpRight(cellBoard, column, row);
        sum += takeStateUpLeft(cellBoard, column, row);
        sum += takeStateDownLeft(cellBoard, column, row);
        sum += takeStateDownRight(cellBoard, column, row);
        return sum;
   }

    private static int takeStateRightCell(Cell[][] cellBoard, int column, int row){
        return cellBoard[column][row].hasRight()?cellBoard[column][row + 1].getState():cellBoard[column][0].getState();
      }

    private static int takeStateLeftCell(Cell[][] cellBoard, int column, int row){
       return cellBoard[column][row].hasLeft()?cellBoard[column][row - 1].getState():cellBoard[column][cellBoard[column].length - 1].getState();
    }

    private static int takeStateUpCell(Cell[][] cellBoard, int column, int row){
        return cellBoard[column][row].hasUp()?cellBoard[column - 1][row].getState():cellBoard[cellBoard.length - 1][row].getState();
    }

    private static int takeStateDownCell(Cell[][] cellBoard, int column, int row){
        return cellBoard[column][row].hasDown()?cellBoard[column + 1][row].getState():cellBoard[0][row].getState();
    }

    private static int takeStateUpRight(Cell[][] cellBoard, int column, int row){
        if(cellBoard[column][row].hasUp()){
            if(cellBoard[column][row].hasRight()){
                return cellBoard[column - 1][row + 1].getState();
            } else{
                return cellBoard[column - 1][0].getState();
            }
        } else {
            if(cellBoard[column][row].hasRight()){
                return cellBoard[cellBoard.length - 1][row + 1].getState();
            } else {
                return cellBoard[cellBoard.length - 1][0].getState();
            }
        }
    }

    private static int takeStateUpLeft(Cell[][] cellBoard, int column, int row){
        if(cellBoard[column][row].hasUp()){
            if(cellBoard[column][row].hasLeft()){
                return cellBoard[column - 1][row - 1].getState();
            } else{
                return cellBoard[column - 1][cellBoard[column].length - 1].getState();
            }
        } else {
            if(cellBoard[column][row].hasLeft()){
                return cellBoard[cellBoard.length - 1][row - 1].getState();
            } else {
                return cellBoard[cellBoard.length - 1][cellBoard[column].length - 1].getState();
            }
        }
    }

    private static int takeStateDownLeft(Cell[][] cellBoard, int column, int row){
        if(cellBoard[column][row].hasDown()){
            if(cellBoard[column][row].hasLeft()){
                return cellBoard[column + 1][row - 1].getState();
            } else{
                return cellBoard[column + 1][cellBoard[column].length - 1].getState();
            }
        } else {
            if(cellBoard[column][row].hasLeft()){
                return cellBoard[0][row - 1].getState();
            } else {
                return cellBoard[0][cellBoard[column].length - 1].getState();
            }
        }
    }

    private static int takeStateDownRight(Cell[][] cellBoard, int column, int row){
        if(cellBoard[column][row].hasDown()){
            if(cellBoard[column][row].hasRight()){
                return cellBoard[column + 1][row + 1].getState();
            } else{
                return cellBoard[column + 1][0].getState();
            }
        } else {
            if(cellBoard[column][row].hasRight()){
                return cellBoard[0][row + 1].getState();
            } else {
                return cellBoard[0][0].getState();
            }
        }
    }

    private static int rulesOfLife(int state, int neighbourSum){
        int result = 0;
        if(state == 1 && neighbourSum < 2){
            result = 0;
        } else if(state == 1 && (neighbourSum == 2 || neighbourSum == 3)){
            result = 1;
        } else if(state == 1 && neighbourSum > 3){
            result = 0;
        } else if(state == 0 && neighbourSum == 3){
            result = 1;
        }
        return result;
    }
}