package cellcreator.entity;

import java.util.Random;
import java.util.Date;

public class Cell {
    private int state;
    private int maxRowIndex;
    private int maxColumnIndex;
    private int rowPosition;
    private int columnPosition;
    private boolean emptyCell;

    private static final Date date = new Date();
    private static final Random rand = new Random(date.getTime());

    public Cell(int column, int row, int columnLength, int rowLength) {
        columnPosition = column;
        rowPosition = row;
        maxColumnIndex = columnLength - 1;
        maxRowIndex = rowLength - 1;
        state = rand.nextInt(2);
    }

    public Cell(int column, int row, int columnLength, int rowLength, boolean emptyCell) {
        columnPosition = column;
        rowPosition = row;
        maxColumnIndex = columnLength - 1;
        maxRowIndex = rowLength - 1;
        this.emptyCell = emptyCell;
    }

    public int getState() {
        return this.state;
    }

    public boolean hasRight() {
        return rowPosition + 1 <= maxRowIndex;
    }

    public boolean hasLeft() {
        return rowPosition - 1 >= 0;
    }

    public boolean hasDown() {
        return columnPosition + 1 <= maxColumnIndex;
    }

    public boolean hasUp() {
        return columnPosition - 1 >= 0;
    }

    public void setState(int i){
        state = i;
    }

    public String toString() {
        String value = "";
        if (state == 1) {
            value = "*";
        } else {
            value = "";
        }
        return value;
    }
}
