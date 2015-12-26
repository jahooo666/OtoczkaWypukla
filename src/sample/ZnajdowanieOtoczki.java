package sample;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.Stack;

import static sample.PrzygotowaniePunktow.sortujPunkty;

public class ZnajdowanieOtoczki {
    public static Stack<Point2D> znajdzOtoczke(ArrayList<Point2D> punkty) {
        Stack<Point2D> stos = new Stack<>();
        sortujPunkty(punkty);
        stos.push(punkty.get(0));
        stos.push(punkty.get(1));
        stos.push(punkty.get(2));

        for (int i = 3; i < punkty.size(); i++)
        {
            // Usuwamy najwyższy element ze stosu dopóki nie tworzą skrętu w prawo
            while (orientation(nextToTop(stos), stos.peek(), punkty.get(i)) != 2)
                stos.pop();
            stos.push(punkty.get(i));
        }

        return stos;
    }

    public static Point2D nextToTop(Stack<Point2D> stos){
        Point2D top = stos.pop();
        Point2D nextToTop = stos.peek();
        stos.push(top);
        return nextToTop;
    }

    public static int orientation(Point2D p, Point2D q, Point2D r) {
        // Sprawdzamy w którą stronę "skręca" dana trójka pktów (p, q, r).
        // Zwracamy następnujące wartości
        // 0 --> p, q i r są współliniowe
        // 1 --> w prawo (zgodne z ruchami wskazówek zegara)
        // 2 --> w lewo (niezgodne z ruchami wskazówek zegara)

        double val =    (q.getY() - p.getY()) * (r.getX() - q.getX()) -
                        (q.getX() - p.getX()) * (r.getY() - q.getY());

        if (val == 0) return 0;  // współliniowe
        return (val > 0) ? 1 : 2; // w prawo lub w lewo
    }
}
