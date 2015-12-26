package sample;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PrzygotowaniePunktow {
    public static Point2D findFirstPoint(ArrayList<Point2D> punkty) {
        //znajduje najniższy punkt
        Point2D first = punkty.get(0);
        for (Point2D punkt : punkty) {
            if ((punkt.getY() < first.getY())
                    || ((punkt.getY() == first.getY()) && (punkt.getX() < first.getX()))) {
                first = punkt;
            }
        }
        return first;
    }

    public static void sortujPunkty(ArrayList<Point2D> punkty) {
        Point2D first = findFirstPoint(punkty);
        //wypiszPunkty(punkty);
        punkty.remove(first);
        przerzucWzgledemPierwszego(punkty,first);
        first = new Point2D(0, 0);
        //System.out.printf("Rozmiar nowego to: %d\n", punkty.size());
        Collections.sort(punkty, new Comparator<Point2D>() {
            @Override
            public int compare(Point2D o1, Point2D o2) {
                double tan1 = Math.atan2(o1.getX(), o1.getY());
                double tan2 = Math.atan2(o2.getX(), o2.getY());
                return Double.compare(tan2, tan1);
            }
        });
        /*
        if(tan1==tan2){
            int retval = Double.compare((o1.distance(0,0)),(o2.distance(0,0)));

                   //tutaj sie powinno usuwać te punkty bo leżaą na jednej linii wzgledem zera. Oznacza to żę ten bliższy na pewno nie bedzie w otoczce

                    if(retval>0){
                        punkty.remove(o2);
                    }else{
                        punkty.remove(o1);
                    }
            return retval;
        }
        */
        punkty.add(0, first);
    }

    public static void wypiszPunkty(ArrayList<Point2D> punkty) {
        for (Point2D punkt : punkty) {
            System.out.println(punkt);
        }
    }





    public static void przerzucWzgledemPierwszego(ArrayList<Point2D> punkty,Point2D pierwszy) {
        double x = pierwszy.getX();
        double y = pierwszy.getY();
        for (Point2D punkt : punkty) {
            Point2D nowy = punkt.subtract(x, y);
            Collections.replaceAll(punkty, punkt, nowy);
        }
    }
}
