package trianglesquare.point;

public class Point {
    private int abscissaPosition;
    private int ordinatePosition;
    private static int count = 0;
    private int id = 65 + count++;
    private char index = (char) id;

    public int getAbscissaPosition() {
        return abscissaPosition;
    }

    public int getOrdinatePosition() {
        return ordinatePosition;
    }

    public char getIndex() {
        return index;
    }

    public void setAbscissaPosition(int abscissaPosition) {
        this.abscissaPosition = abscissaPosition;
    }

    public void setOrdinatePosition(int ordinatePosition) {
        this.ordinatePosition = ordinatePosition;
    }
}