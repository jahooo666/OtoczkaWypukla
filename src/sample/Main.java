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
        Canvas canvas = new Canvas(700, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        dodajPunkty();
        sortujPunkty();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        rysujPunkty(punkty, gc);
    }

    public Point2D findLowestPoint() {
        //znajduje najniższy punkt
        Point2D lowest = punkty.get(0);
        for (Point2D punkt : punkty) {
            if ((punkt.getY() > lowest.getY())
                    || ((punkt.getY() == lowest.getY()) && (punkt.getY() == lowest.getY()))) {
                lowest = punkt;
            }
        }
        return lowest;
    }

    public void sortujPunkty() {
        Point2D lowest = findLowestPoint();
        wypiszPunkty();
        Collections.sort(punkty, new Comparator<Point2D>() {
            @Override
            public int compare(Point2D o1, Point2D o2) {
                double tan1 = Math.atan2(o1.getX(), o1.getY());
                double tan2 = Math.atan2(o2.getX(), o2.getY());
                return Double.compare(tan1, tan2);
            }
        });
        System.out.println("nowe");
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

    public void rysujPunkty(ArrayList<Point2D> punkty, GraphicsContext gc) {

        for (Point2D punkt : punkty) {
            gc.fillOval(punkt.getX(), punkt.getY(), 5, 5);
            gc.fillText(Integer.toString(punkty.indexOf(punkt)),punkt.getX(), punkt.getY());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
