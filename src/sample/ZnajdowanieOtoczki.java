package sample;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Stack;

import static sample.PrzygotowaniePunktow.sortujPunkty;

public class ZnajdowanieOtoczki {
    public static Stack<Point2D> znajdzOtoczke(ArrayList<Point2D> punkty){
        Stack<Point2D> stos = new Stack<>();
        sortujPunkty(punkty);
        stos.push(punkty.get(0));
        stos.push(punkty.get(1));
        stos.push(punkty.get(2));
        return stos;
    }

    // Sprawdzamy w którą stronę "skręca" dana trójka pktów (p, q, r).
    // Zwracamy następnujące wartości
    // 0 --> p, q i r są współliniowe
    // 1 --> w prawo (zgodne z ruchami wskazówek zegara)
    // 2 --> w lewo (niezgodne z ruchami wskazówek zegara)
    public static int orientation(Point2D p, Point2D q, Point2D r) {
        double val =
                (q.getY() - p.getY()) * (r.getX() - q.getX())-
                        (q.getX() - p.getX()) * (r.getY() - q.getY());
        if (val == 0) return 0;  // współliniowe
        return (val > 0) ? 1 : 2; // w prawo lub w lewo
    }
}
