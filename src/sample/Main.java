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

import static sample.MonteCarlo.MonteCarlo;
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
        System.out.printf("Pole figury to: %f\n",MonteCarlo(canvas,wierzcholki));
        rysujPunktyPro(punkty, canvas);
        rysujOtoczke(wierzcholki,canvas);
        ArrayList<Point2D> otoczka = stackToArrayList(wierzcholki);

    }

    public void dodajPunkty() {
        this.punkty = new PointsFromFileReader().read("kwadrat");
        //this.punkty = new PointsFromFileReader().read("dane.txt");
        //this.punkty = new PointsFromFileReader().read("dane2");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
