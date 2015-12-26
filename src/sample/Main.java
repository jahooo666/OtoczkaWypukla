package sample;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Stack;

import static sample.MonteCarlo.losujPunkty;
import static sample.MonteCarlo.stackToArrayList;
import static sample.PrzecinanieOdcinkow.sprawdzPrzecinanie;
import static sample.Rysowanie.*;
import static sample.ZnajdowanieOtoczki.znajdzOtoczke;

public class Main extends Application {
    ArrayList<Point2D> punkty = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Otoczka wypukła");
        Group root = new Group();
        Canvas canvas = new Canvas(1000, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        dodajPunkty();
        Stack<Point2D> wierzcholki = znajdzOtoczke(punkty);

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        rysujPunktyPro(punkty, canvas);
        rysujOtoczke(wierzcholki,canvas);


        Point2D A = new Point2D(1, 2);
        Point2D B = new Point2D(3, 4);

        Point2D C = new Point2D(2, 1);
        Point2D D = new Point2D(3, 4);
        System.out.println(sprawdzPrzecinanie(A,B,C,D));


        rysujWylosowanePunkty(losujPunkty(1000,canvas),canvas);
        ArrayList<Point2D> otoczka = stackToArrayList(wierzcholki);

    }

    public void dodajPunkty() {
        this.punkty = new PointsFromFileReader().read("dane.txt");
        //this.punkty = new PointsFromFileReader().read("dane2");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
