package sample;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Stack;

public class Rysowanie {

    public static void rysujPunkty(ArrayList<Point2D> punkty, Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        for (Point2D punkt : punkty) {
            gc.fillOval(punkt.getX(), punkt.getY(), 5, 5);
            gc.fillText(Integer.toString(punkty.indexOf(punkt)), punkt.getX(), punkt.getY());
        }
    }
    public static void rysujWylosowanePunkty(ArrayList<Point2D> punkty, Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.LIGHTBLUE);
        for (Point2D punkt : punkty) {
            gc.fillOval(punkt.getX(), punkt.getY(), 3, 3);
        }
    }


    public static void rysujPunktyPro(ArrayList<Point2D> punkty, Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.RED);
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        gc.strokeLine(w / 2, 0, w / 2, h);
        gc.strokeLine(0, h / 2, w, h / 2);

        for (Point2D punkt : punkty) {
            gc.fillOval(punkt.getX() + w / 2, h - punkt.getY() - h / 2, 5, 5);
            //System.out.printf("x: %f, y: %f \n", punkt.getX() + w / 2, h - punkt.getY() - h / 2);
            gc.fillText(Integer.toString(punkty.indexOf(punkt)), punkt.getX() + w / 2, h - punkt.getY() - h / 2);

        }
    }

    public static void rysujOtoczke(Stack<Point2D> wierzcholki, Canvas canvas){
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        gc.setLineWidth(3);
        gc.setStroke(Color.RED);
        Point2D first = wierzcholki.pop();
        Point2D firstofAll = first;
        Point2D second = wierzcholki.pop();

        //gc.moveTo(first.getX(),first.getY());
        while(!wierzcholki.isEmpty()){
            gc.strokeLine(first.getX()+w/2,h-first.getY()-h/2,second.getX()+w/2,h-second.getY()-h/2);
            first = second;
            second =  wierzcholki.pop();
        }
        gc.strokeLine(first.getX()+w/2,h-first.getY()-h/2,second.getX()+w/2,h-second.getY()-h/2);
        first = firstofAll;
        gc.strokeLine(first.getX()+w/2,h-first.getY()-h/2,second.getX()+w/2,h-second.getY()-h/2);
    }


}
