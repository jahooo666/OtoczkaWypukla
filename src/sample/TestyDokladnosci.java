package sample;

import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.Stack;

import static sample.Main.dodajPunkty;
import static sample.MonteCarlo.monteCarloCalculate;
import static sample.ZnajdowanieOtoczki.znajdzOtoczke;

public class TestyDokladnosci {
    public static void testuj(Canvas canvas) {
        String[] pliki = new String[4];
        pliki[0] = "dane.txt";  // przykladowe dane z 'trudniejszymi' punktami w celu dokladnego zbadania otoczki
        pliki[1] = "dane2";     // oryginalne dane testowe z isoda
        pliki[2] = "kwadrat"; //plik zawierający kwadrat o boku 100
        pliki[3] = "caly";  //plik zawierający całą ćwiartkę układu (boki 400x500)

        Double[] oczekiwaneWyniki = new Double[4];
        oczekiwaneWyniki[0] = 10000.0;
        oczekiwaneWyniki[1] = 10000.0;
        oczekiwaneWyniki[2] = 10000.0;
        oczekiwaneWyniki[3] = 200000.0;
        System.out.printf("\nNazwa pliku\t\t100\t\t\t1000\t\t\t10^4\t\t\t10^5\t\t\t10^6\n");
        for (int i = 0; i < pliki.length ; i++) {
            System.out.printf("\n%s: \t",pliki[i]);

            ArrayList<Point2D> punkty =  dodajPunkty(pliki[i]);
            Stack<Point2D> wierzcholki = znajdzOtoczke(punkty);

            for (int j = 2; j < 7; j++) {
                System.out.printf("%f\t",monteCarloCalculate(canvas,wierzcholki,(int)Math.pow(10.0,j)));
            }
        }
    }
}
