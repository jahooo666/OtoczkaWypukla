package sample;

import javafx.geometry.Point2D;

public class PrzecinanieOdcinkow {
    // sprawdzamy, czy punkt z należy do odcinka |AB|
    private static boolean przynaleznosc(double Ax, double Ay, double Bx, double By, double Cx, double Cy) {
        double det; //wyznacznik macierCy

        det = Ax * By + Bx * Cy + Cx * Ay - Cx * By - Ax * Cy - Bx * Ay;
        if (det != 0)
            return false;
        else {
            if ((Math.min(Ax, Bx) <= Cx) && (Cx <= Math.max(Ax, Bx)) &&
                    (Math.min(Ay, By) <= Cy) && (Cy <= Math.max(Ay, By)))
                return true;
            else
                return false;
        }
    }

    //  wyznacznik macierzy
    private static double det_matrix(double Ax, double Ay, double Bx, double By, double Cx, double Cy) {
        return (Ax * By + Bx * Cy + Cx * Ay - Cx * By - Ax * Cy - Bx * Ay);
    }


    //4 punkty -> tablica xów
    public static double[] getXesFromPoints(Point2D A, Point2D B, Point2D C, Point2D D) {
        double x[] = new double[4];
        x[0] = A.getX();
        x[1] = B.getX();
        x[2] = C.getX();
        x[3] = D.getX();
        return x;
    }

    //4 punkty -> tablica yów
    public static double[] getYesFromPoints(Point2D A, Point2D B, Point2D C, Point2D D) {
        double y[] = new double[4];
        y[0] = A.getY();
        y[1] = B.getY();
        y[2] = C.getY();
        y[3] = D.getY();
        return y;
    }

    public static boolean sprawdzPrzecinanie(Point2D A, Point2D B, Point2D C, Point2D D) {
        int i, det; //wyznacznik macierzy
        double x[] = getXesFromPoints(A, B, C, D); //tablica wspolrzednych x punktow
        double y[] = getYesFromPoints(A, B, C, D); //tablica wspolrzednych y punktow


        //Sprawdzanie, czy jakiś punkt należy do drugiego odcinka
        if ((przynaleznosc(x[0], y[0], x[1], y[1], x[2], y[2]))
                || (przynaleznosc(x[0], y[0], x[1], y[1], x[3], y[3]))
                || (przynaleznosc(x[2], y[2], x[3], y[3], x[0], y[0]))
                || (przynaleznosc(x[2], y[2], x[3], y[3], x[1], y[1]))
                ) {
            //System.out.println("Odcinki sie przecinaja- jest przynaleznosc");
            return true;
        } else { //zaden punkt nie nalezy do drugego odcinka
            if (((det_matrix(x[0], y[0], x[1], y[1], x[2], y[2])) * (det_matrix(x[0], y[0], x[1], y[1], x[3], y[3])) >= 0)
                    || ((det_matrix(x[2], y[2], x[3], y[3], x[0], y[0])) * (det_matrix(x[2], y[2], x[3], y[3], x[1], y[1])) >= 0)
                    ) {
                //System.out.println("Odcinki sie NIE przecinaja");
                return false;
            } else { //znaki wyznaczników sa równe
                //System.out.println("Odcinki sie przecinaja bo punkty leza po przeciwnych stronach");
                return true;
            }
        }
    }
}