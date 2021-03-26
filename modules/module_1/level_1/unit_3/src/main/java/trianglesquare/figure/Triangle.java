package trianglesquare.figure;

import trianglesquare.point.Point;

import java.util.Scanner;

public class Triangle implements Figure {
    private final Scanner scanner = new Scanner(System.in);
    private final Point a = new Point();
    private final Point b = new Point();
    private final Point c = new Point();

    private void setPoint(Point obj){
        System.out.println("¬ведите координаты X точки " + obj.getIndex() + "");
        obj.setAbscissaPosition(scanner.nextInt());
        System.out.println("¬ведите координаты Y точки " + obj.getIndex() + "");
        obj.setOrdinatePosition(scanner.nextInt());
    }

    private void calculate(Point a, Point b, Point c){
        int firstDeterminant = ((a.getAbscissaPosition() - c.getAbscissaPosition()) * (b.getOrdinatePosition() - c.getOrdinatePosition()));
        int secondDeterminant = ((b.getAbscissaPosition() - c.getAbscissaPosition()) * (a.getOrdinatePosition() - c.getOrdinatePosition()));
        double result = 0.5 * Math.abs(firstDeterminant - secondDeterminant);
        System.out.println("ѕлощадь треугольника = " + result);
    }

    @Override
    public void calculateSquare(){
        setPoint(a);
        setPoint(b);
        setPoint(c);
        calculate(a, b, c);
    }
}