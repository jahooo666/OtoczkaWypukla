package sample;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MonteCarlo {
    public static ArrayList<Point2D> losujPunkty(int ile, Canvas canvas) {
        //losowanie podanej liczby punktow w obszarze ograniczonym przez canvas
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        ArrayList<Point2D> wylosowane = new ArrayList<>();
        for (int i = 0; i < ile; i++) {
            double x = Math.random() * w;
            double y = Math.random() * h;
            Point2D nowy = new Point2D(x, y);
            wylosowane.add(nowy);
        }

        return wylosowane;
    }

    public boolean czyNalezy(Point2D punkt, ArrayList<Point2D> otoczka){
       //if( )
        return true;
    }


    public static ArrayList<Point2D> stackToArrayList(Stack<Point2D> stos) {
        //konwerter stosu na ArrayListe - z odwroconą kolejnością!
        ArrayList<Point2D> wierzcholki = new ArrayList<>();
        while(!stos.isEmpty()){
            wierzcholki.add(stos.pop());
        }

        return wierzcholki;
    }

}
