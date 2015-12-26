package sample;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.rmi.MarshalException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Main extends Application {
    ArrayList<Point2D> punkty = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Otoczka wypukła");
        Group root = new Group();
        Canvas canvas = new Canvas(1000, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        dodajPunkty();
        sortujPunkty();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        rysujPunktyPro(punkty, canvas);
    }

    public Point2D findFirstPoint() {
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

    public void sortujPunkty() {
        Point2D first = findFirstPoint();
        wypiszPunkty();
        punkty.remove(first);
        przerzucWzgledemPierwszego(first);
        first = new Point2D(0,0);
        System.out.printf("Rozmiar nowego to: %d\n",punkty.size());
        Collections.sort(punkty, new Comparator<Point2D>() {
            @Override
            public int compare(Point2D o1, Point2D o2) {
                double tan1 = Math.atan2(o1.getX(), o1.getY());
                double tan2 = Math.atan2(o2.getX(), o2.getY());
                if(tan1==tan2){
                    int retval = Double.compare((o1.distance(0,0)),(o2.distance(0,0)));
                   /*
                   //tutaj sie powinno usuwać te punkty bo leżaą na jednej linii wzgledem zera. Oznacza to żę ten bliższy na pewno nie bedzie w otoczce

                    if(retval>0){
                        punkty.remove(o2);
                    }else{
                        punkty.remove(o1);
                    }*/
                    return retval;
                }
                return Double.compare(tan2, tan1);
            }
        });
        punkty.add(0,first);
        System.out.printf("Na końcu dlugosc rowna %d\n", punkty.size());
        System.out.println("nowe");
        wypiszPunkty();
        System.out.println("zamieniam pierwszy");


        wypiszPunkty();
    }


    public void wypiszPunkty(){
        for (Point2D punkt: punkty) {
            System.out.println(punkt);
        }
    }

    public void dodajPunkty() {
        this.punkty = new PointsFromFileReader().read("dane.txt");
    }

    public void rysujPunkty(ArrayList<Point2D> punkty, Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        for (Point2D punkt : punkty) {
            gc.fillOval(punkt.getX(),punkt.getY(), 5, 5);
            gc.fillText(Integer.toString(punkty.indexOf(punkt)),punkt.getX(),punkt.getY());
        }
    }

    public void przerzucWzgledemPierwszego(Point2D pierwszy){
        double x = pierwszy.getX();
        double y = pierwszy.getY();
        for (Point2D punkt : punkty) {
            Point2D nowy = punkt.subtract(x,y);
            Collections.replaceAll(punkty,punkt,nowy);
            System.out.println("lol");
        }
    }


    public void rysujPunktyPro(ArrayList<Point2D> punkty, Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double w = canvas.getWidth();
        double h = canvas.getHeight();
        gc.strokeLine(w/2,0,w/2,h);
        gc.strokeLine(0,h/2,w,h/2);

        for (Point2D punkt : punkty){
            gc.fillOval(punkt.getX()+w/2,h-punkt.getY()-h/2, 5, 5);
            System.out.printf("x: %f, y: %f \n",punkt.getX()+w/2,h-punkt.getY()-h/2);
            gc.fillText(Integer.toString(punkty.indexOf(punkt)),punkt.getX()+w/2,h-punkt.getY()-h/2);

        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
