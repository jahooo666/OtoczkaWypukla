package sample;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import static sample.PrzecinanieOdcinkow.sprawdzPrzecinanie;
import static sample.Rysowanie.rysujWylosowanePunkty;

public class MonteCarlo {

    public static double monteCarloCalculate(Canvas canvas, Stack<Point2D> wierzcholki,int dokladnosc) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        ArrayList<Point2D> otoczka = stackToArrayList(wierzcholki);

        otoczka = przesunPunktyOtoczki(otoczka, canvas);
        int s = otoczka.size()-1;
        Point2D ostatni = new Point2D(otoczka.get(s).getX(),otoczka.get(s).getY());
        otoczka.add(ostatni);

        double poleCale = canvas.getHeight() * canvas.getWidth();
        int wszystkie = dokladnosc;
        int trafione = 0;
        ArrayList<Point2D> wylosowane = losujPunkty(wszystkie, canvas);
        rysujWylosowanePunkty(wylosowane, canvas);

        for (Point2D punkt : wylosowane) {
            if (czyNalezy(punkt, otoczka)){
                trafione++;
                canvas.getGraphicsContext2D().setFill(Color.DARKVIOLET);
                canvas.getGraphicsContext2D().fillOval(punkt.getX(), punkt.getY(), 3, 3);
            }
        }
        //System.out.printf("\nJest %d trafionych na %d wystrzelonych\n", trafione, wszystkie);
        //chyba jest problem z dzieleniem doubli vo ychodzi 0
        double wszystkieD = wszystkie;
        double trafioneD = trafione;

        double procentTrafien = (double)(trafioneD / wszystkieD)*100.0;
        //System.out.printf("Procent trafien to: %f procent \n", procentTrafien);
        double poleTrafione = procentTrafien * poleCale/100;
        return poleTrafione;
    }


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

    public static ArrayList<Point2D> przesunPunktyOtoczki(ArrayList<Point2D> wierzcholki, Canvas canvas) {
        //metoda do translatowania pktow otoczki tak zeby latwo dalo sie sprawdzac przy metodzie monte carlo
        ArrayList<Point2D> otoczka = new ArrayList();
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        for (Point2D punkt : wierzcholki) {
            otoczka.add((new Point2D(punkt.getX() + w / 2, h - punkt.getY() - h / 2)));
        }

        return otoczka;
    }


    public static boolean czyNalezy(Point2D punkt, ArrayList<Point2D> otoczka) {
        int liczbaPrzeciec = 0;
        Point2D punktgora = new Point2D(punkt.getX(), 0);
        Point2D firstOfAll = otoczka.get(0);
        Point2D first = firstOfAll;
        Point2D second = otoczka.get(1);

        for (int i = 2; i < otoczka.size(); i++) {
            if (sprawdzPrzecinanie(first, second, punkt,punktgora)) {
                liczbaPrzeciec++;
            }
            first = second;
            second = otoczka.get(i);

        }
        //na koniec ostatni z pierwszym
        first = second;
        second = firstOfAll;
        if (sprawdzPrzecinanie(first, second, punkt, punktgora)) {
            liczbaPrzeciec++;
        }

        if (liczbaPrzeciec == 1) {
            return true;
        } else if (liczbaPrzeciec == 2){
            return false;
        } else if (liczbaPrzeciec == 0){
            return false;
        } else {
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
        for (int i = wierzcholki.size() - 1; i >= 0; i--) {
            stos.push(wierzcholki.get(i));
        }
        return wierzcholki;
    }


}
