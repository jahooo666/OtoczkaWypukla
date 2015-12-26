package sample;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import static sample.PrzecinanieOdcinkow.sprawdzPrzecinanie;

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

    public boolean czyNalezy(Point2D punkt, ArrayList<Point2D> otoczka) {
        int liczbaPrzeciec = 0;
        Point2D punktgora = new Point2D(punkt.getX(),0);
        Point2D firstOfAll = otoczka.get(0);
        Point2D first = firstOfAll;
        Point2D second = otoczka.get(1);

        for (int i = 2; i < otoczka.size(); i++) {
            first = second;
            second = otoczka.get(i);
            if(sprawdzPrzecinanie(first,second,punkt,punktgora)){
                liczbaPrzeciec++;
            }
        }
        //na koniec ostatni z pierwszym
        first = second;
        second = firstOfAll;
        if(sprawdzPrzecinanie(first,second,punkt,punktgora)){
            liczbaPrzeciec++;
        }

        if (liczbaPrzeciec == 1) return true;
        if ((liczbaPrzeciec == 2) || (liczbaPrzeciec == 0)) return false;
        else {
            System.out.println("Jest wiecej niż 2 przeciecia. To jest niemożliwe- otoczka jest źle zrobbiona");
            return false;
        }
    }


    public static ArrayList<Point2D> stackToArrayList(Stack<Point2D> stos) {
        //konwerter stosu na ArrayListe - z odwroconą kolejnością!
        ArrayList<Point2D> wierzcholki = new ArrayList<>();
        while (!stos.isEmpty()) {
            wierzcholki.add(stos.pop());
        }

        return wierzcholki;
    }

}
